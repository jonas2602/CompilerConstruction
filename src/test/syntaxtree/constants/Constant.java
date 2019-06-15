package test.syntaxtree.constants;

import gen.PascalParser;
import gen.PascalParser.UnsignedConstantContext;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.visitors.PascalVisitor;

public abstract class Constant extends Node {

    public Constant(BlockNode parent) {
        super(parent);
    }

    public static Constant buildAST(UnsignedConstantContext ctx, BlockNode parent) {
        if(ctx.unsignedNumber() != null) {
            return NumberConstant.buildAST(ctx.unsignedNumber(), parent);
        }
        else if(ctx.constantChr() != null) {
            CharConstant chrconst = new CharConstant(parent);
            chrconst.setValue(PascalVisitor.visitConstantChar(ctx.constantChr()));
            return chrconst;
        }
        else {
            StringConstant strconst = new StringConstant(parent);
            strconst.setValue(PascalVisitor.visitConstantString(ctx.string()));
            return strconst;
        }
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
