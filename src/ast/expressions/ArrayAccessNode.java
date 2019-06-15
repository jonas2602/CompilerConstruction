package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.ArrayTypeNode;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

import java.util.List;
import java.util.ArrayList;

public class ArrayAccessNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Child;
    private List<AbstractSyntaxTree> m_IndexExpressions = new ArrayList<>();

    public ArrayAccessNode(AbstractSyntaxTree InChild) {
        this.m_Child = InChild;
        m_Child.SetParent(this);
    }

    public void AddIndexExpression(AbstractSyntaxTree exp) {
        m_IndexExpressions.add(exp);
        exp.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode childType = m_Child.CheckType();
        if (!(childType instanceof ArrayTypeNode)) {
            throw new TypeCheckException(this, "Indexed access is only possible on arrays");
        }

        NamedTypeNode IntTypeNode = NamedTypeNode.IntNode;
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
        return m_Child.GetType().GetTypeDetails();
        // TODO: if multiple indices, type is set of arraytype
    }
}
