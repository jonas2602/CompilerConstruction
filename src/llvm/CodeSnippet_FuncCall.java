package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_FuncCall extends CodeSnippet_Base {
    private String m_FunctionName;
    private CodeSnippet_Base m_ReturnType;
    private CodeSnippet_Base m_TypeExtension;
    private List<CodeSnippet_Base> m_Parameters;

    public CodeSnippet_FuncCall(String InFunctionName, CodeSnippet_Base InReturnType) {
        this(InFunctionName, InReturnType, new ArrayList<>());
    }

    public CodeSnippet_FuncCall(String InFunctionName, CodeSnippet_Base InReturnType, List<CodeSnippet_Base> InParams) {
        this(InFunctionName, InReturnType, InParams, null);
    }

    public CodeSnippet_FuncCall(String InFunctionName, CodeSnippet_Base InReturnType, CodeSnippet_Base InTypeExtension) {
        this(InFunctionName, InReturnType, new ArrayList<>(), InTypeExtension);
    }

    public CodeSnippet_FuncCall(String InFunctionName, CodeSnippet_Base InReturnType, List<CodeSnippet_Base> InParams, CodeSnippet_Base InTypeExtension) {
        m_FunctionName = InFunctionName;
        m_ReturnType = InReturnType;
        m_Parameters = InParams;
        m_TypeExtension = InTypeExtension;
    }

    public void AddParameter(CodeSnippet_Base InParam) {
        m_Parameters.add(InParam);
    }

    public void AddParameter(String InParam) {
        m_Parameters.add(new CodeSnippet_Plain(InParam));
    }

    // TODO: Constructor that initializes from FunctionDecl snippet

    @Override
    public List<String> WriteLines() {
        return new ArrayList<>() {{
            add(Write());
        }};
    }

    @Override
    public String Write() {
        if (m_TypeExtension != null) {
            return String.format("call %s %s @%s(%s)",
                    m_ReturnType.Write(),
                    m_TypeExtension.Write(),
                    m_FunctionName,
                    CodeSnippetHelper.MakeSeperatedSnippets(", ", m_Parameters));
        } else {
            return String.format("call %s @%s(%s)",
                    m_ReturnType.Write(),
                    m_FunctionName,
                    CodeSnippetHelper.MakeSeperatedSnippets(", ", m_Parameters));
        }
    }
}
