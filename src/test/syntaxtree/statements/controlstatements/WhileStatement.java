package test.syntaxtree.statements.controlstatements;

import gen.PascalParser.WhileStatementContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.Node;
import test.syntaxtree.scopes.ProxyNode;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.statements.StatementBlock;
import test.syntaxtree.statements.expressions.Expression;

public class WhileStatement extends Node implements ASTBuilder<WhileStatementContext> {

    private Node condition;
    private ProxyNode doBlock;

    public WhileStatement(ScopeNode parent) {
        super(parent);
        doBlock = new ProxyNode(parent);
    }

    @Override
    public void buildAST(WhileStatementContext ctx) {
        condition = Expression.visitExpression(ctx.expression(), parentBlock);
        StatementBlock.processSingleStatement(ctx.statement(), doBlock);
    }

    @Override
    public String toString() {
        return "WHILE ( "+condition+" ) DO";
    }

    @Override
    public void print(int level) {
        super.println(level, this);
        doBlock.print(level);
    }
}
