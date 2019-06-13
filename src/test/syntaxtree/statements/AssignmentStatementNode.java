package test.syntaxtree.statements;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;

public class AssignmentStatementNode extends Node {

    private Node variable;
    private Node expression;

    public AssignmentStatementNode(BlockNode parent) {
        super(parent);
    }

    public Node getVariable() {
        return variable;
    }

    public void setVariable(Node variable) {
        this.variable = variable;
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
    }

    public void buildAST(PascalParser.AssignmentStatementContext ctx) {
        VariableNode var = new VariableNode(super.parentBlock);
        var.buildAST(ctx.variable());
        variable = var;
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
