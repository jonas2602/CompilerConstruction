package test.syntaxtree.declarations;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;

public class ConstantDeclarationNode extends Node {

    private String name;

    public ConstantDeclarationNode(BlockNode parent) {
        super(parent);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void buildAST(PascalParser.ConstantDefinitionContext ctx) {
        name = ctx.identifier().getText();

        //TODO: type?
    }

    @Override
    public String toString() {
        return "CONST "+name;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
    }
}
