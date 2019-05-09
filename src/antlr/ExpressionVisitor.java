package antlr;

import gen.GramBaseVisitor;
import gen.GramParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class ExpressionVisitor extends GramBaseVisitor<Float> {
    @Override
    public Float visitExpression(GramParser.ExpressionContext ctx) {
        System.out.println("Visit expression");

        return new ExpressionVisitor().visit(ctx.additive());
    }

    @Override
    public Float visitAdditive(GramParser.AdditiveContext ctx) {
        System.out.println("Visit additive");

        ParseTree childTree = ctx.getChild(0);
        return new ExpressionVisitor().visit(childTree);
    }

    @Override
    public Float visitSum(GramParser.SumContext ctx) {
        System.out.println("Visit sum");

        Float mult = new ExpressionVisitor().visit(ctx.multiplicative());
        Float add = new ExpressionVisitor().visit(ctx.additive());
        return mult + add;
    }

    @Override
    public Float visitSubtract(GramParser.SubtractContext ctx) {
        System.out.println("Visit subtract");

        Float mult = new ExpressionVisitor().visit(ctx.multiplicative());
        Float add = new ExpressionVisitor().visit(ctx.additive());
        return mult - add;
    }

    @Override
    public Float visitMultiplicative(GramParser.MultiplicativeContext ctx) {
        System.out.println("Visit multiplicative");

        ParseTree childTree = ctx.getChild(0);
        return new ExpressionVisitor().visit(childTree);
    }

    @Override
    public Float visitProduct(GramParser.ProductContext ctx) {
        System.out.println("Visit product");

        Float cons = new ExpressionVisitor().visit(ctx.constant());
        Float mult = new ExpressionVisitor().visit(ctx.multiplicative());
        return cons * mult;
    }

    @Override
    public Float visitDivide(GramParser.DivideContext ctx) {
        System.out.println("Visit divide");

        Float cons = new ExpressionVisitor().visit(ctx.constant());
        Float mult = new ExpressionVisitor().visit(ctx.multiplicative());
        return cons / mult;
    }

    @Override
    public Float visitConstant(GramParser.ConstantContext ctx) {
        System.out.println("Visit constant");

        if (ctx.additive() != null)
        {
            return new ExpressionVisitor().visit(ctx.additive());
        }
        else
        {
            return Float.valueOf(ctx.NUMBER().getText());
        }
    }
}
