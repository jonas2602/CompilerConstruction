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

    public static abstract class FuncDeclNode_FloatParam extends FuncDeclNode_Core {

        public FuncDeclNode_FloatParam(String InName, PrimitiveTypeNode rparam) {
            super(InName, PrimitiveTypeNode.FloatNode);

            AddParameter(new ParamDeclNode("left", PrimitiveTypeNode.FloatNode));
            AddParameter(new ParamDeclNode("right", rparam));

            m_bCustomCallLogic = true;
            m_bInline = true;
        }
    }

    public static class FuncDeclNode_addFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_addFloat() {
            super("operator+", PrimitiveTypeNode.FloatNode);
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex = slave.AddFloatFloat(leftParam.Write(), rightParam.Write());
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_addInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_addInt() {
            super("operator+", PrimitiveTypeNode.IntNode);
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex = slave.AddFloatInt(rightParam.Write(), leftParam.Write());
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_subInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_subInt() {
            super("operator-", PrimitiveTypeNode.IntNode);
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int cast = slave.CastIntToFloat(rightParam.Write());
            int scopeIndex = slave.SubFloatFloat(leftParam.Write(), "%" + cast);
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_subFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_subFloat() {
            super("operator-", PrimitiveTypeNode.FloatNode);
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex = slave.SubFloatFloat(leftParam.Write(), rightParam.Write());
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_mulInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_mulInt() {
            super("operator*", PrimitiveTypeNode.IntNode);
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int cast = slave.CastIntToFloat(rightParam.Write());
            int scopeIndex = slave.MulFloatFloat(leftParam.Write(), "%" + cast);
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_mulFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_mulFloat() {
            super("operator*", PrimitiveTypeNode.FloatNode);
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex = slave.MulFloatFloat(leftParam.Write(), rightParam.Write());
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_divFloat extends FuncDeclNode_FloatParam {
        public FuncDeclNode_divFloat() {
            super("operator/", PrimitiveTypeNode.FloatNode);
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex = slave.DivFloatFloat(leftParam.Write(), rightParam.Write());
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_divInt extends FuncDeclNode_FloatParam {
        public FuncDeclNode_divInt() {
            super("operator/", PrimitiveTypeNode.IntNode);
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int cast = slave.CastIntToFloat(rightParam.Write());
            int scopeIndex = slave.DivFloatFloat(leftParam.Write(), "%" + cast);
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }
}

