package ast.statement;

import ast.AbstractSyntaxTree;

public class AssignmentNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Variable;
    private AbstractSyntaxTree m_Expression;

    public AssignmentNode(AbstractSyntaxTree InVariable, AbstractSyntaxTree InExpression) {
        this.m_Variable = InVariable;
        this.m_Expression = InExpression;
    }
}
