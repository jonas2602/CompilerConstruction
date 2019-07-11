package writer;

import writer.natives.NativeFunction;
import writer.natives.NativeFunction_memcpy;
import writer.snippets.*;
import writer.wrappers.*;

import java.util.*;

public class GeneratorSlave {
    private List<CodeSnippet_Struct> m_Structs;

    private List<CodeSnippet_Base> m_Constants;
    private List<CodeSnippet_Base> m_GlobalVariables;
    private int m_ConstantCounter;
    private int m_GlobalsCounter;

    private HashMap<String, ParamContainer> m_StringConstants;

    private HashSet<Class> m_NativeMap;
    private List<CodeSnippet_FuncDef> m_FunctionDefinitions;
    private List<CodeSnippet_FuncDecl> m_FunctionDeclarations;

    private ArrayDeque<ScopeInterface> m_ScopeStack;
    private ScopeInterface m_ActiveScope;

    public GeneratorSlave() {
        m_Structs = new ArrayList<>();
        m_Constants = new ArrayList<>();
        m_GlobalVariables = new ArrayList<>();
        m_StringConstants = new HashMap<>();

        m_ConstantCounter = 0;
        m_GlobalsCounter = 0;

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
        ParamContainer cached = m_StringConstants.get(content);
        if (cached != null) {
            return cached;
        }

        // Convert \n, \t, ... to hex code
        StringBuilder builder = new StringBuilder();
        for (char c : content.toCharArray()) {
            // TODO: when to convert to hex?
            if (Character.isISOControl(c)) {
                String hexValue = Integer.toHexString(c);
                if (hexValue.length() < 2) {
                    hexValue = "0" + hexValue;
                }
                builder.append("\\" + hexValue.toUpperCase());
            } else {
                builder.append(c);
            }
        }

        TypeWrapper stringType = TypeManager.STRING(content.length() + 1);
        ValueWrapper_Variable var = ValueWrapper_Variable.CONSTSTRING(m_ConstantCounter++);
        // String constName = String.format("@.str.%d", m_ConstantCounter++);
        // CodeSnippet_Plain constType = new CodeSnippet_Plain(stringWrapper.CreateTypeName());

        CodeSnippet_Base snippet = new CodeSnippet_Args("%s = constant %s c\"%s\\00\"", var, stringType, builder.toString());

        m_Constants.add(snippet);

        cached = new ParamContainer(new TypeWrapper_Pointer(stringType), var);
        m_StringConstants.put(content, cached);
        return cached;
    }

