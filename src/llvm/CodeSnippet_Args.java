package llvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeSnippet_Args extends CodeSnippet_Base {
    private String m_Format;
    private List<Object> m_Content;

    public CodeSnippet_Args(String format) {
        this(format, new ArrayList<>());
    }

    public CodeSnippet_Args(String format, Object param) {
        this(format, new ArrayList<>() {{
            add(param);
        }});
    }

    public CodeSnippet_Args(String format, Object... params) {
        this(format, List.of(params));
    }

    public CodeSnippet_Args(String format, List<Object> params) {
        m_Format = format;
        m_Content = params;
    }

    public void AddParameter(Object param) {
        m_Content.add(param);
    }

    @Override
    public List<String> WriteLines() {
        return Arrays.asList(Write().split("\n"));
    }

    @Override
    public String Write() {
        return String.format(m_Format, m_Content.toArray());
    }
}
