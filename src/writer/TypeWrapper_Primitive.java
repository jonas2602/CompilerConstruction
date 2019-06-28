package writer;

import ast.core.*;

public class TypeWrapper_Primitive extends TypeWrapper {
    public static TypeWrapper_Primitive CHAR = new TypeWrapper_Primitive(new PascalType_Char());
    public static TypeWrapper_Primitive INT = new TypeWrapper_Primitive(new PascalType_Int());
    public static TypeWrapper_Primitive FLOAT = new TypeWrapper_Primitive(new PascalType_Float());
    public static TypeWrapper_Primitive DOUBLE = new TypeWrapper_Primitive(new PascalType_Double());
    public static TypeWrapper_Primitive BOOL = new TypeWrapper_Primitive(new PascalType_Bool());

    private PascalType_Primitive m_BaseType; // i8, i32, float, ...

    public TypeWrapper_Primitive(PascalType_Primitive InType) {
        m_BaseType = InType;
    }

    @Override
    public String GetTypeName() {
        return m_BaseType.GetTypeName();
    }

    @Override
    public TypeWrapper GetChild() {
        System.err.println("Reached bottom of TypeWrapper");
        return null;
    }
}
