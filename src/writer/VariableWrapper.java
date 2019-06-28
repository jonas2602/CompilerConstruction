package writer;

public class VariableWrapper extends ValueWrapper {
    public static VariableWrapper SCOPEVAR() {
        return new VariableWrapper("%%%d");
    }

    public static VariableWrapper CONSTSTRING() {
        return new VariableWrapper("@.str.%d");
    }

    private String m_Format;
    private int m_ScopeIndex;

    public VariableWrapper(String InFormat) {
        m_Format = InFormat;
    }

    public void AssignScopeIndex(int InIndex) {
        m_ScopeIndex = InIndex;
    }

    @Override
    public String CreateDataString() {
        return String.format(m_Format, m_ScopeIndex);
    }
}
