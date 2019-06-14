package visitors;

import ast.*;
import ast.expression.*;
import ast.statement.ProcCallNode;
import ast.types.NamedTypeNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;
import java.util.List;

public class ExpressionVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    @Override
    public AbstractSyntaxTree visitExpression(PascalParser.ExpressionContext ctx) {
        AbstractSyntaxTree left = visitSimpleExpression(ctx.simpleExpression());
        if (ctx.relationaloperator() == null) {
            return left;
        }

        AbstractSyntaxTree right = visitExpression(ctx.expression());
        RelationalNode.ERelationalOperator operator = RelationalNode.ERelationalOperator.EQUAL;
        if (ctx.relationaloperator().EQUAL() != null) {
            operator = RelationalNode.ERelationalOperator.EQUAL;
        } else if (ctx.relationaloperator().NOT_EQUAL() != null) {
            operator = RelationalNode.ERelationalOperator.NOTEQUAL;
        } else if (ctx.relationaloperator().LT() != null) {
            operator = RelationalNode.ERelationalOperator.LESS;
        } else if (ctx.relationaloperator().LE() != null) {
            operator = RelationalNode.ERelationalOperator.LESSEQUAL;
        } else if (ctx.relationaloperator().GT() != null) {
            operator = RelationalNode.ERelationalOperator.GREATER;
        } else if (ctx.relationaloperator().GE() != null) {
            operator = RelationalNode.ERelationalOperator.GREATEREQUAL;
        } else if (ctx.relationaloperator().IN() != null) {
            operator = RelationalNode.ERelationalOperator.IN;
        }

        return new RelationalNode(left, right, operator);
    }

    @Override
    public AbstractSyntaxTree visitSimpleExpression(PascalParser.SimpleExpressionContext ctx) {
        AbstractSyntaxTree left = visitTerm(ctx.term());
        if (ctx.additiveoperator() == null) {
            return left;
        }

        AbstractSyntaxTree right = visitSimpleExpression(ctx.simpleExpression());
        AdditiveNode.EAdditiveOperator operator = AdditiveNode.EAdditiveOperator.PLUS;
        if (ctx.additiveoperator().PLUS() != null) {
            operator = AdditiveNode.EAdditiveOperator.PLUS;
        } else if (ctx.additiveoperator().MINUS() != null) {
            operator = AdditiveNode.EAdditiveOperator.MINUS;
        } else if (ctx.additiveoperator().OR() != null) {
            operator = AdditiveNode.EAdditiveOperator.OR;
        }

        return new AdditiveNode(left, right, operator);
    }

    @Override
    public AbstractSyntaxTree visitTerm(PascalParser.TermContext ctx) {
        AbstractSyntaxTree left = visitSignedFactor(ctx.signedFactor());
        if (ctx.multiplicativeoperator() == null) {
            return left;
        }

        AbstractSyntaxTree right = visitTerm(ctx.term());
        MultiplicativeNode.EMultiplicativeOperator operator = MultiplicativeNode.EMultiplicativeOperator.STAR;
        if (ctx.multiplicativeoperator().STAR() != null) {
            operator = MultiplicativeNode.EMultiplicativeOperator.STAR;
        } else if (ctx.multiplicativeoperator().SLASH() != null) {
            operator = MultiplicativeNode.EMultiplicativeOperator.SLASH;
        } else if (ctx.multiplicativeoperator().DIV() != null) {
            operator = MultiplicativeNode.EMultiplicativeOperator.DIV;
        } else if (ctx.multiplicativeoperator().MOD() != null) {
            operator = MultiplicativeNode.EMultiplicativeOperator.MOD;
        } else if (ctx.multiplicativeoperator().AND() != null) {
            operator = MultiplicativeNode.EMultiplicativeOperator.AND;
        }

        return new MultiplicativeNode(left, right, operator);
    }

    @Override
    public AbstractSyntaxTree visitSignedFactor(PascalParser.SignedFactorContext ctx) {
        AbstractSyntaxTree factor = visitFactor(ctx.factor());
        if (ctx.MINUS() != null) {
            // TODO: negate factor
        }

        return factor;
    }

    @Override
    public AbstractSyntaxTree visitFactor(PascalParser.FactorContext ctx) {
        if (ctx.variable() != null) {
            return new VariableAccessVisitor().visit(ctx.variable());
        } else if (ctx.expression() != null) {
            return visitExpression(ctx.expression());
        } else if (ctx.functionDesignator() != null) {
            return visitFunctionDesignator(ctx.functionDesignator());
        } else if (ctx.unsignedConstant() != null) {
            return visitUnsignedConstant(ctx.unsignedConstant());
        } else if (ctx.set() != null) {
            return visitSet(ctx.set());
        } else if (ctx.factor() != null) {
            // TODO: Negate factor
            return visitFactor(ctx.factor());
        } else {
            return visitBool(ctx.bool());
        }
    }

    @Override
    public AbstractSyntaxTree visitFunctionDesignator(PascalParser.FunctionDesignatorContext ctx) {
        String procName = ctx.identifier().IDENT().getText();
        List<AbstractSyntaxTree> params = new ArrayList<>();
        if (ctx.parameterList() != null) {
            params = new ParameterVisitor().visit(ctx.parameterList());
        }

        FuncCallNode funcCall = new FuncCallNode(procName, params);
        return funcCall;
    }

    @Override
    public AbstractSyntaxTree visitUnsignedConstant(PascalParser.UnsignedConstantContext ctx) {
        if (ctx.unsignedNumber() != null) {
            return visitUnsignedNumber(ctx.unsignedNumber());
        } else if (ctx.constantChr() != null) {
            return visitConstantChr(ctx.constantChr());
        } else if (ctx.string() != null) {
            return visitString(ctx.string());
        } else {
            // TODO: NIL?
            return null;
        }
    }

    @Override
    public AbstractSyntaxTree visitUnsignedNumber(PascalParser.UnsignedNumberContext ctx) {
        if (ctx.unsignedInteger() != null) {
            return visitUnsignedInteger(ctx.unsignedInteger());
        } else {
            return visitUnsignedReal(ctx.unsignedReal());
        }
    }

    @Override
    public AbstractSyntaxTree visitUnsignedInteger(PascalParser.UnsignedIntegerContext ctx) {
        // TODO: Create Constant Node of type Integer
        return new ConstantNode(ctx.NUM_INT().getText(), new NamedTypeNode(EPrimitiveType.INT));
    }

    @Override
    public AbstractSyntaxTree visitUnsignedReal(PascalParser.UnsignedRealContext ctx) {
        // TODO: Create Constant Node of type Real
        return new ConstantNode(ctx.NUM_REAL().getText(), new NamedTypeNode(EPrimitiveType.REAL));
    }

    @Override
    public AbstractSyntaxTree visitConstantChr(PascalParser.ConstantChrContext ctx) {
        // TODO: Create Constant Node of type Char
        // TODO: Convert to char?
        return new ConstantNode(ctx.unsignedInteger().getText(), new NamedTypeNode(EPrimitiveType.CHAR));
    }

    @Override
    public AbstractSyntaxTree visitString(PascalParser.StringContext ctx) {
        // TODO: Create Constant Node of type String
        return new ConstantNode(ctx.STRING_LITERAL().getText(), new NamedTypeNode(EPrimitiveType.STRING));
    }
}