    public ParamContainer CreateFunctionCall(String name, TypeWrapper returnType, boolean bEnterScope) {
        CodeSnippet_FuncCall call = new CodeSnippet_FuncCall(name, returnType);

        // Add Call to current Function Scope
        ParamContainer outContainer = null;
        if (TypeWrapper_Other.VOID.CompareType(returnType)) {
            GetScopeSnippetAsDef().AddStatement(call);
            outContainer = ParamContainer.VOIDCONTAINER();
        } else {
            ValueWrapper_Variable ScopeIndex = GetScopeSnippetAsDef().AddStatementWithStorage(call);
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
        ValueWrapper_Variable scopeVariable = GetScopeSnippet().AddParameter(type.CreateTypeName());
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
        // target and source are both pointer -> need to compare the sizes of the pointed types
        int sourceSize = source.GetRootType().GetChild().GetTypeByteSize(8);
        int targetSize = target.GetRootType().GetChild().GetTypeByteSize(8);
        int blockSize = Math.min(sourceSize, targetSize);

        // convert to char* if given as other types
        if (!TypeManager.CHARPTR().CompareType(source.GetRootType())) {
            // source = CreateArrayElementPtr(source, 0);
            source = BitCast(source, TypeManager.CHARPTR());
        }
        if (!TypeManager.CHARPTR().CompareType(target.GetRootType())) {
            target = BitCast(target, TypeManager.CHARPTR());
        }

        CreateNativeCall(new NativeFunction_memcpy(target, source, blockSize, 8));

        // CodeSnippet_Args stmt = new CodeSnippet_Args("call void @llvm.memcpy.p0i8.p0i8.i64(%s, %s, i64 %s, i1 false)", target, source, blockSize);
        // GetScopeSnippetAsDef().AddStatement(stmt);
    }

    public ParamContainer BitCast(ParamContainer source, TypeWrapper targetType) {
        return TwoParameterInstructionConstantType("bitcast", source, targetType);
    }

    public ParamContainer CastFloatToInt(ParamContainer source) {
        return TwoParameterInstructionConstantType("fptosi", source, TypeWrapper_Primitive.INT);
    }

    public ParamContainer CastIntToFloat(ParamContainer source) {
        return TwoParameterInstructionConstantType("sitofp", source, TypeWrapper_Primitive.FLOAT);
    }

    public ParamContainer TruncToInt(ParamContainer source) {
        return TwoParameterInstructionConstantType("trunc", source, TypeWrapper_Primitive.INT);
    }

    public ParamContainer TruncToChar(ParamContainer source) {
        return TwoParameterInstructionConstantType("trunc", source, TypeWrapper_Primitive.CHAR);
    }

    public ParamContainer TruncToBool(ParamContainer source) {
        return TwoParameterInstructionConstantType("trunc", source, TypeWrapper_Primitive.BOOL);
    }

    public ParamContainer ExtendToInt(ParamContainer source) {
        return TwoParameterInstructionConstantType("zext", source, TypeWrapper_Primitive.INT);
    }

    public ParamContainer ExtendToLong(ParamContainer source) {
        return TwoParameterInstructionConstantType("zext", source, TypeWrapper_Primitive.LONG);
    }

    public ParamContainer ExtendToChar(ParamContainer source) {
        return TwoParameterInstructionConstantType("zext", source, TypeWrapper_Primitive.CHAR);
    }

    public ParamContainer ExtendToBool(ParamContainer source) {
        return TwoParameterInstructionConstantType("zext", source, TypeWrapper_Primitive.BOOL);
    }

    public ParamContainer ExtendFloatToDouble(ParamContainer source) {
        return TwoParameterInstructionConstantType("fpext", source, TypeWrapper_Primitive.DOUBLE);
    }

    public ParamContainer TwoParameterInstructionConstantType(String instruction, ParamContainer source, TypeWrapper type) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("%s %s to %s", instruction, source, type.CreateTypeName());
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(type, scopeVar);
    }

    public ValueWrapper_Variable ExtendToInt(String sourceType, String sourceData) {
        String exp = String.format("sext %s %s to i32", sourceType, sourceData);
        return GetScopeSnippetAsDef().AddStatementWithStorage(exp);
    }

    public ParamContainer NegateBool(ParamContainer param) {
        return TwoParameterInstructionConstant("xor", param, "1");
    }

    public ParamContainer IncInt(ParamContainer param) {
        return TwoParameterInstructionConstant("add", param, "1");
    }

    public ParamContainer DecInt(ParamContainer param) {
        return TwoParameterInstructionConstant("sub", param, "1");
    }

    public ParamContainer IncFloat(ParamContainer param) {
        return TwoParameterInstructionConstant("fadd", param, "1.0");
    }

    public ParamContainer DecFloat(ParamContainer param) {
        return TwoParameterInstructionConstant("fsub", param, "1.0");
    }


    public ParamContainer NegateInt(ParamContainer param) {
        return TwoParameterInstructionConstant("mul", param, "-1");
    }

    public ParamContainer NegateFloat(ParamContainer param) {
        return TwoParameterInstructionConstant("fmul", param, "-1.0");
    }

