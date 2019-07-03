package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_PlainObject extends CodeSnippet_Base {
    private Object m_Content;

    public CodeSnippet_PlainObject(Object content) {
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
        return m_Content.toString();
    }
}
