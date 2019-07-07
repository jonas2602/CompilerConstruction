package writer.snippets;

import writer.wrappers.TypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Struct extends CodeSnippet_Base {
    private String m_Name;
    private List<CodeSnippet_Base> m_Entries;

    public CodeSnippet_Struct(String name) {
        m_Name = name;
        m_Entries = new ArrayList<>();
    }

    public void AddEntry(TypeWrapper type) {
        m_Entries.add(new CodeSnippet_Plain(type.CreateTypeName()));
    }

    @Override
    public List<String> WriteLines() {
        return new ArrayList<>() {{
            add(Write());
        }};
    }

    @Override
    public String Write() {
        return String.format("%%struct.%s = type { %s }", m_Name, CodeSnippetHelper.MakeSeperatedSnippets(", ", m_Entries));
    }
}
