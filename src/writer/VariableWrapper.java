package writer;

public class VariableWrapper extends ValueWrapper {
    public static VariableWrapper SCOPEVAR(int InIndex) {
        return new VariableWrapper("%%%d", InIndex);
    }

    public static VariableWrapper CONSTSTRING(int InIndex) {
        return new VariableWrapper("@.str.%d", InIndex);
    }

    private String m_Format;
    private int m_ScopeIndex;

    public VariableWrapper(String format) {
        m_Format = format;
    }

    public VariableWrapper(String format, int index) {
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
