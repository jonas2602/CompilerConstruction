package writer.wrappers;

public class ValueWrapper_Constant extends ValueWrapper {
    private String m_Data;

    public ValueWrapper_Constant(String data) {
        m_Data = data;
    }

    public ValueWrapper_Constant(int data) {
        m_Data = Integer.toString(data);
    }

    public ValueWrapper_Constant(float data) {
        m_Data = Float.toString(data);
    }

    @Override
    public String CreateDataString() {
        return m_Data;
    }

}
