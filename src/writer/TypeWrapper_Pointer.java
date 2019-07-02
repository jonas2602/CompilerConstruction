package writer;

public class TypeWrapper_Pointer extends TypeWrapper {
    public static TypeWrapper_Pointer CHARPTR = new TypeWrapper_Pointer(TypeWrapper_Primitive.CHAR);
    public static TypeWrapper_Pointer INTPTR = new TypeWrapper_Pointer(TypeWrapper_Primitive.INT);
    public static TypeWrapper_Pointer FLOAT = new TypeWrapper_Pointer(TypeWrapper_Primitive.FLOAT);
    public static TypeWrapper_Pointer DOUBLE = new TypeWrapper_Pointer(TypeWrapper_Primitive.DOUBLE);
    public static TypeWrapper_Pointer BOOL = new TypeWrapper_Pointer(TypeWrapper_Primitive.BOOL);

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
