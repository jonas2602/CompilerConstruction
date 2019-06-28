package ast.core;

import ast.AbstractSyntaxTree;
import ast.EPrimitiveType;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.TypeContainer;

public class PascalType_Int extends PascalType_Primitive {
    public PascalType_Int() {
        super(EPrimitiveType.INT, 32, false, "%d", "0");
    }

    public static abstract class FuncDeclNode_IntParam extends PascalType_Operation {
        public FuncDeclNode_IntParam(String InName, TypeNode InReturnType, PrimitiveTypeNode rparam, FunctionCall operation) {
            super(InName, InReturnType, PrimitiveTypeNode.IntNode, rparam, operation);
        }
    }

    public static class FuncDeclNode_addInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_addInt() {
            super("operator+", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::AddIntInt);
        }
    }

    public static class FuncDeclNode_addFloat extends FuncDeclNode_IntParam {
        public FuncDeclNode_addFloat() {
            super("operator+", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                TypeContainer cast = slave.CastIntToFloat(lParam);
                return slave.AddFloatFloat(cast, rParam);
            });
        }
    }

    public static class FuncDeclNode_subInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_subInt() {
            super("operator-", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::SubIntInt);
        }
    }

    public static class FuncDeclNode_subFloat extends FuncDeclNode_IntParam {
        public FuncDeclNode_subFloat() {
            super("operator-", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                TypeContainer cast = slave.CastIntToFloat(lParam);
                return slave.SubFloatFloat(cast, rParam);
            });
        }
    }

    public static class FuncDeclNode_mulInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_mulInt() {
            super("operator*", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::MulIntInt);
        }
    }

    public static class FuncDeclNode_mulFloat extends FuncDeclNode_IntParam {
        public FuncDeclNode_mulFloat() {
            super("operator*", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                TypeContainer cast = slave.CastIntToFloat(lParam);
                return slave.MulFloatFloat(cast, rParam);
            });
        }
    }

    public static class FuncDeclNode_divInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_divInt() {
            super("operator/", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                TypeContainer castLeft = slave.CastIntToFloat(lParam);
                TypeContainer castRight = slave.CastIntToFloat(rParam);
                return slave.DivFloatFloat(castLeft, castRight);
            });
        }
    }

    public static class FuncDeclNode_divFloat extends FuncDeclNode_IntParam {
        public FuncDeclNode_divFloat() {
            super("operator/", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
                TypeContainer cast = slave.CastIntToFloat(lParam);
                return slave.DivFloatFloat(cast, rParam);
            });
        }
    }

    public static class FuncDeclNode_divTrucInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_divTrucInt() {
            super("operatordiv", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::DivIntInt);
        }
    }

    public static class FuncDeclNode_modInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_modInt() {
            super("operatormod", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, GeneratorSlave::ModIntInt);
        }
    }

    public static class FuncDeclNode_eqInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_eqInt() {
            super("operatoreq", PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntEQ);
        }
    }

    public static class FuncDeclNode_neInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_neInt() {
            super("operatorne", PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntNE);
        }
    }

    public static class FuncDeclNode_ltInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_ltInt() {
            super("operatorlt", PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntLT);
        }
    }

    public static class FuncDeclNode_leInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_leInt() {
            super("operatorle", PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntLE);
        }
    }

    public static class FuncDeclNode_gtInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_gtInt() {
            super("operatorgt", PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntGT);
        }
    }

    public static class FuncDeclNode_geInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_geInt() {
            super("operatorge", PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.IntNode, GeneratorSlave::IntGE);
        }
    }
}
