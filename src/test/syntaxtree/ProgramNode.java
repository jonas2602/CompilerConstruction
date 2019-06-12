package test.syntaxtree;

public class ProgramNode extends Node {
    private String name;
    private Node block;

    public ProgramNode() {
        super();
    }

    public void setName(String name) {
       this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBlock(Node block) {
        this.block = block;
    }

    public Node getBlock() {
        return this.block;
    }

    @Override
    public String toString() {
        return "PROGRAM "+name;
    }
}
