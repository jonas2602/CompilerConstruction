package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.declarations.VarDeclNode;
import ast.types.PointerTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class AccessNode_Address extends AbstractSyntaxTree implements AccessInterface {
    private AbstractSyntaxTree m_Child;

    public AccessNode_Address(AbstractSyntaxTree child) {
        m_Child = child;
        m_Child.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        m_Child.CheckType();
        // TODO: only variables are allowed as childs.
        //  is it even possible to have a constant as child?

        return GetType();
    }

    @Override
    public TypeNode GetType() {
        // TODO: find better solution
        // Create temporary pointer node
        return new PointerTypeNode(m_Child.GetType());
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return m_Child.CreateSnippet(slave);
    }

    @Override
    public VarDeclNode GetVarDeclNode() {
        return ((AccessInterface) m_Child).GetVarDeclNode();
    }

    @Override
    public ParamContainer LoadValue(GeneratorSlave InSlave, ParamContainer InPointer) {
        return InPointer;
    }
}
