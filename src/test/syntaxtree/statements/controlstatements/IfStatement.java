package test.syntaxtree.statements.controlstatements;

import gen.PascalParser.IfStatementContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.Node;
import test.syntaxtree.scopes.ProxyNode;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.statements.StatementBlock;
import test.syntaxtree.statements.expressions.Expression;

public class IfStatement extends Node implements ASTBuilder<IfStatementContext> {

    private Node condition;
    private ProxyNode thenBlock;
    private ProxyNode elseBlock;

    public IfStatement(ScopeNode parent) {
        super(parent);
        thenBlock = new ProxyNode(parent);
        elseBlock = new ProxyNode(parent);
    }

    @Override
    public void buildAST(IfStatementContext ctx) {
        condition = Expression.visitExpression(ctx.expression(), parentBlock);
        StatementBlock.processSingleStatement(ctx.statement(0), thenBlock);
        if(ctx.ELSE() != null) {
            StatementBlock.processSingleStatement(ctx.statement(1), elseBlock);
        }
        else {
            //TODO: ugly
            elseBlock = null;
        }
    }

    @Override
    public String toString() {
        return "IF ( "+condition+" ) THEN";
    }

    @Override
    public void print(int level) {
        super.println(level, this);
        thenBlock.print(level);
        if(elseBlock != null) {
            super.println(level, "ELSE");
            elseBlock.print(level);
        }
    }
}
