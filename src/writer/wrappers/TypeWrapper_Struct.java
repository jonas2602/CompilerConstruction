package writer.wrappers;

import writer.snippets.CodeSnippetHelper;

import java.util.ArrayList;
import java.util.List;

public class TypeWrapper_Struct extends TypeWrapper {
    private String m_RawName;
    private List<TypeWrapper> m_EntryTypes;
    private boolean m_bExtended;

    public TypeWrapper_Struct(String rawName) {
        m_RawName = rawName;
        m_EntryTypes = new ArrayList<>();
    }

    public TypeWrapper_Struct(String rawName, List<TypeWrapper> entries, boolean bExtended) {
        m_RawName = rawName;
        m_EntryTypes = entries;
        m_bExtended = bExtended;
    }

    public void AddEntry(TypeWrapper entry) {
        m_EntryTypes.add(entry);
    }

    @Override
    public String CreateTypeName() {
        if (m_bExtended) {
            return String.format("{ %s }", CodeSnippetHelper.MakeSeperatedTypes(", ", m_EntryTypes));
        } else {
            return String.format("%%struct.%s", m_RawName);
        }
    }

    @Override
    public TypeWrapper GetChild(int index) {
        return m_EntryTypes.get(index);
    }

    @Override
    public TypeWrapper GetChild(String index) {
        return m_EntryTypes.get(Integer.parseInt(index));
    }

    @Override
    public int GetTypeSize(int align) {
        int size = 0;
        for (TypeWrapper entry : m_EntryTypes) {
            size += entry.GetTypeSize(align);
        }

        return size;
    }

    @Override
    public boolean CompareType(TypeWrapper otherType) {
        // assuming, that all references of the same struct type use the same type wrapper object
        // it's only necessary to compare the object ptr
        return otherType == this;
    }

    @Override
    public boolean IsExtended() {
        return m_bExtended;
    }

    @Override
    public TypeWrapper MakeExtended() {
        if (m_bExtended) {
            // return self if already an extended struct
            return this;
        }

        return new TypeWrapper_Struct(m_RawName, m_EntryTypes, true);
    }
}
