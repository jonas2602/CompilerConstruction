package ast.core;

import ast.EPrimitiveType;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class PascalType_Float extends PascalType_Primitive {
    public PascalType_Float() {
        super(EPrimitiveType.FLOAT, 32, true, "%f", "0.0");
    }

    public static abstract class FuncDeclNode_FloatParam extends PascalType_Operation {
        public FuncDeclNode_FloatParam(String InName, PrimitiveTypeNode rparam, FunctionCall operation) {
            super(InName, PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, rparam, operation);
        }
    }

    public static class FuncDeclNode_addFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_addFloat() {
            super("operator+", PrimitiveTypeNode.FloatNode, GeneratorSlave::AddFloatFloat);
        }
    }

    public static class FuncDeclNode_addInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_addInt() {
            super("operator+", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.AddFloatFloat(lParam, cast);
            });
        }
    }

    public static class FuncDeclNode_subInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_subInt() {
            super("operator-", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.SubFloatFloat(lParam, cast);
            });
        }
    }

    public static class FuncDeclNode_subFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_subFloat() {
            super("operator-", PrimitiveTypeNode.FloatNode, GeneratorSlave::SubFloatFloat);
        }
    }

    public static class FuncDeclNode_mulInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_mulInt() {
            super("operator*", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.MulFloatFloat(lParam, cast);
            });
        }
    }

    public static class FuncDeclNode_mulFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_mulFloat() {
            super("operator*", PrimitiveTypeNode.FloatNode, GeneratorSlave::MulFloatFloat);
        }
    }

    public static class FuncDeclNode_divFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_divFloat() {
            super("operator/", PrimitiveTypeNode.FloatNode, GeneratorSlave::DivFloatFloat);
        }
    }

    public static class FuncDeclNode_divInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_divInt() {
            super("operator/", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.CastIntToFloat(rParam);
                return slave.DivFloatFloat(lParam, cast);
            });
        }
    }
}
