package ast.core.operators;

import ast.BlockNode;
import ast.core.StdBuilder;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class FloatOperators implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        //prefix operators
        std.AddFunctionDeclaration(new NegateFloat());

        //post operators
        std.AddFunctionDeclaration(new IncFloat());
        std.AddFunctionDeclaration(new DecFloat());

        std.AddFunctionDeclaration(new AddFloat());
        std.AddFunctionDeclaration(new AddInt());
        std.AddFunctionDeclaration(new SubFloat());
        std.AddFunctionDeclaration(new SubInt());
        std.AddFunctionDeclaration(new MulFloat());
        std.AddFunctionDeclaration(new MulInt());
        std.AddFunctionDeclaration(new DivFloat());
        std.AddFunctionDeclaration(new DivInt());
    }

    public static class NegateFloat extends PascalType_Prefix {
        public NegateFloat() {
            super(Operator.NEG, PrimitiveTypeNode.FloatNode, GeneratorSlave::NegateFloat);
        }
    }

    public static class IncFloat extends PascalType_Prefix {
        public IncFloat() {
            super(Operator.INC, PrimitiveTypeNode.FloatNode, GeneratorSlave::IncFloat);
        }
    }

    public static class DecFloat extends PascalType_Prefix {
        public DecFloat() {
            super(Operator.DEC, PrimitiveTypeNode.FloatNode, GeneratorSlave::DecFloat);
        }
    }

    public static abstract class FloatOperator extends PascalType_Operator {
        public FloatOperator(Operator operator, PrimitiveTypeNode rparam, FunctionCallTwoParams operation) {
            super(operator, PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, rparam, operation);
        }
    }

    public static class AddFloat extends FloatOperator {
        public AddFloat() {
            super(Operator.ADD, PrimitiveTypeNode.FloatNode, GeneratorSlave::AddFloatFloat);
        }
    }

    public static class AddInt extends FloatOperator {
        public AddInt() {
            super(Operator.ADD, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.AddFloatFloat(lParam, cast);
            });
        }
    }

    public static class SubFloat extends FloatOperator {
        public SubFloat() {
            super(Operator.SUB, PrimitiveTypeNode.FloatNode, GeneratorSlave::SubFloatFloat);
        }
    }

    public static class SubInt extends FloatOperator {
        public SubInt() {
            super(Operator.SUB, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.SubFloatFloat(lParam, cast);
            });
        }
    }

    public static class MulFloat extends FloatOperator {
        public MulFloat() {
            super(Operator.MUL, PrimitiveTypeNode.FloatNode, GeneratorSlave::MulFloatFloat);
        }
    }

    public static class MulInt extends FloatOperator {
        public MulInt() {
            super(Operator.MUL, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.MulFloatFloat(lParam, cast);
            });
        }
    }

    public static class DivFloat extends FloatOperator {
        public DivFloat() {
            super(Operator.DIV, PrimitiveTypeNode.FloatNode, GeneratorSlave::DivFloatFloat);
        }
    }

    public static class DivInt extends FloatOperator {
        public DivInt() {
            super(Operator.DIV, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.DivFloatFloat(lParam, cast);
            });
        }
    }
}
