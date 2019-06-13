package test.syntaxtree.subs;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.subs.SubNode;

public class FunctionDeclarationNode extends SubNode {

    public FunctionDeclarationNode(BlockNode parent) {
        super(parent);
    }

    public void buildAST(PascalParser.FunctionDeclarationContext ctx) {
        name = ctx.identifier().getText();
        block = new BlockNode(parentBlock);
        block.buildAST(ctx.block());
    }

    @Override
    public String toString() {
        return "FUNC "+name;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
        block.print(level);
    }
}
