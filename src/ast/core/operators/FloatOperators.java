package ast.core.operators;

import ast.BlockNode;
import ast.core.StdBuilder;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
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

        //comp operators
        std.AddFunctionDeclaration(new EQFloat());
        std.AddFunctionDeclaration(new NEFloat());
        std.AddFunctionDeclaration(new LTFloat());
        std.AddFunctionDeclaration(new LEFloat());
        std.AddFunctionDeclaration(new GTFloat());
        std.AddFunctionDeclaration(new GEFloat());

        std.AddFunctionDeclaration(new EQInt());
        std.AddFunctionDeclaration(new NEInt());
        std.AddFunctionDeclaration(new LTInt());
        std.AddFunctionDeclaration(new LEInt());
        std.AddFunctionDeclaration(new GTInt());
        std.AddFunctionDeclaration(new GEInt());
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
        public FloatOperator(Operator operator, TypeNode rparam, FunctionCallTwoParams operation) {
            super(operator, PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, rparam, operation);
        }

        public FloatOperator(Operator operator, TypeNode returnType, TypeNode rparam, FunctionCallTwoParams operation) {
            super(operator, returnType, PrimitiveTypeNode.FloatNode, rparam, operation);
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

    public static class EQFloat extends FloatOperator {
        public EQFloat() {
            super(Operator.EQ, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, GeneratorSlave::FloatEQ);
        }
    }

    public static class NEFloat extends FloatOperator {
        public NEFloat() {
            super(Operator.NE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, GeneratorSlave::FloatNE);
        }
    }

    public static class LTFloat extends FloatOperator {
        public LTFloat() {
            super(Operator.LT, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, GeneratorSlave::FloatLT);
        }
    }

    public static class LEFloat extends FloatOperator {
        public LEFloat() {
            super(Operator.LE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, GeneratorSlave::FloatLE);
        }
    }

    public static class GTFloat extends FloatOperator {
        public GTFloat() {
            super(Operator.GT, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, GeneratorSlave::FloatGT);
        }
    }

    public static class GEFloat extends FloatOperator {
        public GEFloat() {
            super(Operator.GE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, GeneratorSlave::FloatGE);
        }
    }

    public static class EQInt extends FloatOperator {
        public EQInt() {
            super(Operator.EQ, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.FloatEQ(lParam, cast);
            });
        }
    }

    public static class NEInt extends FloatOperator {
        public NEInt() {
            super(Operator.NE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.FloatNE(lParam, cast);
            });
        }
    }

    public static class LTInt extends FloatOperator {
        public LTInt() {
            super(Operator.LT, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.FloatLT(lParam, cast);
            });
        }
    }

    public static class LEInt extends FloatOperator {
        public LEInt() {
            super(Operator.LE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.FloatLE(lParam, cast);
            });
        }
    }

    public static class GTInt extends FloatOperator {
        public GTInt() {
            super(Operator.GT, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.FloatGT(lParam, cast);
            });
        }
    }

    public static class GEInt extends FloatOperator {
        public GEInt() {
            super(Operator.GE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.FloatGE(lParam, cast);
            });
        }
    }
}
