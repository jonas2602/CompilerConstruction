package test.syntaxtree.statements.calls;

import gen.PascalParser.ActualParameterContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.statements.expressions.Expression;

public class Parameter extends Node implements ASTBuilder<ActualParameterContext> {

    private Node expression;

    public Parameter(BlockNode parent) {
        super(parent);
    }

    @Override
    public void buildAST(ActualParameterContext ctx) {
        expression = Expression.visitExpression(ctx.expression(), parentBlock);
        //TODO: width
    }

    @Override
    public String toString() {
        return expression + ",";
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
