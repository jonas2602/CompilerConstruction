package test.syntaxtree.statements;

import gen.PascalParser.AssignmentStatementContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.statements.expressions.Expression;

public class AssignmentStatementNode extends Node implements ASTBuilder<AssignmentStatementContext> {

    private VariableNode variable;
    private Node expression;

    public AssignmentStatementNode(BlockNode parent) {
        super(parent);
    }

    public VariableNode getVariable() {
        return variable;
    }

    public void setVariable(VariableNode variable) {
        this.variable = variable;
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
    }

    public void buildAST(AssignmentStatementContext ctx) {
        VariableNode var = new VariableNode(super.parentBlock);
        var.buildAST(ctx.variable());
        variable = var;

        expression = Expression.visitExpression(ctx.expression(), parentBlock);
    }

    @Override
    public String toString() {
        return variable +" = "+ expression;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
    }
}
