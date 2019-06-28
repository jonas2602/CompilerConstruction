package writer;

public class TypeWrapper_Other extends TypeWrapper {
    public static TypeWrapper_Other LABEL = new TypeWrapper_Other("label");

    private String m_TypeName;

    public TypeWrapper_Other(String InTypeName) {
        m_TypeName = InTypeName;
    }

    @Override
    public String CreateTypeName() {
        return m_TypeName;
    }

    @Override
    public TypeWrapper GetChild() {
        System.err.println("Reached bottom of TypeWrapper");
        return null;
    }
}
