package writer;

public class TypeWrapper_Pointer extends TypeWrapper {
    private TypeWrapper m_PointedType;

    public TypeWrapper_Pointer(TypeWrapper InPointedType) {
        m_PointedType = InPointedType;
    }

    @Override
    public String GetTypeName() {
        return m_PointedType.GetTypeName() + "*";
    }

    @Override
    public TypeWrapper GetChild() {
        return m_PointedType;
    }
}
