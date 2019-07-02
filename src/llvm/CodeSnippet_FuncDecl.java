package llvm;

import writer.TypeWrapper;
import writer.VariableWrapper;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_FuncDecl extends CodeSnippet_Base implements ScopeInterface {
    protected String m_Name;
    protected List<CodeSnippet_Base> m_Params;
    protected CodeSnippet_Base m_ReturnType;

    protected int m_ParameterCounter = 0;

    public CodeSnippet_FuncDecl(String name, CodeSnippet_Base returnType) {
        m_Name = name;
        m_ReturnType = returnType;
        m_Params = new ArrayList<>();
    }

    public VariableWrapper AddParameter(CodeSnippet_Base param) {
        m_Params.add(param);
        return VariableWrapper.SCOPEVAR(m_ParameterCounter++);
    }

    public VariableWrapper AddParameter(String InParam) {
        return AddParameter(new CodeSnippet_Plain(InParam));
    }

    @Override
    public List<String> WriteLines() {
        return new ArrayList<>() {{
            add(Write());
        }};
    }

    @Override
    public String Write() {
        return String.format(
                "declare %s @%s(%s)",
                m_ReturnType.Write(),
                m_Name,
                MakeParameterString()
        );
    }

    String MakeParameterString() {
        String OutString = "";

        for (int i = 0; i < m_Params.size(); i++) {
            if (i > 0) {
                OutString += ", ";
            }
            OutString += m_Params.get(i).Write();
        }

        return OutString;
    }
}
