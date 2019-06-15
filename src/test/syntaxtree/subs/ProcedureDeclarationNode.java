package test.syntaxtree.subs;

import gen.PascalParser.ProcedureDeclarationContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;

public class ProcedureDeclarationNode extends SubNode implements ASTBuilder<ProcedureDeclarationContext> {

    public ProcedureDeclarationNode(BlockNode parent) {
        super(parent);
    }

    public void buildAST(ProcedureDeclarationContext ctx) {
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
