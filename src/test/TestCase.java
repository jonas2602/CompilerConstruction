package test;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
    private String m_FileName;
    private List<String> m_Lines;

    public TestCase(String fileName, String... lines) {
        m_FileName = fileName;

        m_Lines = new ArrayList<>();
        m_Lines.add("READY");
        for(String s: lines) {
            m_Lines.add(s);
        }
    }

    public TestCase AddLines(String... lines) {
        for(String s: lines) {
            m_Lines.add(s);
        }

        return this;
    }

    public TestCase AddSeperatorLines(String lines) {
        String[] unpackedLines = lines.split(" ");
        return AddLines(unpackedLines);
    }

    public String GetFileName() {
        return m_FileName;
    }

    public List<String> GetLines() {
        return m_Lines;
    }
}
