package visitors;

import ast.*;
import ast.expressions.*;
import ast.types.NamedTypeNode;
import ast.types.PrimitiveTypeNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.List;

public class ExpressionVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    @Override
    public AbstractSyntaxTree visitExpression(PascalParser.ExpressionContext ctx) {
        AbstractSyntaxTree left = visitSimpleExpression(ctx.simpleExpression());
        if (ctx.relationaloperator() == null) {
            return left;
        }

        AbstractSyntaxTree right = visitExpression(ctx.expression());
        FuncCallNode funcCall = null;

        if (ctx.relationaloperator().EQUAL() != null) {
            funcCall = new FuncCallNode("operatoreq");
        } else if (ctx.relationaloperator().NOT_EQUAL() != null) {
            funcCall = new FuncCallNode("operatorne");
        } else if (ctx.relationaloperator().LT() != null) {
            funcCall = new FuncCallNode("operatorlt");
        } else if (ctx.relationaloperator().LE() != null) {
            funcCall = new FuncCallNode("operatorle");
        } else if (ctx.relationaloperator().GT() != null) {
            funcCall = new FuncCallNode("operatorgt");
        } else if (ctx.relationaloperator().GE() != null) {
            funcCall = new FuncCallNode("operatorge");
        } else if (ctx.relationaloperator().IN() != null) {
            funcCall = new FuncCallNode("operatorin");
        }

        funcCall.AddParameter(left);
        funcCall.AddParameter(right);
        return funcCall;
    }

    @Override
    public AbstractSyntaxTree visitSimpleExpression(PascalParser.SimpleExpressionContext ctx) {
        AbstractSyntaxTree left = visitTerm(ctx.term());
        if (ctx.additiveoperator() == null) {
            return left;
        }
        AbstractSyntaxTree right = visitSimpleExpression(ctx.simpleExpression());

        FuncCallNode funcCall = null;
        if (ctx.additiveoperator().PLUS() != null) {
            funcCall = new FuncCallNode("operator+");
        } else if (ctx.additiveoperator().MINUS() != null) {
            funcCall = new FuncCallNode("operator-");
        } else if (ctx.additiveoperator().OR() != null) {
            funcCall = new FuncCallNode("operator||");
        }

        funcCall.AddParameter(left);
        funcCall.AddParameter(right);
        return funcCall;
    }

    @Override
    public AbstractSyntaxTree visitTerm(PascalParser.TermContext ctx) {
        AbstractSyntaxTree left = visitSignedFactor(ctx.signedFactor());
        if (ctx.multiplicativeoperator() == null) {
            return left;
        }

        FuncCallNode funcCall = null;
        AbstractSyntaxTree right = visitTerm(ctx.term());
        if (ctx.multiplicativeoperator().STAR() != null) {
            funcCall = new FuncCallNode("operator*");
        } else if (ctx.multiplicativeoperator().SLASH() != null) {
            funcCall = new FuncCallNode("operator/");
        } else if (ctx.multiplicativeoperator().DIV() != null) {
            funcCall = new FuncCallNode("operatordiv");
        } else if (ctx.multiplicativeoperator().MOD() != null) {
            funcCall = new FuncCallNode("operatormod");
        } else if (ctx.multiplicativeoperator().AND() != null) {
            funcCall = new FuncCallNode("operator&&");
        }

        funcCall.AddParameter(left);
        funcCall.AddParameter(right);
        return funcCall;
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
        String funcName = ctx.identifier().IDENT().getText();
        FuncCallNode funcCall = new FuncCallNode(funcName);

        if (ctx.parameterList() != null) {
            List<AbstractSyntaxTree> paramList = new ParameterVisitor().visit(ctx.parameterList());
            for (AbstractSyntaxTree param : paramList) {
                funcCall.AddParameter(param);
            }
        }

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
        return new ConstantNode(ctx.NUM_INT().getText(), PrimitiveTypeNode.IntNode);
    }

    @Override
    public AbstractSyntaxTree visitUnsignedReal(PascalParser.UnsignedRealContext ctx) {
        // TODO: Create Constant Node of type Real
        double value = Double.parseDouble(ctx.NUM_REAL().getText());
        long bits = Double.doubleToLongBits(value);
        if ((bits & 0xFFFFFFFFl) != 0) {
            System.out.println("'" + value + "' should be interpreted as double because binary representation exeedes single precision float");
        }

        return new ConstantNode(ctx.NUM_REAL().getText(), PrimitiveTypeNode.FloatNode);
    }

    @Override
    public AbstractSyntaxTree visitConstantChr(PascalParser.ConstantChrContext ctx) {
        // TODO: Create Constant Node of type Char
        // TODO: Convert to char?
        return new ConstantNode(ctx.unsignedInteger().getText(), PrimitiveTypeNode.CharNode);
    }

    @Override
    public AbstractSyntaxTree visitString(PascalParser.StringContext ctx) {
        String data = ctx.STRING_LITERAL().getText();
        data = data.substring(1, data.length() - 1);

        if (data.length() == 1) {
            int numeric = (int) data.charAt(0);
            return new ConstantNode(Integer.toString(numeric), PrimitiveTypeNode.CharNode);
        }
        return new ConstantNode_String(data);
    }

    @Override
    public AbstractSyntaxTree visitBool(PascalParser.BoolContext ctx) {
        String data = ctx.TRUE() != null ? "1" : "0";
        return new ConstantNode(data, PrimitiveTypeNode.BoolNode);
    }
}
