package writer;

public abstract class TypeWrapper {
    public abstract String CreateTypeName();
    public abstract TypeWrapper GetChild();
    public abstract int GetTypeSize();
    public abstract boolean CompareType(TypeWrapper otherType);
    public int GetTypeByteSize() {
        return GetTypeSize() / 8;
    }

    @Override
    public String toString() {
        return CreateTypeName();
    }
}
