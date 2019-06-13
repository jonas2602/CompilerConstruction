package test.syntaxtree;

public class ConstantDeclarationNode extends Node {

    private String name;

    public ConstantDeclarationNode() {
        super();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "CONST "+name;
    }

    @Override
    public void print(int level) {

    }
}
