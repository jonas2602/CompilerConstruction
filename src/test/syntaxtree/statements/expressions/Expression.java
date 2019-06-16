package test.syntaxtree.statements.expressions;

import gen.PascalParser.*;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.Constant;
import test.syntaxtree.statements.calls.FunctionCall;
import test.syntaxtree.statements.expressions.operators.Operator;
import test.syntaxtree.statements.expressions.operators.additiveoperators.MinusOperator;
import test.syntaxtree.statements.expressions.operators.additiveoperators.OrOperator;
import test.syntaxtree.statements.expressions.operators.additiveoperators.PlusOperator;
import test.syntaxtree.statements.expressions.operators.multiplicativeoperators.*;
import test.syntaxtree.statements.expressions.operators.relationaloperators.*;
import test.syntaxtree.statements.variables.PointerVariable;
import test.syntaxtree.statements.variables.VariableNode;

public class Expression {
    public static Node visitExpression(ExpressionContext ctx, ScopeNode parent) {
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
        return op.check();
    }

    public static Node visitSimpleExpression(SimpleExpressionContext ctx, ScopeNode parent) {
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
        return op.check();
    }

    public static Node visitTerm(TermContext ctx, ScopeNode parent) {
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
        return op.check();
    }

    public static Node visitSignedFactor(SignedFactorContext ctx, ScopeNode parent) {
        Node factor = visitFactor(ctx.factor(), parent);
        if(ctx.MINUS() != null) {
            MinusSign minus = new MinusSign(parent);
            minus.setTerm(factor);
            return minus.check();
        }

        return factor;
    }

    public static Node visitFactor(FactorContext ctx, ScopeNode parent) {
        if(ctx.variable() != null) {
            VariableNode variable = new VariableNode(parent);
            if(ctx.variable().AT() != null) {
                variable = new PointerVariable(parent);
            }
            variable.buildAST(ctx.variable());
            return variable;
        }
        else if(ctx.expression() != null) {
            return visitExpression(ctx.expression(), parent);
        }
        else if(ctx.functionDesignator() != null) {
            FunctionCall func = new FunctionCall(parent);
            func.buildAST(ctx.functionDesignator());
            return func;
        }
        else if(ctx.unsignedConstant() != null) {
            return Constant.buildAST(ctx.unsignedConstant(), parent);
        }
        else if(ctx.set() != null) {
            //TODO
            return null;
        }
        else if(ctx.factor() != null) {
            NotSign not = new NotSign(parent);
            not.setTerm(visitFactor(ctx.factor(), parent));
            return not.check();
        }
        else {
            return Constant.buildAST(ctx.bool(), parent);
        }
    }
}
