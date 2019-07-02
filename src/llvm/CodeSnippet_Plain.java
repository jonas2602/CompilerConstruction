package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Plain extends CodeSnippet_Base {
    private String m_Content;

    public CodeSnippet_Plain(String content) {
        m_Content = content;
    }

    @Override
    public List<String> WriteLines() {
        return new ArrayList<>() {{
            add(Write());
        }};
    }

    @Override
    public String Write() {
        return m_Content;
    }
}
