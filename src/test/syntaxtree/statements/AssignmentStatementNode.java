package test.syntaxtree.statements;

import test.syntaxtree.Node;

public class AssignmentStatementNode extends Node {

    private String variable;
    private Node expression;

    public AssignmentStatementNode() {
        super();
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Node getExpression() {
        return expression;
    }

    public void setExpression(Node expression) {
        this.expression = expression;
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
