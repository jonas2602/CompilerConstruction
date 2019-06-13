package test.syntaxtree.subs;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.subs.SubNode;

public class ProcedureDeclarationNode extends SubNode {

    public ProcedureDeclarationNode(BlockNode parent) {
        super(parent);
    }

    public void buildAST(PascalParser.ProcedureDeclarationContext ctx) {
        name = ctx.identifier().getText();
        block = new BlockNode(parentBlock);
        block.buildAST(ctx.block());
    }

    @Override
    public String toString() {
        return "PROC "+name;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
        super.printParameters(level);
        block.print(level);
    }
}
