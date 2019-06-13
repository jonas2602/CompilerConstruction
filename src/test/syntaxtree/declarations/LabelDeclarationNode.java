package test.syntaxtree;

import gen.PascalParser;
import test.visitors.PascalVisitor;

public class LabelDeclarationNode extends Node {

    private int value;

    public LabelDeclarationNode(BlockNode parent) {
        super(parent);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void buildAST(PascalParser.LabelContext ctx) {
        value = PascalVisitor.visitUnsignedInt(ctx.unsignedInteger());
    }

    @Override
    public String toString() {
        return "LABELDEC "+value;
    }

    @Override
    public void print(int level) {
        println(level, this);
    }
}
