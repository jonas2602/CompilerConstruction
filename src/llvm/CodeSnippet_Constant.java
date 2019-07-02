package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Constant extends CodeSnippet_Base {
    private CodeSnippet_Base m_Name;
    private CodeSnippet_Base m_Type;
    private CodeSnippet_Plain m_Data;

    public CodeSnippet_Constant(CodeSnippet_Base name, CodeSnippet_Base type, CodeSnippet_Plain data) {
        m_Name = name;
        m_Type = type;
        m_Data = data;
    }

    public CodeSnippet_Base GetName() {
        return m_Name;
    }

    public CodeSnippet_Base GetType() {
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
        return String.format("%s = constant %s %s", m_Name.Write(), m_Type.Write(), m_Data.Write());
    }
}
