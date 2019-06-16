package test.syntaxtree.constants;

import gen.PascalParser.BoolContext;
import gen.PascalParser.UnsignedConstantContext;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.visitors.PascalVisitor;

public abstract class Constant extends Node {

    public Constant(ScopeNode parent) {
        super(parent);
    }

    public static Constant buildAST(UnsignedConstantContext ctx, ScopeNode parent) {
        if(ctx.unsignedNumber() != null) {
            return NumberConstant.buildAST(ctx.unsignedNumber(), parent);
        }
        /*else if(ctx.constantChr() != null) {
            CharConstant chrconst = new CharConstant(parent);
            chrconst.setValue(PascalVisitor.visitConstantChar(ctx.constantChr()));
            return chrconst;
        }*/
        else {
            StringConstant strconst = new StringConstant(parent);
            strconst.setValue(PascalVisitor.visitConstantString(ctx.string()));
            return strconst;
        }
    }

    public static Constant buildAST(BoolContext ctx, ScopeNode parent) {
        BoolConstant b = new BoolConstant(parent);
        b.setValue(PascalVisitor.visitBoolean(ctx));
        return b;
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
