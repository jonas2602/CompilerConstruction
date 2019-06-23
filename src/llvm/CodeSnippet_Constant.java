package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Constant extends CodeSnippet_Base {
    private String m_Name;
    private CodeSnippet_Type m_Type;
    private CodeSnippet_Plain m_Data;

    public CodeSnippet_Constant(String InName, CodeSnippet_Type InType, CodeSnippet_Plain InData) {
        m_Name = InName;
        m_Type = InType;
        m_Data = InData;
    }

    public String GetName() {
        return m_Name;
    }

    public CodeSnippet_Type GetType() {
        return m_Type;
    }

    @Override
    public List<String> WriteLines() {
        return new ArrayList<>() {{
            add(Write());
        }};
    }

    @Override
    public String Write() {
        return String.format("%s = constant %s %s", m_Name, m_Type.Write(), m_Data.Write());
    }
}
