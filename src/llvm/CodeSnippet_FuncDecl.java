package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_FuncDecl extends CodeSnippet_Base {
    protected String m_Name;
    protected List<CodeSnippet_Base> m_Params = new ArrayList<>();
    protected CodeSnippet_Type m_ReturnType;

    public CodeSnippet_FuncDecl(String InName, CodeSnippet_Type InReturnType) {
        m_Name = InName;
        m_ReturnType = InReturnType;
    }

    public void AddParameter(CodeSnippet_Base InParam) {
        m_Params.add(InParam);
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
