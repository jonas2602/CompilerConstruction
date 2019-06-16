package test.syntaxtree.statements.controlstatements;

import gen.PascalParser.ForStatementContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.Node;
import test.syntaxtree.scopes.ProxyNode;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.statements.StatementBlock;
import test.syntaxtree.statements.expressions.Expression;

public class ForStatement extends Node implements ASTBuilder<ForStatementContext> {
    private String variable;
    private Node startValue;
    private Node endValue;
    private ProxyNode doBlock;
    private boolean upper;

    public ForStatement(ScopeNode parent) {
        super(parent);
        doBlock = new ProxyNode(parent);
    }

    @Override
    public void buildAST(ForStatementContext ctx) {
        variable = ctx.identifier().getText();
        startValue = Expression.visitExpression(ctx.forList().initialValue().expression(), parentBlock);
        endValue = Expression.visitExpression(ctx.forList().finalValue().expression(), parentBlock);
        upper = false;
        if(ctx.forList().TO() != null) {
            upper = true;
        }
        StatementBlock.processSingleStatement(ctx.statement(), doBlock);
    }

    @Override
    public String toString() {
        return "FOR ( " + variable + " = " + startValue + " "+(upper?"to":"downto")+" "+endValue+" ) do";
    }

    @Override
    public void print(int level) {
        super.println(level, this);
        doBlock.print(level);
    }
}
