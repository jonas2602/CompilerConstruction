package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_FuncDecl extends CodeSnippet_Base {
    protected String m_Name;
    protected List<CodeSnippet_Base> m_Params = new ArrayList<>();
    protected CodeSnippet_Base m_ReturnType;

    protected int m_StorageCounter = 0;

    public CodeSnippet_FuncDecl(String InName, CodeSnippet_Base InReturnType) {
        m_Name = InName;
        m_ReturnType = InReturnType;
    }

    public int AddParameter(CodeSnippet_Base InParam) {
        m_Params.add(InParam);
        return m_StorageCounter++;
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
