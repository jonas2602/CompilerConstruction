package writer.wrappers;

public class ParamContainer {
    public static ParamContainer LABELCONTAINER() {
        return new ParamContainer(TypeWrapper_Other.LABEL, new ValueWrapper_Constant(""));
    }

    public static ParamContainer VOIDCONTAINER() {
        return new ParamContainer(TypeWrapper_Other.VOID, new ValueWrapper_Constant(""));
    }

    public static ParamContainer NULLCONTAINER() {
        return new ParamContainer(TypeWrapper_Other.NULL, new ValueWrapper_Constant(""));
    }

    public static ParamContainer BOOLCONTAINER(boolean InBool) {
        return new ParamContainer(TypeWrapper_Primitive.BOOL, new ValueWrapper_Constant(InBool ? "true" : "false"));
    }

    public static ParamContainer CHARCONTAINER(char InChar) {
        return new ParamContainer(TypeWrapper_Primitive.CHAR, new ValueWrapper_Constant(InChar));
    }

    public static ParamContainer INTCONTAINER(int InInt) {
        return new ParamContainer(TypeWrapper_Primitive.INT, new ValueWrapper_Constant(InInt));
    }


    public static ParamContainer FromTypeString(String typeString) {
        return null; // TODO:
    }

    private TypeWrapper m_RootType; // i8, i32, float, [3 x i8], [10 x [ 10 x i8]], ...
    private ValueWrapper m_ValueAccessor; // Constant or Local Variable(%X) or Global Variable(@X)

    public ParamContainer(TypeWrapper type, String constant) {
        this(type, new ValueWrapper_Constant(constant));
    }

    public ParamContainer(TypeWrapper type, ValueWrapper value) {
        m_RootType = type;
        m_ValueAccessor = value;
    }

    // Copy constructors
//    public ParamContainer(ParamContainer InSourceContainer, String InConstant) {
//        this(InSourceContainer.m_RootType, new ValueWrapper_Constant(InConstant));
//    }

    public ParamContainer(ParamContainer sourceContainer, ValueWrapper value) {
        m_RootType = sourceContainer.m_RootType;
        m_ValueAccessor = value;
    }


    public TypeWrapper GetRootType() {
        return m_RootType;
    }

    public ValueWrapper GetValueAccessor() {
        return m_ValueAccessor;
    }

    public void SetValueAccessor(ValueWrapper wrapper) {
        m_ValueAccessor = wrapper;
    }

    public String CreateTypeString() {
        return m_RootType.CreateTypeName();
    }

    public String CreateDataString() {
        return m_ValueAccessor.CreateDataString();
    }

    public String CreateParameterString() {
        return String.format("%s %s", m_RootType.CreateTypeName(), m_ValueAccessor);
    }

    public boolean IsPrimitive() {
        return m_RootType instanceof TypeWrapper_Primitive;
    }

    public boolean IsArray() {
        return m_RootType instanceof TypeWrapper_Array;
    }

    public boolean IsPointer() {
        return m_RootType instanceof TypeWrapper_Pointer;
    }

    @Override
    public String toString() {
        return CreateParameterString();
    }
}
