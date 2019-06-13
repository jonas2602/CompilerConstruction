package test.syntaxtree;

public abstract class SubNode extends Node {
    protected String name;
    protected BlockNode block;

    public SubNode() {
        super();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBlock(BlockNode block) {
        this.block = block;
    }

    public BlockNode getBlock() {
        return this.block;
    }
}
