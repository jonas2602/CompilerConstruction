package ast.core;

import ast.EPrimitiveType;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.TypeContainer;

public class PascalType_Float extends PascalType_Primitive {
    public PascalType_Float() {
        super(EPrimitiveType.FLOAT, 32, true, "%f", "0.0");
    }

    public interface FloatFunctionCalls {
        public TypeContainer createFunctionCall(GeneratorSlave slave, TypeContainer lParam, TypeContainer rParam);
    }

    public static abstract class FuncDeclNode_FloatParam extends FuncDeclNode_Core {

        private FloatFunctionCalls operation;

        public FuncDeclNode_FloatParam(String InName, PrimitiveTypeNode rparam, FloatFunctionCalls operation) {
            super(InName, PrimitiveTypeNode.FloatNode);

            AddParameter(new ParamDeclNode("left", PrimitiveTypeNode.FloatNode));
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

    public static class FuncDeclNode_addFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_addFloat() {
            super("operator+", PrimitiveTypeNode.FloatNode, GeneratorSlave::AddFloatFloat);
        }
    }

    public static class FuncDeclNode_addInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_addInt() {
            super("operator+", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                TypeContainer cast = slave.CastIntToFloat(rParam);
                return slave.AddFloatFloat(lParam, cast);
            });
        }
    }

    public static class FuncDeclNode_subInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_subInt() {
            super("operator-", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                TypeContainer cast = slave.CastIntToFloat(rParam);
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
                TypeContainer cast = slave.CastIntToFloat(rParam);
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
                TypeContainer cast = slave.CastIntToFloat(rParam);
                return slave.DivFloatFloat(lParam, cast);
            });
        }
    }
}
