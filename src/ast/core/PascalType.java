package ast.core;

public class PascalType {
    private final String m_ShortName;
    private final int m_Size;
    private final boolean m_bDezimal;
    private final String m_Placeholder;
    private final String m_DefaultValue;

    public PascalType(String InShortName, int InSize, boolean InDezimal, String InPlaceholder, String InDefaultValue) {
        m_ShortName = InShortName;
        m_Size = InSize;
        m_bDezimal = InDezimal;
        m_Placeholder = InPlaceholder;
        m_DefaultValue = InDefaultValue;
    }

    public final String GetShortName() {
        return m_ShortName;
    }

    public final int GetSize() {
        return m_Size;
    }

    public final boolean GetIsDezimal() {
        return m_bDezimal;
    }

    public final String GetPlaceholder() {
        return m_Placeholder;
    }

    public final String GetDefaultValue() {
        return m_DefaultValue;
    }
}
