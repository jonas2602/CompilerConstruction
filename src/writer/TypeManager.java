package writer;

import ast.EPrimitiveType;

public class TypeManager {
    public static TypeWrapper STRING(int InSize) {
        return new TypeWrapper_Array(TypeWrapper_Primitive.CHAR, InSize);
    }


    public static String GetTypeName(EPrimitiveType InType) {
        switch (InType) {
            case CHAR: return "i8";
            case INT: return "i32";
            case LONG: return "i64";
            case FLOAT: return "float";
            case DOUBLE: return "double";
            default: return "void";
        }
    }

    public static String MakeArrayType(EPrimitiveType InType, int InSize) {
        return MakeArrayType(GetTypeName(InType), InSize);
    }

    public static String MakeArrayType(String InType, int InSize) {
        return String.format("[%d x %s]", InSize, InType);
    }

}
