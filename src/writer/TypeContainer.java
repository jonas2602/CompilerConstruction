package writer;

public class TypeContainer {
    public static TypeContainer FromTypeString(String InTypeString) {
        return null; // TODO:
    }

    private GeneratorSlave m_Slave;
    private TypeWrapper m_RootType; // i8, i32, float, [3 x i8], [10 x [ 10 x i8]], ...
    private String m_ValueAccessor; // Constant or Local Variable(%X) or Global Variable(@X)

    public TypeContainer(GeneratorSlave InSlave, TypeWrapper InType, String InAccessor) {
        m_Slave = InSlave;
        m_RootType = InType;
        m_ValueAccessor = InAccessor;
    }

    public TypeContainer(TypeWrapper InType, String InAccessor) {
        m_Slave = null;
        m_RootType = InType;
        m_ValueAccessor = InAccessor;
    }

    public TypeWrapper GetRootType() {
        return m_RootType;
    }

    public String GetTypeName() {
        return m_RootType.GetTypeName();
    }

    public String GetValueAccessor() {
        return m_ValueAccessor;
    }

    public String CreateParameterString() {
        return String.format("%s %s", m_RootType.GetTypeName(), m_ValueAccessor);
    }


}
