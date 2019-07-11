package writer.wrappers;

import writer.TypeManager;

public class TypeWrapper_Array extends TypeWrapper {
    private int m_Size;
    private TypeWrapper m_BaseType;

    public TypeWrapper_Array(TypeWrapper baseType, int size) {
        m_BaseType = baseType;
        m_Size = size;
    }

    @Override
    public String CreateTypeName() {
        return TypeManager.MakeArrayType(m_BaseType.CreateTypeName(), m_Size);
    }

    @Override
    public TypeWrapper GetChild(int index) {
        return m_BaseType;
    }

    @Override
    public int GetTypeSize(int align) {
        return m_Size * m_BaseType.GetTypeSize(align);
    }

    @Override
    public boolean CompareType(TypeWrapper otherType) {
        // Is Array?
        if (!(otherType instanceof TypeWrapper_Array)) {
            return false;
        }

        // same size?
        TypeWrapper_Array otherArray = (TypeWrapper_Array) otherType;
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
