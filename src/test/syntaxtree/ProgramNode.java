package test.syntaxtree;

import gen.PascalParser;
import test.syntaxtree.scopes.BlockNode;
import test.syntaxtree.scopes.ScopeNode;

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

    public ScopeNode getBlock() {
        return this.block;
    }

    public void buildAST(PascalParser.ProgramContext ctx) {
        name = ctx.programHeading().identifier().getText();
        block = new BlockNode();
        block.buildAST(ctx.block());
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
