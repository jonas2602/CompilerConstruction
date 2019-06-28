package llvm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeSnippet_Args extends CodeSnippet_Base {
    private String m_Format;
    private List<Object> m_Content;

    public CodeSnippet_Args(String InFormat) {
        this(InFormat, new ArrayList<>());
    }

    public CodeSnippet_Args(String InFormat, Object InParam) {
        m_Format = InFormat;
        m_Content = new ArrayList<>() {{
            add(InParam);
        }};
    }

    public CodeSnippet_Args(String InFormat, List<Object> InContent) {
        m_Format = InFormat;
        m_Content = InContent;
    }

    public void AddParameter(Object InParam) {
        m_Content.add(InParam);
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
