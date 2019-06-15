package test.syntaxtree.constants;

import gen.PascalParser.UnsignedNumberContext;
import test.syntaxtree.BlockNode;
import test.visitors.PascalVisitor;

public abstract class NumberConstant extends Constant {

    public NumberConstant(BlockNode parent) {
        super(parent);
    }

    public static NumberConstant buildAST(UnsignedNumberContext ctx, BlockNode parent) {
        if(ctx.unsignedInteger() != null) {
            IntConstant i = new IntConstant(parent);
            i.setValue(PascalVisitor.visitUnsignedInt(ctx.unsignedInteger()));
            return i;
        }

        RealConstant r = new RealConstant(parent);
        r.setValue(PascalVisitor.visitUnsignedReal(ctx.unsignedReal()));
        return r;
    }
}
