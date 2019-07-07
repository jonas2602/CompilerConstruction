package writer.snippets;

import writer.wrappers.TypeWrapper;
import writer.wrappers.ValueWrapper_Variable;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_FuncCall extends CodeSnippet_Base implements ScopeInterface {
    private String m_FunctionName;
    private CodeSnippet_Base m_ReturnType;
    private List<CodeSnippet_Base> m_TypeExtension;
    private List<CodeSnippet_Base> m_Parameters;

    public CodeSnippet_FuncCall(String functionName, TypeWrapper returnType) {
        this(functionName, new CodeSnippet_Plain(returnType.CreateTypeName()));
    }

    public CodeSnippet_FuncCall(String functionName, CodeSnippet_Base returnType) {
        m_FunctionName = functionName;
        m_ReturnType = returnType;
        m_Parameters = new ArrayList<>();
        m_TypeExtension = new ArrayList<>();
    }
//
//    public CodeSnippet_FuncCall(String InFunctionName, CodeSnippet_Base InReturnType, List<CodeSnippet_Base> InParams) {
//        this(InFunctionName, InReturnType, InParams, null);
//    }
//
//    public CodeSnippet_FuncCall(String InFunctionName, CodeSnippet_Base InReturnType, CodeSnippet_Base InTypeExtension) {
//        this(InFunctionName, InReturnType, new ArrayList<>(), InTypeExtension);
//    }

//    public CodeSnippet_FuncCall(String InFunctionName, CodeSnippet_Base InReturnType, List<CodeSnippet_Base> InParams, List<CodeSnippet_Base> InTypeExtension) {
//        m_FunctionName = InFunctionName;
//        m_ReturnType = InReturnType;
//        m_Parameters = InParams;
//        m_TypeExtension = InTypeExtension;
//    }

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
        return new ArrayList<>() {{
            add(Write());
        }};
    }

    @Override
    public String Write() {
        if (m_TypeExtension.size() > 0) {
            return String.format("call %s (%s) @%s(%s)",
                    m_ReturnType.Write(),
                    CodeSnippetHelper.MakeSeperatedSnippets(", ", m_TypeExtension),
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
