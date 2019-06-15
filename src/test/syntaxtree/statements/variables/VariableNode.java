package test.syntaxtree.statements;

import gen.PascalParser.VariableContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;

public class VariableNode extends Node implements ASTBuilder<VariableContext> {

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

    public void buildAST(VariableContext ctx) {
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
