package test.syntaxtree.statements.controlstatements;

import gen.PascalParser;
import gen.PascalParser.RepeatStatementContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.Node;
import test.syntaxtree.scopes.ProxyNode;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.statements.StatementBlock;
import test.syntaxtree.statements.expressions.Expression;

public class RepeatStatement extends Node implements ASTBuilder<RepeatStatementContext> {

    private Node condition;
    private ProxyNode doBlock;

    public RepeatStatement(ScopeNode parent) {
        super(parent);
        doBlock = new ProxyNode(parent);
    }

    @Override
    public void buildAST(RepeatStatementContext ctx) {
        condition = Expression.visitExpression(ctx.expression(), parentBlock);
        for(PascalParser.StatementContext statement: ctx.statements().statement()) {
            StatementBlock.processSingleStatement(statement, doBlock);
        }
    }

    @Override
    public String toString() {
        return "until ( "+condition+" )";
    }

    @Override
    public void print(int level) {
        super.println(level, "REPEAT");
        doBlock.print(level);
        super.println(level, this);
    }
}
