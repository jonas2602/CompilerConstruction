package writer.snippets;

import writer.wrappers.TypeWrapper;
import writer.wrappers.ValueWrapper_Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeSnippet_FuncCall extends CodeSnippet_Base implements ScopeInterface {
    private String m_FunctionName;
    private TypeWrapper m_ReturnType;
    private List<CodeSnippet_Base> m_TypeExtension;
    private List<CodeSnippet_Base> m_Parameters;

    public CodeSnippet_FuncCall(String functionName, TypeWrapper returnType) {
        m_FunctionName = functionName;
        m_ReturnType = returnType.MakeExtended();
        m_Parameters = new ArrayList<>();
        m_TypeExtension = new ArrayList<>();
    }

    @Override
    public ValueWrapper_Variable AddParameter(CodeSnippet_Base param) {
        m_Parameters.add(param);
        return null;
    }

    @Override
    public ValueWrapper_Variable AddParameter(String param) {
        m_Parameters.add(new CodeSnippet_Plain(param));
        return null;
    }

    public void AddTypeExtension(TypeWrapper type) {
        m_TypeExtension.add(new CodeSnippet_Plain(type.CreateTypeName()));
    }

    // TODO: Constructor that initializes from FunctionDecl snippet

    @Override
    public List<String> WriteLines() {
        return Arrays.asList(Write());
    }

    @Override
    public String Write() {
        if (m_TypeExtension.size() > 0) {
            return String.format("call %s (%s) @%s(%s)",
                    m_ReturnType.CreateTypeName(),
                    CodeSnippetHelper.MakeSeperatedSnippets(", ", m_TypeExtension),
                    m_FunctionName,
                    CodeSnippetHelper.MakeSeperatedSnippets(", ", m_Parameters));
        } else {
            return String.format("call %s @%s(%s)",
                    m_ReturnType.CreateTypeName(),
                    m_FunctionName,
                    CodeSnippetHelper.MakeSeperatedSnippets(", ", m_Parameters));
        }
    }
}
