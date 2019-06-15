package test.syntaxtree.statements.expressions;

import gen.PascalParser.*;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.statements.expressions.operators.Operator;
import test.syntaxtree.statements.expressions.operators.additiveoperators.MinusOperator;
import test.syntaxtree.statements.expressions.operators.additiveoperators.OrOperator;
import test.syntaxtree.statements.expressions.operators.additiveoperators.PlusOperator;
import test.syntaxtree.statements.expressions.operators.multiplicativeoperators.*;
import test.syntaxtree.statements.expressions.operators.relationaloperators.*;

public class Expression {
    public Node visitExpression(ExpressionContext ctx, BlockNode parent) {
        Node left = visitSimpleExpression(ctx.simpleExpression(), parent);

        if(ctx.relationaloperator() == null) {
            return left;
        }

        Node right = visitExpression(ctx.expression(), parent);

        Operator op = null;

        if(ctx.relationaloperator().EQUAL() != null) {
            op = new EqualOperator(parent);
        }
        else if(ctx.relationaloperator().NOT_EQUAL() != null) {
            op = new NotEqualOperator(parent);
        }
        else if(ctx.relationaloperator().LT() != null) {
            op = new LowerThanOperator(parent);
        }
        else if(ctx.relationaloperator().LE() != null) {
            op = new LowerEqualThanOperator(parent);
        }
        else if(ctx.relationaloperator().GT() != null) {
            op = new GreaterThanOperator(parent);
        }
        else if(ctx.relationaloperator().GE() != null) {
            op = new GreaterEqualThanOperator(parent);
        }
        else {
            op = new InOperator(parent);
        }

        op.setLeft(left);
        op.setRight(right);
        return op;
    }

    public static Node visitSimpleExpression(SimpleExpressionContext ctx, BlockNode parent) {
        Node left = visitTerm(ctx.term(), parent);

        if(ctx.additiveoperator() == null) {
            return left;
        }

        Node right = visitSimpleExpression(ctx.simpleExpression(), parent);

        Operator op = null;

        if(ctx.additiveoperator().PLUS() != null) {
            op = new PlusOperator(parent);
        }
        else if(ctx.additiveoperator().MINUS() != null) {
            op = new MinusOperator(parent);
        }
        else if(ctx.additiveoperator().OR() != null) {
            op = new OrOperator(parent);
        }

        op.setLeft(left);
        op.setRight(right);
        return op;
    }

    public static Node visitTerm(TermContext ctx, BlockNode parent) {
        Node left = visitSignedFactor(ctx.signedFactor(), parent);

        if(ctx.multiplicativeoperator() == null) {
            return left;
        }

        Node right = visitTerm(ctx.term(), parent);

        Operator op = null;
        if(ctx.multiplicativeoperator().STAR() != null) {
            op = new MultiplicativeOperator(parent);
        }
        else if(ctx.multiplicativeoperator().SLASH() != null) {
            op = new RealDivOperator(parent);
        }
        else if(ctx.multiplicativeoperator().DIV() != null) {
            op = new IntDivOperator(parent);
        }
        else if(ctx.multiplicativeoperator().MOD() != null) {
            op = new ModOperator(parent);
        }
        else if(ctx.multiplicativeoperator().AND() != null) {
            op = new AndOperator(parent);
        }

        op.setLeft(left);
        op.setRight(right);
        return op;
    }

    public static Node visitSignedFactor(SignedFactorContext ctx, BlockNode parent) {
        Node factor = visitFactor(ctx.factor(), parent);
        if(ctx.MINUS() != null) {
            MinusSign minus = new MinusSign(parent);
            minus.setTerm(factor);
            return minus;
        }

        return factor;
    }

    public static Node visitFactor(FactorContext ctx, BlockNode parent) {
        if(ctx.variable() != null) {
            //TODO
        }
        else if(ctx.expression() != null) {
            //TODO
        }
        else if(ctx.functionDesignator() != null) {
            //TODO
        }
        else if(ctx.set() != null) {
            //TODO
        }
        else if(ctx.unsignedConstant() != null) {
            //TODO
        }
        else if(ctx.factor() != null) {
            return visitFactor(ctx.factor(), parent);
        }
        else {
            return null;
        }

        return null;
    }
}
