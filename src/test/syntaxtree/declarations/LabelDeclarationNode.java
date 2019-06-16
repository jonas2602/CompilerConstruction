package test.syntaxtree.declarations;

import gen.PascalParser;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.visitors.PascalVisitor;

public class LabelDeclarationNode extends Node {

    private int value;

    public LabelDeclarationNode(ScopeNode parent) {
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
