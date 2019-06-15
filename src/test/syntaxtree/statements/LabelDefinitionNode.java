package test.syntaxtree.statements;

import gen.PascalParser.LabelContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.visitors.PascalVisitor;

public class LabelDefinitionNode extends Node implements ASTBuilder<LabelContext> {
    private int value;

    public LabelDefinitionNode(BlockNode parent) {
        super(parent);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void buildAST(LabelContext ctx) {
        value = PascalVisitor.visitUnsignedInt(ctx.unsignedInteger());
    }

    @Override
    public String toString() {
        return "LABEL "+value;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
    }
}
