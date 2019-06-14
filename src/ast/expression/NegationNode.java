package ast.expression;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

public class NegationNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Child;

    public NegationNode(AbstractSyntaxTree InChild) {
        m_Child = InChild;
        m_Child.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        // TODO: which types can get negated?
        TypeNode childType = m_Child.CheckType();
        if (NamedTypeNode.IntNode.CompareType(childType)) {
            return NamedTypeNode.IntNode;
        } else if (NamedTypeNode.RealNode.CompareType(childType)) {
            return NamedTypeNode.RealNode;
        } else {
            throw new TypeCheckException(this, "Negation not defined for " + childType);
        }
    }

    @Override
    public TypeNode GetType() {
        return m_Child.GetType();
    }
}
