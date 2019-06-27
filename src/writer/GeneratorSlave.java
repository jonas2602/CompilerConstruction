package writer;

import llvm.*;

import java.util.ArrayList;
import java.util.List;

public class GeneratorSlave {
    private List<CodeSnippet_Constant> m_Constants = new ArrayList<>();
    private int m_ConstantCounter = 0;

    private List<CodeSnippet_FuncDef> m_FunctionDefinitions = new ArrayList<>();
    private List<CodeSnippet_FuncDecl> m_FunctionDeclarations = new ArrayList<>();

    private List<CodeSnippet_FuncDef> m_ScopeStack = new ArrayList<>();
    private CodeSnippet_FuncDef m_ActiveScope;

    public CodeSnippet_FuncDef GetScopeSnippet() {
        return m_ActiveScope;
    }

    public CodeSnippet_Constant CreateStringConstant(String InContent) {
        int size = InContent.length() + 1;
        String constName = String.format("@.str.%d", m_ConstantCounter++);
        CodeSnippet_Type constType = new CodeSnippet_TypeArray(CodeSnippet_Type.EType.CHAR, size);
        CodeSnippet_Plain constData = new CodeSnippet_Plain(String.format("c\"%s\\00\"", InContent));

        CodeSnippet_Constant snippet = new CodeSnippet_Constant(constName, constType, constData);
        m_Constants.add(snippet);

        return snippet;
    }

    public TypeContainer CreateStringConstantNew(String InContent) {
        // Convert \n, \t, ... to hex code
        String outString = "";
        for (int c : InContent.chars().toArray()) {
            if (Character.isLetterOrDigit(c) || c == '%') {
                outString += (char) c;
            } else {
                String hexValue = Integer.toHexString(c);
                if (hexValue.length() < 2) {
                    hexValue = "0" + hexValue;
                }
                outString += "\\" + hexValue.toUpperCase();
            }
        }

        TypeWrapper stringWrapper = TypeManager.STRING(InContent.length() + 1);
        String constName = String.format("@.str.%d", m_ConstantCounter++);
        CodeSnippet_Plain constType = new CodeSnippet_Plain(stringWrapper.GetTypeName());
        CodeSnippet_Plain constData = new CodeSnippet_Plain(String.format("c\"%s\\00\"", outString));

        CodeSnippet_Constant snippet = new CodeSnippet_Constant(constName, constType, constData);
        m_Constants.add(snippet);

        return new TypeContainer(this, new TypeWrapper_Pointer(stringWrapper), constName);
    }

    public CodeSnippet_Plain CreateStringReference(CodeSnippet_Constant InStringSource) {
        String sourceSize = InStringSource.GetType().Write();
        String sourceName = InStringSource.GetName();
        String plainText = String.format("getelementptr inbounds (%s, %s* %s, i64 0, i64 0)", sourceSize, sourceSize, sourceName);
        return new CodeSnippet_Plain(plainText);
    }

    public CodeSnippet_Parameter CreateStringParameter(CodeSnippet_Constant InStringSource) {
        return new CodeSnippet_Parameter(CodeSnippet_Type.EType.STRING, CreateStringReference(InStringSource));
    }

    public CodeSnippet_FuncCall CreatePrintfCall(CodeSnippet_Base InSourceParam, List<CodeSnippet_Base> InFiller) {
        InFiller.add(0, InSourceParam);
        CodeSnippet_FuncCall call = new CodeSnippet_FuncCall("printf", new CodeSnippet_Type(CodeSnippet_Type.EType.INT), InFiller, new CodeSnippet_Plain("(i8*, ...)"));
        GetScopeSnippet().AddStatementWithStorage(call);

        return call;
    }

    public CodeSnippet_Plain CreateReturnStmt(CodeSnippet_Type.EType InType, String InData) {
        return CreateReturnStmt(InType.label(), InData);
    }

    public CodeSnippet_Plain CreateReturnStmt(CodeSnippet_Base InType, String InData) {
        return CreateReturnStmt(InType.Write(), InData);
    }