    public ParamContainer TwoParameterInstructionConstant(String instruction, ParamContainer param, String constant) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("%s %s, %s", instruction, param, constant);
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(param, scopeVar);
    }

    public ParamContainer AddIntInt(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("add", left, right);
    }

    public ParamContainer AddFloatFloat(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("fadd", left, right);
    }

    public ParamContainer SubIntInt(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("sub", left, right);
    }

    public ParamContainer SubFloatFloat(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("fsub", left, right);
    }

    public ParamContainer MulIntInt(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("mul", left, right);
    }

    public ParamContainer MulFloatFloat(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("fmul", left, right);
    }

    public ParamContainer DivIntInt(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("sdiv", left, right);
    }

    public ParamContainer ModIntInt(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("srem", left, right);
    }

    public ParamContainer DivFloatFloat(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("fdiv", left, right);
    }

    public ParamContainer OrBoolBool(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("or", left, right);
    }

    public ParamContainer AndBoolBool(ParamContainer left, ParamContainer right) {
        return TwoParametersInstruction("and", left, right);
    }

    public ParamContainer TwoParametersInstruction(String instruction, ParamContainer left, ParamContainer right) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("%s %s, %s", instruction, left, right.GetValueAccessor());
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
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
        TypeWrapper compType = TypeManager.IsNull(left) ? right.GetRootType() : left.GetRootType();
        CodeSnippet_Args stmt = new CodeSnippet_Args("icmp %s %s %s, %s", cond, compType, left.GetValueAccessor(), right.GetValueAccessor());
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
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
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.BOOL, scopeVar);
    }

    // Returns a pointer to the requested memory with size of the given type
    public ParamContainer AllocateMemory(TypeWrapper type) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("alloca %s", type); // TODO: alignment
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        TypeWrapper MemoryPointer = new TypeWrapper_Pointer(type);
        return new ParamContainer(MemoryPointer, scopeVar);
    }

    public ParamContainer AllocateGlobalMemory(TypeWrapper type, ParamContainer defValue) {
        ValueWrapper_Variable var = ValueWrapper_Variable.GLOBALVAR(m_GlobalsCounter++);
        CodeSnippet_Args stmt = new CodeSnippet_Args("%s = dso_local global %s", var, defValue); // TODO: alignment
        m_GlobalVariables.add(stmt);

        return new ParamContainer(new TypeWrapper_Pointer(type), var);
    }

    public void StoreInVariable(ParamContainer varAccess, ParamContainer valueParam) {
        String varParam = varAccess.CreateParameterString();
        String value = valueParam.CreateDataString();
        String exp = String.format("store %s %s, %s", varAccess.GetRootType().GetChild(), value, varParam); // TODO: alignment
        GetScopeSnippetAsDef().AddStatement(exp);
    }

    public ParamContainer LoadFromVariable(ParamContainer variable) {
        TypeWrapper pointedType = variable.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("load %s, %s", pointedType, variable); // TODO: alignment
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(pointedType, scopeVar);
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer array, ParamContainer index) {
        return CreateArrayElementPtr(array, index.GetValueAccessor());
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer array, ValueWrapper index) {
        TypeWrapper arrType = array.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("getelementptr inbounds %s, %s, i32 0, i32 %s", arrType, array, index); // TODO: alignment
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        TypeWrapper elementType = arrType.GetChild(Integer.parseInt(index.CreateDataString()));

        return new ParamContainer(new TypeWrapper_Pointer(elementType), scopeVar);
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer array, int index) {
        return CreateArrayElementPtr(array, new ValueWrapper_Constant(index));
    }

    public ParamContainer CreatePtrArrayElementPtr(ParamContainer array, ParamContainer index) {
        TypeWrapper arrType = array.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("getelementptr inbounds %s, %s, %s", arrType, array, index); // TODO: alignment
        ValueWrapper_Variable scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);

        return new ParamContainer(array.GetRootType(), scopeVar);
    }

    public void CreateBranch(ParamContainer condition, ParamContainer positive, ParamContainer negative) {
        CodeSnippet_Args snippet = new CodeSnippet_Args("br %s, %s, %s", condition, positive, negative);
        GetScopeSnippetAsDef().AddStatement(snippet);
    }

    public CodeSnippet_Switch CreateSwitch(ParamContainer expression, ParamContainer defJump) {
        CodeSnippet_Switch switchStmt = new CodeSnippet_Switch(expression, defJump);
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
        List<String> outLines = new ArrayList<>();

        if (m_Structs.size() > 0) {
            for (CodeSnippet_Struct struct : m_Structs) {
                outLines.addAll(struct.WriteLines());
            }
            outLines.add("");
        }

        if (m_Constants.size() > 0) {
            for (CodeSnippet_Base constant : m_Constants) {
                outLines.addAll(constant.WriteLines());
            }
            outLines.add("");
        }

        if (m_GlobalVariables.size() > 0) {
            for (CodeSnippet_Base global : m_GlobalVariables) {
                outLines.addAll(global.WriteLines());
            }
            outLines.add("");
        }

        if (m_FunctionDeclarations.size() > 0) {
            for (CodeSnippet_FuncDecl decl : m_FunctionDeclarations) {
                outLines.addAll(decl.WriteLines());
            }
            outLines.add("");
        }

        if (m_FunctionDefinitions.size() > 0) {
            for (CodeSnippet_FuncDef def : m_FunctionDefinitions) {
                outLines.addAll(def.WriteLines());
                outLines.add("");
            }
        }

        return outLines;
    }
}
