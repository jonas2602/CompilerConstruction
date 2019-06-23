package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Parameter extends CodeSnippet_Base {
    private CodeSnippet_Type m_Type;
    private CodeSnippet_Base m_Data;

    public CodeSnippet_Parameter(CodeSnippet_Base InType, CodeSnippet_Base InData) {
        m_Type = (CodeSnippet_Type) InType;
        m_Data = InData;
    }

    public CodeSnippet_Parameter(CodeSnippet_Type.EType InType, CodeSnippet_Base InData) {
        m_Type = new CodeSnippet_Type(InType);
        m_Data = InData;
    }

    public CodeSnippet_Type GetTypeSnippet() {
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
