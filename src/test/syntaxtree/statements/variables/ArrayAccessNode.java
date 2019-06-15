package test.syntaxtree.statements.variables;

import gen.PascalParser;
import gen.PascalParser.IndexedVariableContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.statements.expressions.Expression;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ArrayAccessNode extends VariableAccessNode implements ASTBuilder<IndexedVariableContext> {
    private List<Node> expressions;

    public ArrayAccessNode(BlockNode parent) {
        super(parent);

        expressions = new LinkedList<Node>();
    }

    public void addExpression(Node n) {
        expressions.add(n);
    }

    public List<Node> getExpressions() {
        return Collections.unmodifiableList(expressions);
    }

    @Override
    public void buildAST(IndexedVariableContext ctx) {
        for(PascalParser.ExpressionContext expression: ctx.expression()) {
            addExpression(Expression.visitExpression(expression, parentBlock));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(Node n: expressions) {
            builder.append(n+",");
        }
        builder.append("]");
        return builder.toString();
    }
}
