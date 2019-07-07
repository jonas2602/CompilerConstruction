package ast.core.operators;

import ast.core.StdBuilder;
import ast.BlockNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class IntOperators implements StdBuilder {

    public void buildStd(BlockNode std) {
        //prefix operators
        std.AddFunctionDeclaration(new NegateInt());

        //postfix operators
        std.AddFunctionDeclaration(new IncInt());
        std.AddFunctionDeclaration(new DecInt());

        //'normal' int operators
        std.AddFunctionDeclaration(new AddInt());
        std.AddFunctionDeclaration(new AddFloat());
        std.AddFunctionDeclaration(new SubInt());
        std.AddFunctionDeclaration(new SubFloat());
        std.AddFunctionDeclaration(new MulInt());
        std.AddFunctionDeclaration(new MulFloat());
        std.AddFunctionDeclaration(new DivInt());
        std.AddFunctionDeclaration(new DivFloat());

        //'special' int operators
        std.AddFunctionDeclaration(new DivTrucInt());
        std.AddFunctionDeclaration(new ModInt());

        //comp operators
        std.AddFunctionDeclaration(new EQInt());
        std.AddFunctionDeclaration(new NEInt());
        std.AddFunctionDeclaration(new LTInt());
        std.AddFunctionDeclaration(new LEInt());
        std.AddFunctionDeclaration(new GTInt());
        std.AddFunctionDeclaration(new GEInt());

        std.AddFunctionDeclaration(new EQFloat());
        std.AddFunctionDeclaration(new NEFloat());
        std.AddFunctionDeclaration(new LTFloat());
        std.AddFunctionDeclaration(new LEFloat());
        std.AddFunctionDeclaration(new GTFloat());
        std.AddFunctionDeclaration(new GEFloat());
    }

    public static class NegateInt extends PascalType_Prefix {
        public NegateInt() {
            super(Operator.NEG, PrimitiveTypeNode.IntNode, GeneratorSlave::NegateInt);
        }
    }

    public static class IncInt extends PascalType_Prefix {
        public IncInt() {
            super(Operator.INC, PrimitiveTypeNode.IntNode, GeneratorSlave::IncInt);
        }
    }

    public static class DecInt extends PascalType_Prefix {
        public DecInt() {
            super(Operator.DEC, PrimitiveTypeNode.IntNode, GeneratorSlave::DecInt);
        }
    }

    public static abstract class IntOperator extends PascalType_Operator {
        public IntOperator(Operator operator, TypeNode returnType, TypeNode rparam, FunctionCallTwoParams operation) {
            super(operator, returnType, PrimitiveTypeNode.IntNode, rparam, operation);
        }
    }

    public static class AddInt extends IntOperator {
        public AddInt() {
            super(Operator.ADD, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::AddIntInt);
        }
    }

    public static class AddFloat extends IntOperator {
        public AddFloat() {
            super(Operator.ADD, PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.AddFloatFloat(cast, rParam);
            });
        }
    }

    public static class SubInt extends IntOperator {
        public SubInt() {
            super(Operator.SUB, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::SubIntInt);
        }
    }

    public static class SubFloat extends IntOperator {
        public SubFloat() {
            super(Operator.SUB, PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.SubFloatFloat(cast, rParam);
            });
        }
    }

    public static class MulInt extends IntOperator {
        public MulInt() {
            super(Operator.MUL, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::MulIntInt);
        }
    }

    public static class MulFloat extends IntOperator {
        public MulFloat() {
            super(Operator.MUL, PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.MulFloatFloat(cast, rParam);
            });
        }
    }

    public static class DivInt extends IntOperator {
        public DivInt() {
            super(Operator.DIV, PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer castLeft = slave.CastIntToFloat(lParam);
                ParamContainer castRight = slave.CastIntToFloat(rParam);
                return slave.DivFloatFloat(castLeft, castRight);
            });
        }
    }

    public static class DivFloat extends IntOperator {
        public DivFloat() {
            super(Operator.DIV, PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.DivFloatFloat(cast, rParam);
            });
        }
    }

    public static class DivTrucInt extends IntOperator {
        public DivTrucInt() {
            super(Operator.INTDIV, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::DivIntInt);
        }
    }

    public static class ModInt extends IntOperator {
        public ModInt() {
            super(Operator.MOD, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::ModIntInt);
        }
    }

    public static class EQInt extends IntOperator {
        public EQInt() {
            super(Operator.EQ, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntEQ);
        }
    }

    public static class NEInt extends IntOperator {
        public NEInt() {
            super(Operator.NE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntNE);
        }
    }

    public static class LTInt extends IntOperator {
        public LTInt() {
            super(Operator.LT, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntLT);
        }
    }

    public static class LEInt extends IntOperator {
        public LEInt() {
            super(Operator.LE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntLE);
        }
    }

    public static class GTInt extends IntOperator {
        public GTInt() {
            super(Operator.GT, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntGT);
        }
    }

    public static class GEInt extends IntOperator {
        public GEInt() {
            super(Operator.GE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntGE);
        }
    }

    public static class EQFloat extends IntOperator {
        public EQFloat() {
            super(Operator.EQ, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.FloatEQ(cast, rParam);
            });
        }
    }

    public static class NEFloat extends IntOperator {
        public NEFloat() {
            super(Operator.NE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.FloatNE(cast, rParam);
            });
        }
    }

    public static class LTFloat extends IntOperator {
        public LTFloat() {
            super(Operator.LT, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.FloatLT(cast, rParam);
            });
        }
    }

    public static class LEFloat extends IntOperator {
        public LEFloat() {
            super(Operator.LE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.FloatLE(cast, rParam);
            });
        }
    }

    public static class GTFloat extends IntOperator {
        public GTFloat() {
            super(Operator.GT, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.FloatGT(cast, rParam);
            });
        }
    }

    public static class GEFloat extends IntOperator {
        public GEFloat() {
            super(Operator.GE, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(lParam);
                return slave.FloatGE(cast, rParam);
            });
        }
    }
}
