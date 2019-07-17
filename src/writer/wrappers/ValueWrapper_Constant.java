package writer.wrappers;

public class ValueWrapper_Constant extends ValueWrapper {
    private String m_Data;

    public static ValueWrapper_Constant ValueWrapperLong(long data) {
        return new ValueWrapper_Constant(Long.toString(data));
    }

    public static ValueWrapper_Constant ValueWrapperInt(int data) {
        return new ValueWrapper_Constant(Integer.toString(data));
    }

    public static ValueWrapper_Constant ValueWrapperFloat(float data) {
        return new ValueWrapper_Constant(Float.toString(data));
    }

    public static ValueWrapper_Constant ZEROINITIALIZER = new ValueWrapper_Constant("zeroinitializer");
    public static ValueWrapper_Constant NULL = new ValueWrapper_Constant("null");


    public ValueWrapper_Constant(String data) {
        m_Data = data;
    }

    @Override
    public String CreateDataString() {
        return m_Data;
    }

}
