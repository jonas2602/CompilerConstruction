package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.types.RecordTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class RecordFieldWrapper extends VarDeclNode{

    private AbstractSyntaxTree m_Access;
    private int m_FieldIndex;

    public RecordFieldWrapper(String name, AbstractSyntaxTree access, RecordTypeNode record, int index) {
        super(name, record.GetEntryType(index));

        m_Access = access;
        m_FieldIndex = index;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        ParamContainer varAccess = m_Access.CreateSnippet(slave);
        return slave.CreateArrayElementPtr(varAccess, m_FieldIndex);
    }
}
