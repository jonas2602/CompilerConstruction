package writer;

import java.util.ArrayList;
import java.util.List;

public class TypeWrapper_Struct extends TypeWrapper {
    private String m_RawName;
    private List<TypeWrapper> m_EntryTypes;

    public TypeWrapper_Struct(String rawName) {
        m_RawName = rawName;
        m_EntryTypes = new ArrayList<>();
    }

    public void AddEntry(TypeWrapper entry) {
        m_EntryTypes.add(entry);
    }

    @Override
    public String CreateTypeName() {
        return String.format("%%struct.%s", m_RawName);
    }

    @Override
    public TypeWrapper GetChild() {
        System.err.println("Reached bottom of TypeWrapper");
        return null;
    }

    @Override
    public int GetTypeSize() {
        int size = 0;
        for (TypeWrapper entry : m_EntryTypes) {
            size += entry.GetTypeSize();
        }

        return size;
    }

    @Override
    public boolean CompareType(TypeWrapper otherType) {
        // assuming, that all references of the same struct type use the same type wrapper object
        // it's only necessary to compare the object ptr
        return otherType == this;
    }
}
