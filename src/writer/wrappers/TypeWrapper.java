package writer.wrappers;

public abstract class TypeWrapper {
    public abstract String CreateTypeName();

    public abstract TypeWrapper GetChild(int index);
    public abstract TypeWrapper GetChild(String index);

    public TypeWrapper GetChild() {
        return GetChild(0);
    }

    public abstract boolean CompareType(TypeWrapper otherType);

    public abstract int GetTypeSize(int align);

    public int GetTypeByteSize() {
        return GetTypeByteSize(0);
    }

    public int GetTypeByteSize(int align) {
        return GetTypeSize(align * 8) / 8;
    }

    protected int AlignSize(int alignment, int actualSize) {
        if (alignment > 0) {
            int count = (int) Math.ceil((double) actualSize / (double) alignment);
            return count * alignment;
        }

        return actualSize;
    }

    @Override
    public String toString() {
        return CreateTypeName();
    }

}
