package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.VarDeclNode;
import ast.types.RecordTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class AccessNode_Field extends AbstractSyntaxTree implements AccessInterface {
    private AbstractSyntaxTree m_Child;
    private String m_FieldName;
    private int m_FieldIndex;

    public AccessNode_Field(AbstractSyntaxTree child, String fieldName) {
        m_Child = child;
        m_FieldName = fieldName;

        m_Child.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode childType = m_Child.CheckType().GetTypeDetails();

        // child is Record?
        if (!(childType instanceof RecordTypeNode)) {
            throw new TypeCheckException(this, "Field Access is only possible on records");
        }

        // has property with fieldname?
        RecordTypeNode recordType = (RecordTypeNode) childType;
        m_FieldIndex = recordType.GetFieldIndex(m_FieldName);
        if (m_FieldIndex < 0) {
            throw new TypeCheckException(this, "Record has no property called " + m_FieldName);
        }

        return GetType();
    }

    @Override
    public TypeNode GetType() {
        RecordTypeNode recordType = (RecordTypeNode) m_Child.CheckType().GetTypeDetails();
        return recordType.GetEntryType(m_FieldIndex);
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        ParamContainer varAccess = m_Child.CreateSnippet(slave);
        return slave.CreateArrayElementPtr(varAccess, m_FieldIndex);
    }

    @Override
    public VarDeclNode GetVarDeclNode() {
        return ((AccessInterface) m_Child).GetVarDeclNode();
    }

    @Override
    public ParamContainer LoadValue(GeneratorSlave slave, ParamContainer pointer) {
        return slave.LoadFromVariable(pointer);
    }
}
