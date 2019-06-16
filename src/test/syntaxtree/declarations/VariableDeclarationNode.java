package test.syntaxtree.declarations;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;

public class VariableDeclarationNode extends Node {
    private String name;

    public VariableDeclarationNode(ScopeNode parent) {
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
