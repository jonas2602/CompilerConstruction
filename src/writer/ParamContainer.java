package writer;

public class ParamContainer {
    public static ParamContainer LABELCONTAINER() {
        return new ParamContainer(TypeWrapper_Other.LABEL, new ConstantWrapper(""));
    }

    public static ParamContainer FromTypeString(String InTypeString) {
        return null; // TODO:
    }

    private TypeWrapper m_RootType; // i8, i32, float, [3 x i8], [10 x [ 10 x i8]], ...
    private ValueWrapper m_ValueAccessor; // Constant or Local Variable(%X) or Global Variable(@X)

    public ParamContainer(TypeWrapper InType, String InConstant) {
        m_RootType = InType;
        m_ValueAccessor = new ConstantWrapper(InConstant);
    }

    public ParamContainer(TypeWrapper InType, ValueWrapper InValue) {
        m_RootType = InType;
        m_ValueAccessor = InValue;
    }

    // Copy constructors
//    public ParamContainer(ParamContainer InSourceContainer, String InConstant) {
//        this(InSourceContainer.m_RootType, new ConstantWrapper(InConstant));
//    }

    public ParamContainer(ParamContainer InSourceContainer, ValueWrapper InValue) {
        m_RootType = InSourceContainer.m_RootType;
        m_ValueAccessor = InValue;
    }


    public TypeWrapper GetRootType() {
        return m_RootType;
    }

    public ValueWrapper GetValueAccessor() {
        return m_ValueAccessor;
    }

    public void SetValueAccessor(ValueWrapper InWrapper) {
        m_ValueAccessor = InWrapper;
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

    @Override
    public String toString() {
        return CreateParameterString();
    }
}
