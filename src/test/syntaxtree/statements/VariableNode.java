package test.syntaxtree.statements;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;

public class VariableNode extends Node {

    private String name;
    private VariableAccessNode access;

    public VariableNode(BlockNode parent) {
        super(parent);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void buildAST(PascalParser.VariableContext ctx) {
        name = ctx.identifier().getText();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
