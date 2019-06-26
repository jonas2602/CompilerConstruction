package ast.core;

import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;

public class PascalType_Float extends PascalType {
    public PascalType_Float() {
        super("float", 32, true, "%f", "0.0");
    }

    public interface FloatFunctionCalls {
        public int createFunctionCall(GeneratorSlave slave, CodeSnippet_Base lParam, CodeSnippet_Base rParam);
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
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex =  operation.createFunctionCall(slave, leftParam, rightParam);
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_addFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_addFloat() {
            super("operator+", PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> slave.AddFloatFloat(lParam.Write(), rParam.Write()));
        }
    }

    public static class FuncDeclNode_addInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_addInt() {
            super("operator+", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> slave.AddFloatInt(lParam.Write(), rParam.Write()));
        }
    }

    public static class FuncDeclNode_subInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_subInt() {
            super("operator-", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                int cast = slave.CastIntToFloat(rParam.Write());
                return slave.SubFloatFloat(lParam.Write(), "%" + cast);
            });
        }
    }

    public static class FuncDeclNode_subFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_subFloat() {
            super("operator-", PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> slave.SubFloatFloat(lParam.Write(), rParam.Write()));
        }
    }

    public static class FuncDeclNode_mulInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_mulInt() {
            super("operator*", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                int cast = slave.CastIntToFloat(rParam.Write());
                return slave.MulFloatFloat(lParam.Write(), "%" + cast);
            });
        }
    }

    public static class FuncDeclNode_mulFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_mulFloat() {
            super("operator*", PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> slave.MulFloatFloat(lParam.Write(), rParam.Write()));
        }
    }

    public static class FuncDeclNode_divFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_divFloat() {
            super("operator/", PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> slave.DivFloatFloat(lParam.Write(), rParam.Write()));
        }
    }

    public static class FuncDeclNode_divInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_divInt() {
            super("operator/", PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
                int cast = slave.CastIntToFloat(rParam.Write());
                return slave.DivFloatFloat(lParam.Write(), "%" + cast);
            });
        }
    }
}
