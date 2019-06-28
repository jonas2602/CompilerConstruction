package writer;

public class ConstantWrapper extends ValueWrapper {
    private String m_Data;

    public ConstantWrapper(String InData) {
        m_Data = InData;
    }

    @Override
    public String CreateDataString() {
        return m_Data;
    }

}
