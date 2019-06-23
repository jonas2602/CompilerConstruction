package writer;

import llvm.*;

import java.util.ArrayList;
import java.util.List;

public class GeneratorSlave {
    private List<CodeSnippet_Constant> m_Constants = new ArrayList<>();
    private int m_ConstantCounter = 0;

    private List<CodeSnippet_FuncDef> m_FunctionDefinitions = new ArrayList<>();
    private List<CodeSnippet_FuncDecl> m_FunctionDeclarations = new ArrayList<>();

    private List<ScopeInterface> m_ScopeStack = new ArrayList<>();
    private ScopeInterface m_ActiveScope;

    public ScopeInterface GetScopeSnippet() {
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

        return call;
    }

    public CodeSnippet_Plain CreateReturnStmt(CodeSnippet_Type.EType InType, String InData) {
        return new CodeSnippet_Plain(String.format("ret %s %s", InType.label(), InData));
    }

    public CodeSnippet_Plain CreateReturnStmt(CodeSnippet_Type InType, String InData) {
        return new CodeSnippet_Plain(String.format("ret %s %s", InType.Write(), InData));
    }

    public CodeSnippet_FuncDef CreateFunctionDefinition(String InName, CodeSnippet_Type InReturnType, boolean bEnterScope) {
        CodeSnippet_FuncDef def = new CodeSnippet_FuncDef(InName, InReturnType);
        m_FunctionDefinitions.add(def);
        if (bEnterScope) {
            EnterScope(def);
        }

        return def;
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

    public CodeSnippet_FuncDecl CreateFunctionDeclaration(String InName, CodeSnippet_Type InReturnType) {
        CodeSnippet_FuncDecl decl = new CodeSnippet_FuncDecl(InName, InReturnType);
        m_FunctionDeclarations.add(decl);

        return decl;
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
        }

        return OutLines;
    }
}
