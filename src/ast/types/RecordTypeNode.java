package ast.types;

import ast.TypeCheckException;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;
import writer.wrappers.TypeWrapper_Struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordTypeNode extends TypeNode {
    private String m_RecordName;
    private List<TypeNode> m_EntryList;
    private Map<String, Integer> m_EntryNameMap;

    private TypeWrapper_Struct m_WrapperCache;


    public RecordTypeNode() {
        m_EntryList = new ArrayList<>();
        m_EntryNameMap = new HashMap<>();
    }

    public void AddEntry(String entryName, TypeNode entryType) {
        m_EntryNameMap.put(entryName, m_EntryList.size());
        m_EntryList.add(entryType);

        entryType.SetParent(this);
    }

    @Override
    public void SetTypeName(String name) {
        m_RecordName = name;
    }

    public int GetFieldIndex(String name) {
        if (!m_EntryNameMap.containsKey(name)) {
            return -1;
        }

        return m_EntryNameMap.get(name);
    }

    public TypeNode GetEntryType(int entryIndex) {
        return m_EntryList.get(entryIndex);
    }

    @Override
    public TypeNode CheckType() {
        for (TypeNode entry : m_EntryList) {
            entry.CheckType();

            if (CompareType(entry)) {
                // TODO: how about more complex loops? a -> b -> c -> a -> b -> c -> a, ...
                throw new TypeCheckException(this, "Records nested in itself are not allowed. Use pointer to avoid memory overflow");
            }
        }

        return GetType();
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof RecordTypeNode)) {
            return false;
        }

        RecordTypeNode OtherType = ((RecordTypeNode) otherTypeNode.GetCompareType());
        if (OtherType != this) {
            return false;
        }

        return true;
    }

    @Override
    public TypeWrapper GetWrappedType() {
        if (m_WrapperCache == null) {
            System.out.println("Record type requested before construction finished");
            m_WrapperCache = new TypeWrapper_Struct(m_RecordName);

            for (TypeNode entry : m_EntryList) {
                m_WrapperCache.AddEntry(entry.GetWrappedType());
            }
        }

        return m_WrapperCache;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // gets called by the owning type definition node at least once
        // => get struct name from parent
        m_WrapperCache = new TypeWrapper_Struct(m_RecordName);

        List<TypeWrapper> entryTypes = new ArrayList<>();
        for (TypeNode entryType : m_EntryList) {
            TypeWrapper wrapper = entryType.GetWrappedType();
            entryTypes.add(wrapper);
            m_WrapperCache.AddEntry(wrapper);
        }

        slave.CreateStruct(m_RecordName, entryTypes);
        return null;
    }
}

