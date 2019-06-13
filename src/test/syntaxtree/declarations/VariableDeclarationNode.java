package test.syntaxtree.declarations;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;

public class VariableDeclarationNode extends Node {
    private String name;

    public VariableDeclarationNode(BlockNode parent) {
        super(parent);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "VARDEC "+name;
    }

    public void print(int level) {
        super.println(level, this);
    }
}
