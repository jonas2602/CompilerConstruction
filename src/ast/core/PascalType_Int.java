package ast.core;

import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.NamedTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;

public class PascalType_Int extends PascalType {
    public PascalType_Int() {
        super("i32", 32, false, "%d", "0");
    }

    public interface IntFunctionCalls {
        public CodeSnippet_Base createFunctionCall(GeneratorSlave slave, CodeSnippet_Base leftParam, CodeSnippet_Base rightParam);
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
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            return operation.createFunctionCall(slave, leftParam, rightParam);
        }
    }

    public static class FuncDeclNode_addInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_addInt() {
            super("operator+", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, leftParam, rightParam) -> {
                int scopeIndex = slave.AddIntInt(leftParam.Write(), rightParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_addFloat extends FuncDeclNode_IntParam {
        public FuncDeclNode_addFloat() {
            super("operator+", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, leftParam, rightParam) -> {
                int scopeIndex = slave.AddFloatInt(rightParam.Write(), leftParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_subInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_subInt() {
            super("operator-", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, leftParam, rightParam) -> {
                int scopeIndex = slave.SubIntInt(leftParam.Write(), rightParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_subFloat extends FuncDeclNode_IntParam {
        public FuncDeclNode_subFloat() {
            super("operator-", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, leftParam, rightParam) -> {
                int cast = slave.CastIntToFloat(leftParam.Write());
                int scopeIndex = slave.SubFloatFloat("%" + cast, rightParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_mulInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_mulInt() {
            super("operator*", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, leftParam, rightParam) -> {
                int scopeIndex = slave.MulIntInt(leftParam.Write(), rightParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_mulFloat extends FuncDeclNode_IntParam {
        public FuncDeclNode_mulFloat() {
            super("operator*", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, leftParam, rightParam) -> {
                int cast = slave.CastIntToFloat(leftParam.Write());
                int scopeIndex = slave.MulFloatFloat("%" + cast, rightParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_divInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_divInt() {
            super("operator/", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.IntNode, (slave, leftParam, rightParam) -> {
                int castLeft = slave.CastIntToFloat(leftParam.Write());
                int castRight = slave.CastIntToFloat(rightParam.Write());
                int scopeIndex = slave.DivFloatFloat("%" + castLeft, "%" + castRight);
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_divFloat extends FuncDeclNode_IntParam {
        public FuncDeclNode_divFloat() {
            super("operator/", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, leftParam, rightParam) -> {
                int cast = slave.CastIntToFloat(leftParam.Write());
                int scopeIndex = slave.DivFloatFloat("%" + cast, rightParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_divTrucInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_divTrucInt() {
            super("operatordiv", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, leftParam, rightParam) -> {
                int scopeIndex = slave.DivIntInt(leftParam.Write(), rightParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }

    public static class FuncDeclNode_modInt extends FuncDeclNode_IntParam {
        public FuncDeclNode_modInt() {
            super("operatormod", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, leftParam, rightParam) -> {
                int scopeIndex = slave.ModIntInt(leftParam.Write(), rightParam.Write());
                return new CodeSnippet_Plain("%" + scopeIndex);
            });
        }
    }
}
