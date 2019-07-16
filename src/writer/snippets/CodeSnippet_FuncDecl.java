package writer.snippets;

import writer.wrappers.TypeWrapper;
import writer.wrappers.ValueWrapper_Variable;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_FuncDecl extends CodeSnippet_Base implements ScopeInterface {
    protected String m_Name;
    protected List<CodeSnippet_Base> m_Params;
    protected TypeWrapper m_ReturnType;

    protected int m_ParameterCounter = 0;

    public CodeSnippet_FuncDecl(String name, TypeWrapper returnType) {
        m_Name = name;
        m_ReturnType = returnType.MakeExtended();
        m_Params = new ArrayList<>();
    }

    public ValueWrapper_Variable AddParameter(CodeSnippet_Base param) {
        m_Params.add(param);
        return ValueWrapper_Variable.SCOPEVAR(m_ParameterCounter++);
    }

    public ValueWrapper_Variable AddParameter(String param) {
        return AddParameter(new CodeSnippet_Plain(param));
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
                m_ReturnType.CreateTypeName(),
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
