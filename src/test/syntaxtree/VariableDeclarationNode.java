package test.syntaxtree;

public class VariableDeclarationNode extends Node {
    private String name;

    public VariableDeclarationNode() {
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
        return "VARDEC "+name;
    }

    public void print(int level) {
        super.println(level, this);
    }
}
