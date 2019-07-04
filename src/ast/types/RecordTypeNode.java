package ast.types;

import ast.TypeCheckException;
import ast.declarations.TypeDeclNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.TypeWrapper;
import writer.TypeWrapper_Struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordTypeNode extends TypeNode {
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
    public TypeNode CheckType() {
        for (TypeNode entry : m_EntryList) {
            entry.CheckType();

            if (CompareType(entry)) {
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

            String structName = ((TypeDeclNode) GetParent()).GetName();
            m_WrapperCache = new TypeWrapper_Struct(structName);

            for (TypeNode entry : m_EntryList) {
                m_WrapperCache.AddEntry(entry.GetWrappedType());
            }
        }

        return m_WrapperCache;
    }

    // only gets called by the owning type definition node
    // => get struct name from parent
    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        String structName = ((TypeDeclNode) GetParent()).GetName();
        m_WrapperCache = new TypeWrapper_Struct(structName);

        List<TypeWrapper> entryTypes = new ArrayList<>();
        for (TypeNode entryType : m_EntryList) {
            TypeWrapper wrapper = entryType.GetWrappedType();
            entryTypes.add(wrapper);
            m_WrapperCache.AddEntry(wrapper);
        }

        slave.CreateStruct(structName, entryTypes);
        return null;
    }
}

