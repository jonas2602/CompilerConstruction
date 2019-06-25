package ast.core;

public class PascalType {
    private String m_ShortName;
    private int m_Size;
    private boolean m_bDezimal;
    private String m_Placeholder;
    private String m_DefaultValue;

    public PascalType(String InShortName, int InSize, boolean InDezimal, String InPlaceholder, String InDefaultValue) {
        m_ShortName = InShortName;
        m_Size = InSize;
        m_bDezimal = InDezimal;
        m_Placeholder = InPlaceholder;
        m_DefaultValue = InDefaultValue;
    }

    public String GetShortName() {
        return m_ShortName;
    }

    public int GetSize() {
        return m_Size;
    }

    public boolean GetIsDezimal() {
        return m_bDezimal;
    }

    public String GetPlaceholder() {
        return m_Placeholder;
    }

    public String GetDefaultValue() {
        return m_DefaultValue;
    }
}
