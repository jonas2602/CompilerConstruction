package test.syntaxtree.statements;

import gen.PascalParser.GotoStatementContext;

import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.visitors.PascalVisitor;

public class GotoStatementNode extends Node implements ASTBuilder<GotoStatementContext> {

    private int value;

    public GotoStatementNode(BlockNode parent) {
        super(parent);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void buildAST(GotoStatementContext ctx) {
        value = PascalVisitor.visitUnsignedInt(ctx.label().unsignedInteger());
    }

    @Override
    public String toString() {
        return "GOTO "+value;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
    }
}
