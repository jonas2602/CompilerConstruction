package test.syntaxtree.statements;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.visitors.PascalVisitor;

public class GotoStatementNode extends Node {

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

    public void buildAST(PascalParser.GotoStatementContext label) {
        value = PascalVisitor.visitUnsignedInt(label.label().unsignedInteger());
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
