package test.syntaxtree.statements;

import test.syntaxtree.Node;

public class LabelDefinitionNode extends Node {
    private int name;

    public LabelDefinitionNode() {
        super();
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "LABEL "+name;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
    }
}
