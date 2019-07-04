package writer;

import com.sun.source.tree.Scope;
import llvm.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GeneratorSlave {
    private List<CodeSnippet_Struct> m_Structs;

    private List<CodeSnippet_Base> m_Constants;
    private int m_ConstantCounter = 0;

    private HashSet<Class> m_NativeMap;
    private List<CodeSnippet_FuncDef> m_FunctionDefinitions;
    private List<CodeSnippet_FuncDecl> m_FunctionDeclarations;

    private ArrayDeque<ScopeInterface> m_ScopeStack;
    private ScopeInterface m_ActiveScope;

    public GeneratorSlave() {
        m_Structs = new ArrayList<>();
        m_Constants = new ArrayList<>();
        m_NativeMap = new HashSet<>();
        m_FunctionDefinitions = new ArrayList<>();
        m_FunctionDeclarations = new ArrayList<>();
        m_ScopeStack = new ArrayDeque<>();
    }

    public boolean IsDefinitionInScope() {
        return m_ActiveScope instanceof CodeSnippet_FuncDef;
    }

    public ScopeInterface GetScopeSnippet() {
        return m_ActiveScope;
    }

    public CodeSnippet_FuncDef GetScopeSnippetAsDef() {
        return (CodeSnippet_FuncDef) m_ActiveScope;
    }

    public CodeSnippet_FuncCall GetScopeSnippetAsCall() {
        return (CodeSnippet_FuncCall) m_ActiveScope;
    }

    public ParamContainer CreateNativeCall(NativeFunction name) {
        Class nativeClass = name.getClass();
        if (!m_NativeMap.contains(nativeClass)) {
            name.CreateFunctionDeclaration(this);
            m_NativeMap.add(nativeClass);
        }

        return name.CreateFunctionCall(this);
    }

    public void CreateStruct(String name, List<TypeWrapper> entries) {
        CodeSnippet_Struct switchSnippet = new CodeSnippet_Struct(name);
        m_Structs.add(switchSnippet);
        for (TypeWrapper ent : entries) {
            switchSnippet.AddEntry(ent);
        }
    }

    public ParamContainer CreateStringConstant(String content) {
        // Convert \n, \t, ... to hex code
        StringBuilder builder = new StringBuilder();
        for (char c : content.toCharArray()) {
            if (Character.isLetterOrDigit(c) || c == '%') {
                builder.append(c);
            } else {
                String hexValue = Integer.toHexString(c);
                if (hexValue.length() < 2) {
                    hexValue = "0" + hexValue;
                }
                builder.append("\\" + hexValue.toUpperCase());
            }
        }

        TypeWrapper stringType = TypeManager.STRING(content.length() + 1);
        VariableWrapper var = VariableWrapper.CONSTSTRING(m_ConstantCounter++);
        // String constName = String.format("@.str.%d", m_ConstantCounter++);
        // CodeSnippet_Plain constType = new CodeSnippet_Plain(stringWrapper.CreateTypeName());

        CodeSnippet_Base snippet = new CodeSnippet_Args("%s = constant %s c\"%s\\00\"", var, stringType, builder.toString());

        m_Constants.add(snippet);

        return new ParamContainer(new TypeWrapper_Pointer(stringType), var);
    }

//    public CodeSnippet_FuncCall CreatePrintfCall(CodeSnippet_Base InSourceParam, List<CodeSnippet_Base> InFiller) {
//        InFiller.add(0, InSourceParam);
//        CodeSnippet_FuncCall call = new CodeSnippet_FuncCall("printf", new CodeSnippet_Type(CodeSnippet_Type.EType.INTPTR), InFiller, new CodeSnippet_Plain("(i8*, ...)"));
//        GetScopeSnippetAsDef().AddStatementWithStorage(call);
//
//        return call;
//    }

    public ParamContainer CreateFunctionCall(String name, TypeWrapper returnType, boolean bEnterScope) {
        CodeSnippet_FuncCall call = new CodeSnippet_FuncCall(name, returnType);

        // Add Call to current Function Scope
        ParamContainer outContainer = null;
        if (TypeWrapper_Other.VOID.CompareType(returnType)) {
            GetScopeSnippetAsDef().AddStatement(call);
            outContainer = ParamContainer.VOIDCONTAINER();
        } else {
            VariableWrapper ScopeIndex = GetScopeSnippetAsDef().AddStatementWithStorage(call);
            outContainer = new ParamContainer(returnType, ScopeIndex);
        }

        if (bEnterScope) {
            EnterScope(call);
        }

        return outContainer;
    }

    public void CreateFunctionCallParameter(ParamContainer type) {
        GetScopeSnippet().AddParameter(type.CreateParameterString());
    }

    public void CreateFunctionCallExtension(TypeWrapper type) {
        GetScopeSnippetAsCall().AddTypeExtension(type);
    }

    public CodeSnippet_Plain CreateReturnStmt(String type, String data) {
        return new CodeSnippet_Plain(String.format("ret %s %s", type, data));
    }

    public void CreateReturnStmt(ParamContainer param) {
        CodeSnippet_Base stmt = new CodeSnippet_Args("ret %s", param);
        GetScopeSnippetAsDef().AddStatement(stmt);
    }

    public CodeSnippet_FuncDef CreateFunctionDefinition(String name, TypeWrapper returnType, int paramCount, boolean bEnterScope) {
        CodeSnippet_FuncDef def = new CodeSnippet_FuncDef(name, new CodeSnippet_Plain(returnType.CreateTypeName()), paramCount);
        def.AddStatement(new CodeSnippet_Plain("begin:"));
        m_FunctionDefinitions.add(def);
        if (bEnterScope) {
            EnterScope(def);
        }

        return def;
    }

    public ParamContainer CreateFunctionParameter(TypeWrapper type) {
        VariableWrapper scopeVariable = GetScopeSnippet().AddParameter(type.CreateTypeName());
        return new ParamContainer(type, scopeVariable);
    }

    public void EnterScope(ScopeInterface InScopeObject) {
        m_ScopeStack.push(InScopeObject);
        m_ActiveScope = InScopeObject;
    }

    public void ExitScope() {
        m_ScopeStack.pop();
        if (m_ScopeStack.size() > 0) {
            m_ActiveScope = m_ScopeStack.peek();
        }
    }

    public CodeSnippet_FuncDecl CreateFunctionDeclaration(String name, CodeSnippet_Base returnType, boolean bEnterScope) {
        CodeSnippet_FuncDecl decl = new CodeSnippet_FuncDecl(name, returnType);
        m_FunctionDeclarations.add(decl);
        if (bEnterScope) {
            EnterScope(decl);
        }

        return decl;
    }

    public void CopyMemory(ParamContainer source, ParamContainer target) {
        int blockSize = Math.min(source.GetRootType().GetTypeByteSize(), target.GetRootType().GetTypeByteSize());

        // convert to char* if given as other types
        if (!TypeManager.CHARPTR().CompareType(source.GetRootType())) {
            source = CreateArrayElementPtr(source, 0);
        }
        if (!TypeManager.CHARPTR().CompareType(target.GetRootType())) {
            target = BitCast(target, TypeManager.CHARPTR());
        }

        CreateNativeCall(new NativeFunction_memcpy(target, source, blockSize));

        // CodeSnippet_Args stmt = new CodeSnippet_Args("call void @llvm.memcpy.p0i8.p0i8.i64(%s, %s, i64 %s, i1 false)", target, source, blockSize);
        // GetScopeSnippetAsDef().AddStatement(stmt);
    }

    public ParamContainer BitCast(ParamContainer source, TypeWrapper targetType) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("bitcast %s to %s", source, targetType);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(targetType, scopeVar);
    }


    public ParamContainer CastFloatToInt(ParamContainer source) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fptosi %s to i32", source);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.INT, scopeVar);
    }

    public ParamContainer CastIntToFloat(ParamContainer source) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("sitofp %s to float", source);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.FLOAT, scopeVar);
    }


    public ParamContainer ExtendFloatToDouble(ParamContainer source) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fpext %s to double", source);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.DOUBLE, scopeVar);
    }

    public VariableWrapper TruncateIntToChar(String source) {
        String exp = String.format("trunc i32 %s to i8", source);
        return GetScopeSnippetAsDef().AddStatementWithStorage(exp);
    }

    public VariableWrapper ExtendToInt(String sourceType, String sourceData) {
        String exp = String.format("sext %s %s to i32", sourceType, sourceData);
        return GetScopeSnippetAsDef().AddStatementWithStorage(exp);
    }

    public ParamContainer NegateBool(ParamContainer param) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("xor %s, 1", param);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(param, scopeVar);
    }

    public ParamContainer IncInt(ParamContainer param) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("add %s, 1", param);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(param, scopeVar);
    }

    public ParamContainer DecInt(ParamContainer param) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("sub %s, 1", param);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(param, scopeVar);
    }

    public ParamContainer IncFloat(ParamContainer param) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fadd %s, 1.0", param);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(param, scopeVar);
    }

    public ParamContainer DecFloat(ParamContainer param) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fsub %s, 1.0", param);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(param, scopeVar);
    }


    public ParamContainer NegateInt(ParamContainer param) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("mul %s, -1", param);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(param, scopeVar);
    }

    public ParamContainer NegateFloat(ParamContainer param) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fmul %s, -1.0", param);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(param, scopeVar);
    }

    public ParamContainer AddIntInt(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("add", left, right);
    }

    public ParamContainer AddFloatFloat(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("fadd", left, right);
    }

    public ParamContainer SubIntInt(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("sub", left, right);
    }

    public ParamContainer SubFloatFloat(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("fsub", left, right);
    }

    public ParamContainer MulIntInt(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("mul", left, right);
    }

    public ParamContainer MulFloatFloat(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("fmul", left, right);
    }

    public ParamContainer DivIntInt(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("sdiv", left, right);
    }

    public ParamContainer ModIntInt(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("srem", left, right);
    }

    public ParamContainer DivFloatFloat(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("fdiv", left, right);
    }

    public ParamContainer OrBoolBool(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("or", left, right);
    }

    public ParamContainer AndBoolBool(ParamContainer left, ParamContainer right) {
        return ThreeOperantsInstruction("and", left, right);
    }

    public ParamContainer ThreeOperantsInstruction(String inst, ParamContainer left, ParamContainer right) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("%s %s, %s", inst, left, right.GetValueAccessor());
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(left, scopeVar);
    }

    public ParamContainer IntEQ(ParamContainer left, ParamContainer right) {
        return IntComparator("eq", left, right);
    }

    public ParamContainer IntNE(ParamContainer left, ParamContainer right) {
        return IntComparator("ne", left, right);
    }

    public ParamContainer IntLT(ParamContainer left, ParamContainer right) {
        return IntComparator("slt", left, right);
    }

    public ParamContainer IntLE(ParamContainer left, ParamContainer right) {
        return IntComparator("sle", left, right);
    }

    public ParamContainer IntGT(ParamContainer left, ParamContainer right) {
        return IntComparator("sgt", left, right);
    }

    public ParamContainer IntGE(ParamContainer left, ParamContainer right) {
        return IntComparator("sge", left, right);
    }

    public ParamContainer IntComparator(String cond, ParamContainer left, ParamContainer right) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("icmp %s %s, %s", cond, left, right.GetValueAccessor());
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.BOOL, scopeVar);
    }

    public ParamContainer FloatEQ(ParamContainer left, ParamContainer right) {
        return FloatComparator("oeq", left, right);
    }

    public ParamContainer FloatNE(ParamContainer left, ParamContainer right) {
        return FloatComparator("one", left, right);
    }

    public ParamContainer FloatLT(ParamContainer left, ParamContainer right) {
        return FloatComparator("olt", left, right);
    }

    public ParamContainer FloatLE(ParamContainer left, ParamContainer right) {
        return FloatComparator("ole", left, right);
    }

    public ParamContainer FloatGT(ParamContainer left, ParamContainer right) {
        return FloatComparator("ogt", left, right);
    }

    public ParamContainer FloatGE(ParamContainer left, ParamContainer right) {
        return FloatComparator("oge", left, right);
    }

    public ParamContainer FloatComparator(String cond, ParamContainer left, ParamContainer right) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fcmp %s %s, %s", cond, left, right.GetValueAccessor());
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.BOOL, scopeVar);
    }

    // Returns a pointer to the requested memory with size of the given type
    public ParamContainer AllocateMemory(TypeWrapper type) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("alloca %s", type); // TODO: alignment
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        TypeWrapper MemoryPointer = new TypeWrapper_Pointer(type);
        return new ParamContainer(MemoryPointer, scopeVar);
    }

    public void StoreInVariable(ParamContainer varAccess, ParamContainer value) {
        String varParam = varAccess.CreateParameterString();
        String valueParam = value.CreateParameterString();
        String exp = String.format("store %s, %s", valueParam, varParam); // TODO: alignment
        GetScopeSnippetAsDef().AddStatement(exp);
    }

    public ParamContainer LoadFromVariable(ParamContainer variable) {
        TypeWrapper pointedType = variable.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("load %s, %s", pointedType, variable); // TODO: alignment
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(pointedType, scopeVar);
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer array, ParamContainer index) {
        return CreateArrayElementPtr(array, index.GetValueAccessor());
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer array, ValueWrapper index) {
        TypeWrapper arrType = array.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("getelementptr inbounds %s, %s, i64 0, i64 %s", arrType, array, index); // TODO: alignment
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        TypeWrapper elementType = array.GetRootType().GetChild().GetChild();

        return new ParamContainer(new TypeWrapper_Pointer(elementType), scopeVar);
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer array, int index) {
        TypeWrapper arrType = array.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("getelementptr inbounds %s, %s, i64 0, i64 %d", arrType, array, index); // TODO: alignment
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        TypeWrapper elementType = array.GetRootType().GetChild().GetChild();

        return new ParamContainer(new TypeWrapper_Pointer(elementType), scopeVar);
    }

    public void CreateBranch(ParamContainer condition, ParamContainer positive, ParamContainer negative) {
        CodeSnippet_Args snippet = new CodeSnippet_Args("br %s, %s, %s", condition, positive, negative);
        GetScopeSnippetAsDef().AddStatement(snippet);
    }

    public CodeSnippet_Switch CreateSwitch(ParamContainer expression, ParamContainer defJump) {
        CodeSnippet_Switch switchStmt = new CodeSnippet_Switch(new CodeSnippet_PlainObject(expression), new CodeSnippet_PlainObject(defJump));
        GetScopeSnippetAsDef().AddStatement(switchStmt);
        return switchStmt;
    }

    public void AddLabelSwitch(CodeSnippet_Switch stmt, ParamContainer num, ParamContainer jump) {
        stmt.AddParameter(new CodeSnippet_Args("%s, %s ", num, jump));
    }

    public void CreateJump(ParamContainer label, boolean endsBlock) {
        CodeSnippet_Args snippet = new CodeSnippet_Args("br %s", label);
        if (endsBlock) {
            GetScopeSnippetAsDef().AddStatement(snippet);
        } else {
            GetScopeSnippetAsDef().AddStatementIncrementCounter(snippet);
        }
    }

    public void CreateJump(ParamContainer label) {
        CreateJump(label, true);
    }

    public ValueWrapper CreateLabel() {
        return GetScopeSnippetAsDef().AddLabel();
    }

    public List<String> Serialize() {
        List<String> OutLines = new ArrayList<>();

        for (CodeSnippet_Struct struct : m_Structs) {
            OutLines.addAll(struct.WriteLines());
        }

        OutLines.add("");

        for (CodeSnippet_Base constant : m_Constants) {
            OutLines.addAll(constant.WriteLines());
        }

        OutLines.add("");

        for (CodeSnippet_FuncDecl decl : m_FunctionDeclarations) {
            OutLines.addAll(decl.WriteLines());
        }

        OutLines.add("");

        for (CodeSnippet_FuncDef def : m_FunctionDefinitions) {
            OutLines.addAll(def.WriteLines());
            OutLines.add("");
        }

        return OutLines;
    }
}
