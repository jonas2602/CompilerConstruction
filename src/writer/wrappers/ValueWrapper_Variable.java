package writer.wrappers;

public class ValueWrapper_Variable extends ValueWrapper {
    public static ValueWrapper_Variable SCOPEVAR(int InIndex) {
        return new ValueWrapper_Variable("%%%d", InIndex);
    }

    public static ValueWrapper_Variable GLOBALVAR(int InIndex) {
        return new ValueWrapper_Variable("@.global.%d", InIndex);
    }

    public static ValueWrapper_Variable CONSTSTRING(int InIndex) {
        return new ValueWrapper_Variable("@.str.%d", InIndex);
    }

    private String m_Format;
    private int m_ScopeIndex;

    public ValueWrapper_Variable(String format) {
        m_Format = format;
    }

    public ValueWrapper_Variable(String format, int index) {
        m_Format = format;
        m_ScopeIndex = index;
    }

    public void AssignScopeIndex(int index) {
        m_ScopeIndex = index;
    }

    @Override
    public String CreateDataString() {
        return String.format(m_Format, m_ScopeIndex);
    }
}
