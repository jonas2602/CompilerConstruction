package visitors;

import ast.*;
import ast.core.operators.Operator;
import ast.expressions.*;
import ast.types.*;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.LinkedList;
import java.util.List;

public class ExpressionVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    @Override
    public AbstractSyntaxTree visitExpression(PascalParser.ExpressionContext ctx) {
        AbstractSyntaxTree left = visitSimpleExpression(ctx.simpleExpression());
        if (ctx.relationaloperator() == null) {
            return left;
        }

        AbstractSyntaxTree right = visitExpression(ctx.expression());

        Operator operator = null;
        if (ctx.relationaloperator().EQUAL() != null) {
            operator = Operator.EQ;
        } else if (ctx.relationaloperator().NOT_EQUAL() != null) {
            operator = Operator.NE;
        } else if (ctx.relationaloperator().LT() != null) {
            operator = Operator.LT;
        } else if (ctx.relationaloperator().LE() != null) {
            operator = Operator.LE;
        } else if (ctx.relationaloperator().GT() != null) {
            operator = Operator.GT;
        } else if (ctx.relationaloperator().GE() != null) {
            operator = Operator.GE;
        } else if (ctx.relationaloperator().IN() != null) {
            operator = Operator.IN;
        }

        FuncCallNode funcCall = new FuncCallNode(operator);
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

        Operator operator = null;
        if (ctx.additiveoperator().PLUS() != null) {
            operator = Operator.ADD;
        } else if (ctx.additiveoperator().MINUS() != null) {
            operator = Operator.SUB;
        } else if (ctx.additiveoperator().OR() != null) {
            operator = Operator.OR;
        }

        FuncCallNode funcCall = new FuncCallNode(operator);
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

        AbstractSyntaxTree right = visitTerm(ctx.term());

        Operator operator = null;
        if (ctx.multiplicativeoperator().STAR() != null) {
            operator = Operator.MUL;
        } else if (ctx.multiplicativeoperator().SLASH() != null) {
            operator = Operator.DIV;
        } else if (ctx.multiplicativeoperator().DIV() != null) {
            operator = Operator.INTDIV;
        } else if (ctx.multiplicativeoperator().MOD() != null) {
            operator = Operator.MOD;
        } else if (ctx.multiplicativeoperator().AND() != null) {
            operator = Operator.AND;
        }

        FuncCallNode funcCall = new FuncCallNode(operator);
        funcCall.AddParameter(left);
        funcCall.AddParameter(right);
        return funcCall;
    }

    @Override
    public AbstractSyntaxTree visitSignedFactor(PascalParser.SignedFactorContext ctx) {
        AbstractSyntaxTree factor = visitFactor(ctx.factor());
        if (ctx.MINUS() != null) {
            FuncCallNode funcCall = new FuncCallNode(Operator.NEG);
            funcCall.AddParameter(factor);
            return funcCall;
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
            AbstractSyntaxTree factor = visitFactor(ctx.factor());
            FuncCallNode funcCall = new FuncCallNode(Operator.NEG);
            funcCall.AddParameter(factor);
            return funcCall;
        } else {
            return visitBool(ctx.bool());
        }
    }

    @Override
    public AbstractSyntaxTree visitSet(PascalParser.SetContext ctx) {
        List<AbstractSyntaxTree> list = new LinkedList<>();
        TypeVisitor visitor = new TypeVisitor(null);

        PascalParser.ElementListContext elemlist = ctx.elementList();
        for (PascalParser.ElementContext elem : elemlist.element()) {
            if (elem.constant() != null) {
                list.add(visitConstant(elem.constant()));
            } else {
                list.add(visitor.visitSubrangeType(elem.subrangeType()));
            }
        }

        return new ConstantNode_Set(list);
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
    public AbstractSyntaxTree visitConstant(PascalParser.ConstantContext ctx) {
        AbstractSyntaxTree constantNode;
        if (ctx.unsignedNumber() != null) {
            constantNode = visitUnsignedNumber(ctx.unsignedNumber());
        } else if (ctx.identifier() != null) {
            constantNode = new AccessNode_Variable(ctx.identifier().IDENT().getText());
        } else {
            return visitString(ctx.string());
        }

        if (ctx.sign() != null && ctx.sign().MINUS() != null) {
            FuncCallNode funcCall = new FuncCallNode(Operator.NEG);
            funcCall.AddParameter(constantNode);
            constantNode = funcCall;
        }

        return constantNode;
    }

    @Override
    public AbstractSyntaxTree visitUnsignedConstant(PascalParser.UnsignedConstantContext ctx) {
        if (ctx.unsignedNumber() != null) {
            return visitUnsignedNumber(ctx.unsignedNumber());
        } else if (ctx.string() != null) {
            return visitString(ctx.string());
        } else {
            return ConstantNode.NilNode;
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
    public AbstractSyntaxTree visitIdentifier(PascalParser.IdentifierContext ctx) {
        return new AccessNode_Variable(ctx.getText());
    }

    @Override
    public AbstractSyntaxTree visitUnsignedInteger(PascalParser.UnsignedIntegerContext ctx) {
        // TODO: Create Constant Node of type Integer
        return new ConstantNode(ctx.NUM_INT().getText(), PrimitiveTypeNode.ConstIntNode);
    }

    @Override
    public AbstractSyntaxTree visitUnsignedReal(PascalParser.UnsignedRealContext ctx) {
        // TODO: Create Constant Node of type Real
        double value = Double.parseDouble(ctx.NUM_REAL().getText());
        long bits = Double.doubleToLongBits(value);
        if ((bits & 0xFFFFFFFFl) != 0) {
            System.out.println("'" + value + "' should be interpreted as double because binary representation exeedes single precision float");
            return new ConstantNode(Double.toString(value), PrimitiveTypeNode.ConstDoubleNode);
        }

        return new ConstantNode(Double.toString(value), PrimitiveTypeNode.ConstFloatNode);
    }

    @Override
    public AbstractSyntaxTree visitString(PascalParser.StringContext ctx) {
        String data = ctx.STRING_LITERAL().getText();
        data = data.substring(1, data.length() - 1);

        if (data.length() == 1) {
            int numeric = (int) data.charAt(0);
            return new ConstantNode(Integer.toString(numeric), PrimitiveTypeNode.ConstCharNode);
        }
        return new ConstantNode_String(data);
    }

    @Override
    public AbstractSyntaxTree visitBool(PascalParser.BoolContext ctx) {
        String data = ctx.TRUE() != null ? "1" : "0";
        return new ConstantNode(data, PrimitiveTypeNode.ConstBoolNode);
    }
}
