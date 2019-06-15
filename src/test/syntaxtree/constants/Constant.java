package test.syntaxtree.constants;

import gen.PascalParser.BoolContext;
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

    public static Constant buildAST(BoolContext ctx, BlockNode parent) {
        BoolConstant b = new BoolConstant(parent);
        b.setValue(PascalVisitor.visitBoolean(ctx));
        return b;
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
