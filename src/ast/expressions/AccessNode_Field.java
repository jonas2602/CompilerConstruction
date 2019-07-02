package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.declarations.VarDeclNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class AccessNode_Field extends AbstractSyntaxTree implements AccessInterface {
    private AbstractSyntaxTree m_Child;
    private String m_FieldName;

    public AccessNode_Field(AbstractSyntaxTree child, String fieldName) {
        m_Child = child;
        m_FieldName = fieldName;

        m_Child.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode childType = m_Child.CheckType();

        // TODO:
        // child is Record?
        // has property with fieldname?
        // return property type
        return null;
    }

    @Override
    public VarDeclNode GetVarDeclNode() {
        return null;
    }

    @Override
    public ParamContainer LoadValue(GeneratorSlave slave, ParamContainer pointer) {
        return pointer;
    }
}
