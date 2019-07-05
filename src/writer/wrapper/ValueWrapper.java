package writer.wrapper;

public abstract class ValueWrapper {
    public abstract String CreateDataString();

    @Override
    public String toString() {
        return CreateDataString();
    }
}
