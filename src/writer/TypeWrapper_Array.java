package writer;

public class TypeWrapper_Array extends TypeWrapper {
    private int m_Size;
    private TypeWrapper m_BaseType;

    public TypeWrapper_Array(TypeWrapper InBaseType, int InSize) {
        m_BaseType = InBaseType;
        m_Size = InSize;
    }

    @Override
    public String CreateTypeName() {
        return TypeManager.MakeArrayType(m_BaseType.CreateTypeName(), m_Size);
    }

    @Override
    public TypeWrapper GetChild() {
        return m_BaseType;
    }

    @Override
    public int GetTypeSize() {
        return m_Size * m_BaseType.GetTypeSize();
    }

    @Override
    public boolean CompareType(TypeWrapper InOtherType) {
        // Is Array?
        if (!(InOtherType instanceof TypeWrapper_Array)) {
            return false;
        }

        // same size?
        TypeWrapper_Array otherArray = (TypeWrapper_Array) InOtherType;
        if (otherArray.m_Size != m_Size) {
            return false;
        }

        // same element types?
        if (!otherArray.m_BaseType.CompareType(m_BaseType)) {
            return false;
        }

        return true;
    }
}
