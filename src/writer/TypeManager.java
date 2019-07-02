package writer;

import ast.EPrimitiveType;

public class TypeManager {
    public static TypeWrapper STRING(int InSize) {
        return new TypeWrapper_Array(TypeWrapper_Primitive.CHAR, InSize);
    }
    public static TypeWrapper CHARPTR() {
        return new TypeWrapper_Pointer(TypeWrapper_Primitive.CHAR);
    }


    public static String GetTypeName(EPrimitiveType type) {
        switch (type) {
            case CHAR: return "i8";
            case INT: return "i32";
            case LONG: return "i64";
            case FLOAT: return "float";
            case DOUBLE: return "double";
            case BOOL: return "i1";
            default: return "void";
        }
    }

    public static String MakeArrayType(EPrimitiveType type, int size) {
        return MakeArrayType(GetTypeName(type), size);
    }

    public static String MakeArrayType(String type, int size) {
        return String.format("[%d x %s]", size, type);
    }

}
