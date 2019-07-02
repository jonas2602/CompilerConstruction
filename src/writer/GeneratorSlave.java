package writer;

import com.sun.source.tree.Scope;
import llvm.*;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GeneratorSlave {
    private List<CodeSnippet_Base> m_Constants = new ArrayList<>();
    private int m_ConstantCounter = 0;

    private HashSet<Class> m_NativeMap = new HashSet<>();
    private List<CodeSnippet_FuncDef> m_FunctionDefinitions = new ArrayList<>();
    private List<CodeSnippet_FuncDecl> m_FunctionDeclarations = new ArrayList<>();

    private List<ScopeInterface> m_ScopeStack = new ArrayList<>();
    private ScopeInterface m_ActiveScope;

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

    // public CodeSnippet_Constant CreateStringConstant(String InContent) {
    //     int size = InContent.length() + 1;
    //     String constName = String.format("@.str.%d", m_ConstantCounter++);
    //     CodeSnippet_Type constType = new CodeSnippet_TypeArray(CodeSnippet_Type.EType.CHARPTR, size);
    //     CodeSnippet_Plain constData = new CodeSnippet_Plain(String.format("c\"%s\\00\"", InContent));
    //
    //     CodeSnippet_Constant snippet = new CodeSnippet_Constant(constName, constType, constData);
    //     m_Constants.add(snippet);
    //
    //     return snippet;
    // }

    public ParamContainer CreateNativeCall(NativeFunction InNative) {
        Class nativeClass = InNative.getClass();
        if (!m_NativeMap.contains(nativeClass)) {
            InNative.CreateFunctionDeclaration(this);
        }

        return InNative.CreateFunctionCall(this);
    }

    public ParamContainer CreateStringConstantNew(String InContent) {
        // Convert \n, \t, ... to hex code
        StringBuilder builder = new StringBuilder();
        for (char c : InContent.toCharArray()) {
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

        TypeWrapper stringType = TypeManager.STRING(InContent.length() + 1);
        VariableWrapper var = VariableWrapper.CONSTSTRING(m_ConstantCounter++);
        // String constName = String.format("@.str.%d", m_ConstantCounter++);
        // CodeSnippet_Plain constType = new CodeSnippet_Plain(stringWrapper.CreateTypeName());

        CodeSnippet_Base snippet = new CodeSnippet_Args("%s = constant %s c\"%s\\00\"", new ArrayList<>() {{
            add(var);
            add(stringType);
            add(builder.toString());
        }});

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

    public ParamContainer CreateFunctionCall(String InFunctionName, TypeWrapper InReturnType, boolean bEnterScope) {
        CodeSnippet_FuncCall call = new CodeSnippet_FuncCall(InFunctionName, InReturnType);

        // Add Call to current Function Scope
        ParamContainer outContainer = null;
        if (TypeWrapper_Other.VOID.CompareType(InReturnType)) {
            GetScopeSnippetAsDef().AddStatement(call);
            outContainer = ParamContainer.VOIDCONTAINER();
        } else {
            VariableWrapper ScopeIndex = GetScopeSnippetAsDef().AddStatementWithStorage(call);
            outContainer = new ParamContainer(InReturnType, ScopeIndex);
        }

        if (bEnterScope) {
            EnterScope(call);
        }

        return outContainer;
    }

    public void CreateFunctionCallParameter(ParamContainer InType) {
        GetScopeSnippet().AddParameter(InType.CreateParameterString());
    }

    public void CreateFunctionCallExtension(TypeWrapper InType) {
        GetScopeSnippetAsCall().AddTypeExtension(InType);
    }

    public CodeSnippet_Plain CreateReturnStmt(String InType, String InData) {
        return new CodeSnippet_Plain(String.format("ret %s %s", InType, InData));
    }

    public void CreateReturnStmt(ParamContainer InParam) {
        CodeSnippet_Base stmt = new CodeSnippet_Args("ret %s", InParam);
        GetScopeSnippetAsDef().AddStatement(stmt);
    }

    public CodeSnippet_FuncDef CreateFunctionDefinition(String InName, CodeSnippet_Base InReturnType, int InParameterCount, boolean bEnterScope) {
        CodeSnippet_FuncDef def = new CodeSnippet_FuncDef(InName, InReturnType, InParameterCount);
        def.AddStatement(new CodeSnippet_Plain("begin:"));
        m_FunctionDefinitions.add(def);
        if (bEnterScope) {
            EnterScope(def);
        }

        return def;
    }

    public ParamContainer CreateFunctionParameter(TypeWrapper InType) {
        VariableWrapper scopeVariable = GetScopeSnippet().AddParameter(InType.CreateTypeName());
        return new ParamContainer(InType, scopeVariable);
    }

    public void EnterScope(ScopeInterface InScopeObject) {
        m_ScopeStack.add(InScopeObject);
        m_ActiveScope = InScopeObject;
    }

    public void ExitScope() {
        m_ScopeStack.remove(m_ActiveScope);
        if (m_ScopeStack.size() > 0) {
            m_ActiveScope = m_ScopeStack.get(m_ScopeStack.size() - 1);
        }
    }

    public CodeSnippet_FuncDecl CreateFunctionDeclaration(String InName, CodeSnippet_Base InReturnType, boolean bEnterScope) {
        CodeSnippet_FuncDecl decl = new CodeSnippet_FuncDecl(InName, InReturnType);
        m_FunctionDeclarations.add(decl);
        if (bEnterScope) {
            EnterScope(decl);
        }

        return decl;
    }

    public void CopyMemory(ParamContainer InSource, ParamContainer InTarget) {
        int blockSize = Math.min(InSource.GetRootType().GetTypeByteSize(), InTarget.GetRootType().GetTypeByteSize());

        // convert to char* if given as other types
        if (!TypeManager.CHARPTR().CompareType(InSource.GetRootType())) {
            InSource = CreateArrayElementPtr(InSource, 0);
        }
        if (!TypeManager.CHARPTR().CompareType(InTarget.GetRootType())) {
            InTarget = BitCast(InTarget, TypeManager.CHARPTR());
        }

        CodeSnippet_Args stmt = new CodeSnippet_Args("call void @llvm.memcpy.p0i8.p0i8.i64(%s, %s, i64 %s, i1 false)", InTarget, InSource, blockSize);
        GetScopeSnippetAsDef().AddStatement(stmt);
    }

    public ParamContainer BitCast(ParamContainer InSource, TypeWrapper InTargetType) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("bitcast %s to %s", InSource, InTargetType);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(InTargetType, scopeVar);
    }


    public ParamContainer CastFloatToInt(ParamContainer InSource) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fptosi %s to i32", InSource);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.INT, scopeVar);
    }

    public ParamContainer CastIntToFloat(ParamContainer InSource) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("sitofp %s to float", InSource);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.FLOAT, scopeVar);
    }


    public ParamContainer ExtendFloatToDouble(ParamContainer InSource) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fpext %s to double", InSource);
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.DOUBLE, scopeVar);
    }

    public VariableWrapper TruncateIntToChar(String Source) {
        String exp = String.format("trunc i32 %s to i8", Source);
        return GetScopeSnippetAsDef().AddStatementWithStorage(exp);
    }

    public VariableWrapper ExtendToInt(String SourceType, String SourceData) {
        String exp = String.format("sext %s %s to i32", SourceType, SourceData);
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

    public ParamContainer AddIntInt(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("add", InLeft, InRight);
    }

    public ParamContainer AddFloatFloat(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("fadd", InLeft, InRight);
    }

    public ParamContainer SubIntInt(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("sub", InLeft, InRight);
    }

    public ParamContainer SubFloatFloat(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("fsub", InLeft, InRight);
    }

    public ParamContainer MulIntInt(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("mul", InLeft, InRight);
    }

    public ParamContainer MulFloatFloat(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("fmul", InLeft, InRight);
    }

    public ParamContainer DivIntInt(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("sdiv", InLeft, InRight);
    }

    public ParamContainer ModIntInt(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("srem", InLeft, InRight);
    }

    public ParamContainer DivFloatFloat(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("fdiv", InLeft, InRight);
    }

    public ParamContainer OrBoolBool(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("or", InLeft, InRight);
    }

    public ParamContainer AndBoolBool(ParamContainer InLeft, ParamContainer InRight) {
        return ThreeOperantsInstruction("and", InLeft, InRight);
    }

    public ParamContainer ThreeOperantsInstruction(String inst, ParamContainer InLeft, ParamContainer InRight) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("%s %s, %s", inst, InLeft, InRight.GetValueAccessor());
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(InLeft, scopeVar);
    }

    public ParamContainer IntEQ(ParamContainer InLeft, ParamContainer InRight) {
        return IntComparator("eq", InLeft, InRight);
    }

    public ParamContainer IntNE(ParamContainer InLeft, ParamContainer InRight) {
        return IntComparator("ne", InLeft, InRight);
    }

    public ParamContainer IntLT(ParamContainer InLeft, ParamContainer InRight) {
        return IntComparator("slt", InLeft, InRight);
    }

    public ParamContainer IntLE(ParamContainer InLeft, ParamContainer InRight) {
        return IntComparator("sle", InLeft, InRight);
    }

    public ParamContainer IntGT(ParamContainer InLeft, ParamContainer InRight) {
        return IntComparator("sgt", InLeft, InRight);
    }

    public ParamContainer IntGE(ParamContainer InLeft, ParamContainer InRight) {
        return IntComparator("sge", InLeft, InRight);
    }

    public ParamContainer IntComparator(String cond, ParamContainer InLeft, ParamContainer InRight) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("icmp %s %s, %s", cond, InLeft, InRight.GetValueAccessor());
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.BOOL, scopeVar);
    }

    public ParamContainer FloatEQ(ParamContainer InLeft, ParamContainer InRight) {
        return FloatComparator("oeq", InLeft, InRight);
    }

    public ParamContainer FloatNE(ParamContainer InLeft, ParamContainer InRight) {
        return FloatComparator("one", InLeft, InRight);
    }

    public ParamContainer FloatLT(ParamContainer InLeft, ParamContainer InRight) {
        return FloatComparator("olt", InLeft, InRight);
    }

    public ParamContainer FloatLE(ParamContainer InLeft, ParamContainer InRight) {
        return FloatComparator("ole", InLeft, InRight);
    }

    public ParamContainer FloatGT(ParamContainer InLeft, ParamContainer InRight) {
        return FloatComparator("ogt", InLeft, InRight);
    }

    public ParamContainer FloatGE(ParamContainer InLeft, ParamContainer InRight) {
        return FloatComparator("oge", InLeft, InRight);
    }

    public ParamContainer FloatComparator(String cond, ParamContainer InLeft, ParamContainer InRight) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("fcmp %s %s, %s", cond, InLeft, InRight.GetValueAccessor());
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(TypeWrapper_Primitive.BOOL, scopeVar);
    }

    // Returns a pointer to the requested memory with size of the given type
    public ParamContainer AllocateMemory(TypeWrapper InType) {
        CodeSnippet_Args stmt = new CodeSnippet_Args("alloca %s", InType); // TODO: alignment
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        TypeWrapper MemoryPointer = new TypeWrapper_Pointer(InType);
        return new ParamContainer(MemoryPointer, scopeVar);
    }

    public void StoreInVariable(ParamContainer InVarAccess, ParamContainer InValue) {
        String varParam = InVarAccess.CreateParameterString();
        String valueParam = InValue.CreateParameterString();
        String exp = String.format("store %s, %s", valueParam, varParam); // TODO: alignment
        GetScopeSnippetAsDef().AddStatement(exp);
    }

    public ParamContainer LoadFromVariable(ParamContainer InVariable) {
        TypeWrapper pointedType = InVariable.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("load %s, %s", pointedType, InVariable); // TODO: alignment
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        return new ParamContainer(pointedType, scopeVar);
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer InArray, ParamContainer InIndex) {
        return CreateArrayElementPtr(InArray, InIndex.GetValueAccessor());
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer InArray, ValueWrapper InIndex) {
        TypeWrapper arrType = InArray.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("getelementptr inbounds %s, %s, i64 0, i64 %s", arrType, InArray, InIndex); // TODO: alignment
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        TypeWrapper elementType = InArray.GetRootType().GetChild().GetChild();

        return new ParamContainer(new TypeWrapper_Pointer(elementType), scopeVar);
    }

    public ParamContainer CreateArrayElementPtr(ParamContainer InArray, int InIndex) {
        TypeWrapper arrType = InArray.GetRootType().GetChild();
        CodeSnippet_Args stmt = new CodeSnippet_Args("getelementptr inbounds %s, %s, i64 0, i64 %d", arrType, InArray, InIndex); // TODO: alignment
        VariableWrapper scopeVar = GetScopeSnippetAsDef().AddStatementWithStorage(stmt);
        TypeWrapper elementType = InArray.GetRootType().GetChild().GetChild();

        return new ParamContainer(new TypeWrapper_Pointer(elementType), scopeVar);
    }

    public void CreateBranch(ParamContainer InCondition, ParamContainer InPositive, ParamContainer InNegative) {
        CodeSnippet_Args snippet = new CodeSnippet_Args("br %s, %s, %s", new ArrayList<>() {{
            add(InCondition);
            add(InPositive);
            add(InNegative);
        }});

        GetScopeSnippetAsDef().AddStatement(snippet);
    }

    public void CreateJump(ParamContainer InLabel, boolean InEndsBlock) {
        CodeSnippet_Args snippet = new CodeSnippet_Args("br %s", InLabel);
        if (InEndsBlock) {
            GetScopeSnippetAsDef().AddStatement(snippet);
        } else {
            GetScopeSnippetAsDef().AddStatementIncrementCounter(snippet);
        }
    }

    public void CreateJump(ParamContainer InLabel) {
        CreateJump(InLabel, true);
    }

    public ValueWrapper CreateLabel() {
        return GetScopeSnippetAsDef().AddLabel();
    }

    public List<String> Serialize() {
        List<String> OutLines = new ArrayList<>();

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
