package writer;

public class ConstantWrapper extends ValueWrapper {
    private String m_Data;

    public ConstantWrapper(String data) {
        m_Data = data;
    }

    public ConstantWrapper(int data) {
        m_Data = Integer.toString(data);
    }

    public ConstantWrapper(float data) {
        m_Data = Float.toString(data);
    }

    @Override
    public String CreateDataString() {
        return m_Data;
    }

}
