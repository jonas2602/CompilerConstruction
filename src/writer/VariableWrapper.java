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

    public VariableWrapper(String InFormat) {
        m_Format = InFormat;
    }

    public VariableWrapper(String InFormat, int InIndex) {
        m_Format = InFormat;
        m_ScopeIndex = InIndex;
    }

    public void AssignScopeIndex(int InIndex) {
        m_ScopeIndex = InIndex;
    }

    @Override
    public String CreateDataString() {
        return String.format(m_Format, m_ScopeIndex);
    }
}
