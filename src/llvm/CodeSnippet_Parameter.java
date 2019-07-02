package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Parameter extends CodeSnippet_Base {
    private CodeSnippet_Base m_Type;
    private CodeSnippet_Base m_Data;

    public CodeSnippet_Parameter(CodeSnippet_Base type, CodeSnippet_Base data) {
        m_Type = type;
        m_Data = data;
    }

    public CodeSnippet_Parameter(CodeSnippet_Type.EType type, CodeSnippet_Base data) {
        m_Type = new CodeSnippet_Type(type);
        m_Data = data;
    }

    public CodeSnippet_Base GetTypeSnippet() {
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
        return String.format("%s %s", m_Type.Write(), m_Data.Write());
    }
}
