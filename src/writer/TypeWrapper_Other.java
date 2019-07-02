package writer;

public class TypeWrapper_Other extends TypeWrapper {
    public static TypeWrapper_Other LABEL = new TypeWrapper_Other("label");
    public static TypeWrapper_Other VOID = new TypeWrapper_Other("void");
    public static TypeWrapper_Other VARIADIC = new TypeWrapper_Other("...");

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

    @Override
    public int GetTypeSize() {
        System.out.println("Unknown size of type " + m_TypeName);
        return -1;
    }

    @Override
    public boolean CompareType(TypeWrapper InOtherType) {
        // Is Named Type?
        if (!(InOtherType instanceof TypeWrapper_Other)) {
            return false;
        }

        // same type name?
        if (((TypeWrapper_Other) InOtherType).m_TypeName != m_TypeName) {
            return false;
        }

        return true;
    }
}
