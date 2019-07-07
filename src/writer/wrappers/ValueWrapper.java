package writer.wrappers;

public abstract class ValueWrapper {
    public abstract String CreateDataString();

    @Override
    public String toString() {
        return CreateDataString();
    }
}
