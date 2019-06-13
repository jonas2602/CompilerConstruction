package test.syntaxtree.subs;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;

public abstract class SubNode extends Node {
    protected String name;
    protected BlockNode block;

    public SubNode(BlockNode parent) {
        super(parent);
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

    public static SubNode buildAST(PascalParser.ProcedureOrFunctionDeclarationContext ctx, BlockNode parent) {
        if(ctx.functionDeclaration() != null) {
            FunctionDeclarationNode func = new FunctionDeclarationNode(parent);
            func.buildAST(ctx.functionDeclaration());
            return func;
        }


        ProcedureDeclarationNode proc = new ProcedureDeclarationNode(parent);
        proc.buildAST(ctx.procedureDeclaration());
        return proc;
    }
}
