package writer.wrappers;

public class TypeWrapper_Pointer extends TypeWrapper {
    public static TypeWrapper_Pointer CHARPTR = new TypeWrapper_Pointer(TypeWrapper_Primitive.CHAR);
    public static TypeWrapper_Pointer INTPTR = new TypeWrapper_Pointer(TypeWrapper_Primitive.INT);
    public static TypeWrapper_Pointer FLOAT = new TypeWrapper_Pointer(TypeWrapper_Primitive.FLOAT);
    public static TypeWrapper_Pointer DOUBLE = new TypeWrapper_Pointer(TypeWrapper_Primitive.DOUBLE);
    public static TypeWrapper_Pointer BOOL = new TypeWrapper_Pointer(TypeWrapper_Primitive.BOOL);

    private TypeWrapper m_PointedType;

    public TypeWrapper_Pointer(TypeWrapper pointedType) {
        m_PointedType = pointedType;
    }

    @Override
    public String CreateTypeName() {
        return m_PointedType.CreateTypeName() + "*";
    }

    @Override
    public TypeWrapper GetChild(int index) {
        return m_PointedType;
    }

    @Override
    public int GetTypeSize() {
        // Pointer are always 64 bit integer
        return 64;
    }

    @Override
    public boolean CompareType(TypeWrapper otherType) {
        // Is Pointer?
        if (!(otherType instanceof TypeWrapper_Pointer)) {
            return false;
        }

        // Same pointed type?
        if (!((TypeWrapper_Pointer) otherType).m_PointedType.CompareType(m_PointedType)) {
            return false;
        }

        return true;
    }
}
