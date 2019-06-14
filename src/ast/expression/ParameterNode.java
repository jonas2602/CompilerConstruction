package ast.expression;

import ast.AbstractSyntaxTree;

public class ParameterNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Expression;

    public ParameterNode(AbstractSyntaxTree InExpression){
        m_Expression = InExpression;
    }
}
