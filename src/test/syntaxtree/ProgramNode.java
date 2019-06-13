package test.syntaxtree;

public class ProgramNode extends Node {
    private String name;
    private BlockNode block;

    public ProgramNode() {
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

    @Override
    public String toString() {
        return "PROGRAM "+name;
    }

    public void print(int level) {
        super.println(level, this);
        block.print(level);
    }
}
