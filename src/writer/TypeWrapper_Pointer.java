package writer;

public class TypeWrapper_Pointer extends TypeWrapper {
    private TypeWrapper m_PointedType;

    public TypeWrapper_Pointer(TypeWrapper InPointedType) {
        m_PointedType = InPointedType;
    }

    @Override
    public String CreateTypeName() {
        return m_PointedType.CreateTypeName() + "*";
    }

    @Override
    public TypeWrapper GetChild() {
        return m_PointedType;
    }

    @Override
    public int GetTypeSize() {
        return m_PointedType.GetTypeSize();
    }

    @Override
    public boolean CompareType(TypeWrapper InOtherType) {
        // Is Pointer?
        if (!(InOtherType instanceof TypeWrapper_Pointer)) {
            return false;
        }

        // Same pointed type?
        if (!((TypeWrapper_Pointer) InOtherType).m_PointedType.CompareType(m_PointedType)) {
            return false;
        }

        return true;
    }
}