    public CodeSnippet_Plain CreateReturnStmt(String InType, String InData) {
        return new CodeSnippet_Plain(String.format("ret %s %s", InType, InData));
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

    public TypeContainer CreateFunctionParameter(TypeWrapper InType) {
        int scopeIndex = GetScopeSnippet().AddParameter(InType.GetTypeName());
        return new TypeContainer(this, InType, "%" + scopeIndex);
    }

    public void EnterScope(CodeSnippet_FuncDef InScopeObject) {
        m_ScopeStack.add(InScopeObject);
        m_ActiveScope = InScopeObject;
    }

    public void ExitScope() {
        m_ScopeStack.remove(m_ActiveScope);
        if (m_ScopeStack.size() > 0) {
            m_ActiveScope = m_ScopeStack.get(m_ScopeStack.size() - 1);
        }
    }

    public CodeSnippet_FuncDecl CreateFunctionDeclaration(String InName, CodeSnippet_Type InReturnType) {
        CodeSnippet_FuncDecl decl = new CodeSnippet_FuncDecl(InName, InReturnType);
        m_FunctionDeclarations.add(decl);

        return decl;
    }

    public int CastFloatToInt(String Source) {
        String exp = String.format("fptosi float %s to i32", Source);
        return GetScopeSnippet().AddStatementWithStorage(exp);
    }

    public int CastIntToFloat(String Source) {
        String exp = String.format("sitofp i32 %s to float", Source);
        return GetScopeSnippet().AddStatementWithStorage(exp);
    }

    public int ExtendFloatToDouble(String Source) {
        String exp = String.format("fpext float %s to double", Source);
        return GetScopeSnippet().AddStatementWithStorage(exp);
    }

    public TypeContainer ExtendFloatToDouble(TypeContainer InSource) {
        String exp = String.format("fpext float %s to double", InSource.GetValueAccessor());
        int scopeIndex = GetScopeSnippet().AddStatementWithStorage(exp);
        return new TypeContainer(this, TypeWrapper_Primitive.DOUBLE, "%" + scopeIndex);
    }

    public int TruncateIntToChar(String Source) {
        String exp = String.format("trunc i32 %s to i8", Source);
        return GetScopeSnippet().AddStatementWithStorage(exp);
    }

    public int ExtendToInt(String SourceType, String SourceData) {
        String exp = String.format("sext %s %s to i32", SourceType, SourceData);
        return GetScopeSnippet().AddStatementWithStorage(exp);
    }

    public int AddIntInt(String InLeft, String InRight) {
        return ThreeOperantsIntInstruction("add", InLeft, InRight);
    }

    public int AddFloatInt(String InLeft, String InRight) {
        int cast = CastIntToFloat(InRight);
        return AddFloatFloat(InLeft, "%" + cast);
    }

    public int AddFloatFloat(String InLeft, String InRight) {
        return ThreeOperantsFloatInstruction("fadd", InLeft, InRight);
    }

    public int SubIntInt(String InLeft, String InRight) {
        return ThreeOperantsIntInstruction("sub", InLeft, InRight);
    }

    public int SubFloatFloat(String InLeft, String InRight) {
        return ThreeOperantsFloatInstruction("fsub", InLeft, InRight);
    }

    public int MulIntInt(String InLeft, String InRight) {
        return ThreeOperantsIntInstruction("mul", InLeft, InRight);
    }

    public int MulFloatFloat(String InLeft, String InRight) {
        return ThreeOperantsFloatInstruction("fmul", InLeft, InRight);
    }

    public int DivIntInt(String InLeft, String InRight) {
        return ThreeOperantsIntInstruction("sdiv", InLeft, InRight);
    }

    public int ModIntInt(String InLeft, String InRight) {
        return ThreeOperantsIntInstruction("srem", InLeft, InRight);
    }

    public int DivFloatFloat(String InLeft, String InRight) {
        return ThreeOperantsFloatInstruction("fdiv", InLeft, InRight);
    }

    public int ThreeOperantsIntInstruction(String inst, String InLeft, String InRight) {
        return ThreeOperantsInstruction("i32", inst, InLeft, InRight);
    }

    public int ThreeOperantsFloatInstruction(String inst, String InLeft, String InRight) {
        return ThreeOperantsInstruction("float", inst, InLeft, InRight);
    }

    public int ThreeOperantsInstruction(String type, String inst, String InLeft, String InRight) {
        String exp = String.format("%s %s %s, %s", inst, type, InLeft, InRight);
        return GetScopeSnippet().AddStatementWithStorage(exp);
    }

    // Returns a pointer to the requested memory with size of the given type
    public TypeContainer AllocateMemory(TypeWrapper InType) {
        String exp = String.format("alloca %s", InType.GetTypeName()); // TODO: alignment
        int ScopeIndex = GetScopeSnippet().AddStatementWithStorage(exp);
        TypeWrapper MemoryPointer = new TypeWrapper_Pointer(InType);
        return new TypeContainer(this, MemoryPointer, "%" + ScopeIndex);
    }

    public void StoreInVariable(TypeContainer InVarAccess, TypeContainer InValue) {
        String varParam = InVarAccess.CreateParameterString();
        String valueParam = InValue.CreateParameterString();
        String exp = String.format("store %s, %s", valueParam, varParam); // TODO: alignment
        GetScopeSnippet().AddStatement(exp);
    }

    public TypeContainer LoadFromVariable(TypeContainer InVariable) {
        TypeWrapper pointedType = InVariable.GetRootType().GetChild();
        String resultTypeName = pointedType.GetTypeName();
        String varParam = InVariable.CreateParameterString();
        String exp = String.format("load %s, %s", resultTypeName, varParam); // TODO: alignment
        int scopeIndex = GetScopeSnippet().AddStatementWithStorage(exp);
        return new TypeContainer(this, pointedType, "%" + scopeIndex);
    }

    public TypeContainer CreateArrayElementPtr(TypeContainer InArray, TypeContainer InIndex) {
        return CreateArrayElementPtr(InArray, InIndex.GetValueAccessor());
    }

    public TypeContainer CreateArrayElementPtr(TypeContainer InArray, String InIndex) {
        String arrTypeName = InArray.GetRootType().GetChild().GetTypeName();
        String arrParam = InArray.CreateParameterString();
        String exp = String.format("getelementptr inbounds %s, %s, i64 0, i64 %s", arrTypeName, arrParam, InIndex); // TODO: alignment
        int scopeIndex = GetScopeSnippet().AddStatementWithStorage(exp);
        TypeWrapper elementType = InArray.GetRootType().GetChild().GetChild();

        return new TypeContainer(this, new TypeWrapper_Pointer(elementType), "%" + scopeIndex);
    }

    public int AllocateInt() {
        String exp = String.format("alloca i32"); // TODO: alignment
        return GetScopeSnippet().AddStatementWithStorage(exp);
    }

    public void StoreInt(String InValue, int InIndex) {
        String exp = String.format("store i32 %s, i32* %%%d", InValue, InIndex);
        GetScopeSnippet().AddStatement(exp);
    }

    public List<String> Serialize() {
        List<String> OutLines = new ArrayList<>();

        for (CodeSnippet_Constant constant : m_Constants) {
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
