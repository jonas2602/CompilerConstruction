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
}
