package writer.wrappers;

import ast.core.*;

public class TypeWrapper_Primitive extends TypeWrapper {
    public static TypeWrapper_Primitive CHAR = new TypeWrapper_Primitive(PascalType_Primitive.CHAR);
    public static TypeWrapper_Primitive INT = new TypeWrapper_Primitive(PascalType_Primitive.INT);
    public static TypeWrapper_Primitive LONG = new TypeWrapper_Primitive(PascalType_Primitive.LONG);
    public static TypeWrapper_Primitive FLOAT = new TypeWrapper_Primitive(PascalType_Primitive.FLOAT);
    public static TypeWrapper_Primitive DOUBLE = new TypeWrapper_Primitive(PascalType_Primitive.DOUBLE);
    public static TypeWrapper_Primitive BOOL = new TypeWrapper_Primitive(PascalType_Primitive.BOOL);

    private PascalType_Primitive m_BaseType; // i8, i32, float, ...

    public TypeWrapper_Primitive(PascalType_Primitive type) {
        m_BaseType = type;
    }

    @Override
    public String CreateTypeName() {
        return m_BaseType.GetTypeName();
    }

    @Override
    public TypeWrapper GetChild(int index) {
        System.err.println("Reached bottom of TypeWrapper");
        return null;
    }

    @Override
    public TypeWrapper GetChild(String index) {
        System.err.println("Reached bottom of TypeWrapper");
        return null;
    }

    @Override
    public int GetTypeSize(int align) {
        return AlignSize(align, m_BaseType.GetSize());
    }

    @Override
    public boolean CompareType(TypeWrapper otherType) {
        // Is Primitive?
        if (!(otherType instanceof TypeWrapper_Primitive)) {
            return false;
        }

        // same primitive?
        if (((TypeWrapper_Primitive) otherType).m_BaseType.getClass() != m_BaseType.getClass()) {
            return false;
        }

        return true;
    }
}
