package test.syntaxtree;

public class DeclarationNode extends Node {
    private String name;

    public DeclarationNode() {
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
}
