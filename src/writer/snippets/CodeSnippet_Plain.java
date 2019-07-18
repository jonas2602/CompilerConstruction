package writer.snippets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeSnippet_Plain extends CodeSnippet_Base {
    private String m_Content;

    public CodeSnippet_Plain(String content) {
        m_Content = content;
    }

    @Override
    public List<String> WriteLines() {
        return Arrays.asList(Write());
    }

    @Override
    public String Write() {
        return m_Content;
    }
}
