package ast.expression;

import ast.AbstractSyntaxTree;
import ast.EPrimitiveType;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

public class ArrayAccessNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Child;
    private AbstractSyntaxTree[] m_IndexExpressions;

    public ArrayAccessNode(AbstractSyntaxTree InChild, AbstractSyntaxTree[] InIndexExpressions) {
        this.m_Child = InChild;
        this.m_IndexExpressions = InIndexExpressions;
    }

    @Override
    public TypeNode CheckType() {
        NamedTypeNode IntTypeNode = NamedTypeNode.IntNode();
        for (AbstractSyntaxTree index : m_IndexExpressions) {
            // Is IndexNode of primitive type INT?
            TypeNode IndexType = index.CheckType();
            if (!IntTypeNode.CompareType(IndexType)) {
                return null;
            }
        }

        return GetType();
    }

    @Override
    public TypeNode GetType() {
        return m_Child.GetType();
    }
}
