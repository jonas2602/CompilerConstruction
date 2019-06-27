package writer;

public class TypeWrapper_Array extends TypeWrapper {
    private int m_Size;
    private TypeWrapper m_BaseType;

    public TypeWrapper_Array(TypeWrapper InBaseType, int InSize) {
        m_BaseType = InBaseType;
        m_Size = InSize;
    }

    @Override
    public String GetTypeName() {
        return TypeManager.MakeArrayType(m_BaseType.GetTypeName(), m_Size);
    }

    @Override
    public TypeWrapper GetChild() {
        return m_BaseType;
    }
}
