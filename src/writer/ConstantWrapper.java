package writer;

public class ConstantWrapper extends ValueWrapper {
    private String m_Data;

    public ConstantWrapper(String data) {
        m_Data = data;
    }

    @Override
    public String CreateDataString() {
        return m_Data;
    }

}
