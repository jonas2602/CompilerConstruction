package writer.wrapper;

public class TypeWrapper_Other extends TypeWrapper {
    public static TypeWrapper_Other LABEL = new TypeWrapper_Other("label");
    public static TypeWrapper_Other VOID = new TypeWrapper_Other("void");
    public static TypeWrapper_Other VARIADIC = new TypeWrapper_Other("...");
    public static TypeWrapper_Other NULL = new TypeWrapper_Other("null");

    private String m_TypeName;

    public TypeWrapper_Other(String name) {
        m_TypeName = name;
    }

    @Override
    public String CreateTypeName() {
        return m_TypeName;
    }

    @Override
    public TypeWrapper GetChild(int index) {
        System.err.println("Reached bottom of TypeWrapper");
        return null;
    }

    @Override
    public int GetTypeSize() {
        System.out.println("Unknown size of type " + m_TypeName);
        return -1;
    }

    @Override
    public boolean CompareType(TypeWrapper otherType) {
        // Is Named Type?
        if (!(otherType instanceof TypeWrapper_Other)) {
            return false;
        }

        // same type name?
        if (((TypeWrapper_Other) otherType).m_TypeName != m_TypeName) {
            return false;
        }

        return true;
    }
}
