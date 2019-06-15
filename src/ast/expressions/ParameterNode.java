package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class ParameterNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Expression;

    public ParameterNode(AbstractSyntaxTree InExpression) {
        m_Expression = InExpression;
        m_Expression.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        // Function or Procedure call will take care of actual type checking
        return m_Expression.CheckType();
    }

    @Override
    public TypeNode GetType() {
        return m_Expression.GetType();
    }
}
