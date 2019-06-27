package ast.core;

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

    public interface IntFunctionCalls {
        public TypeContainer createFunctionCall(GeneratorSlave slave, TypeContainer lParam, TypeContainer rParam);
    }

    public static abstract class FuncDeclNode_IntParam extends FuncDeclNode_Core {

        private IntFunctionCalls operation;

        public FuncDeclNode_IntParam(String InName, TypeNode InReturnType, PrimitiveTypeNode rparam, IntFunctionCalls operation) {
            super(InName, InReturnType);

            AddParameter(new ParamDeclNode("left", PrimitiveTypeNode.IntNode));
            AddParameter(new ParamDeclNode("right", rparam));

            m_bCustomCallLogic = true;
            m_bInline = true;

            this.operation = operation;
        }

        @Override
        public TypeContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            TypeContainer leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave);
            TypeContainer rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave);

            // load value if requested from a variable
            if (callNode.GetParameterList().get(0) instanceof AccessInterface) {
                leftParam = slave.LoadFromVariable(leftParam);
            }

            // load value if requested from a variable
            if (callNode.GetParameterList().get(1) instanceof AccessInterface) {
                rightParam = slave.LoadFromVariable(rightParam);
            }

            return operation.createFunctionCall(slave, leftParam, rightParam);
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
}
