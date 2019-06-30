package writer;

public abstract class TypeWrapper {
    public abstract String CreateTypeName();
    public abstract TypeWrapper GetChild();
    public abstract int GetTypeSize();
    public abstract boolean CompareType(TypeWrapper InOtherType);

    @Override
    public String toString() {
        return CreateTypeName();
    }
}
