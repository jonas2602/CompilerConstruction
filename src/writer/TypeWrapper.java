package writer;

public abstract class TypeWrapper {
    public abstract String CreateTypeName();
    public abstract TypeWrapper GetChild();

    @Override
    public String toString() {
        return CreateTypeName();
    }
}
