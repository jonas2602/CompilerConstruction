package llvm;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Plain extends CodeSnippet_Base {
    private List<String> m_Content;

    public CodeSnippet_Plain(String InContent) {
        m_Content = new ArrayList<>();
        m_Content.add(InContent);
    }

    public CodeSnippet_Plain(List<String> InContent) {
        m_Content = InContent;
    }

    @Override
    public List<String> WriteLines() {
        return m_Content;
    }

    @Override
    public String Write() {
        return CodeSnippetHelper.MakeSeperatedString("\n", m_Content);
    }
}
