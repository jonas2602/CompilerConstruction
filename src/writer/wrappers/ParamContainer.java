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

    public static ParamContainer BOOLCONTAINER(boolean b) {
        return new ParamContainer(TypeWrapper_Primitive.BOOL, new ValueWrapper_Constant(b ? "true" : "false"));
    }

    public static ParamContainer CHARCONTAINER(char c) {
        return new ParamContainer(TypeWrapper_Primitive.CHAR, ValueWrapper_Constant.ValueWrapperInt(c));
    }

    public static ParamContainer INTCONTAINER(int i) {
        return new ParamContainer(TypeWrapper_Primitive.INT, ValueWrapper_Constant.ValueWrapperInt(i));
    }

    public static ParamContainer LONGCONTAINER(long l) {
        return new ParamContainer(TypeWrapper_Primitive.LONG, ValueWrapper_Constant.ValueWrapperLong(l));
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

    public boolean HasExtendableType() {
        return m_RootType.IsExtendable();
    }

    public boolean HasExtendedType() {
        return m_RootType.IsExtended();
    }

    public int GetTypeAlignment(){
        return m_RootType.GetAlignment();
    }

    @Override
    public String toString() {
        return CreateParameterString();
    }
}
