package ast.core;

import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.NamedTypeNode;
import ast.types.PrimitiveTypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;

public class PascalType_Float extends PascalType {
    public PascalType_Float() {
        super("float", 32, true, "%f", "0.0");
    }

    public static class FuncDeclNode_addFloat extends FuncDeclNode_Core {
        public FuncDeclNode_addFloat() {
            super("operator+", PrimitiveTypeNode.FloatNode);

            AddParameter(new ParamDeclNode("left", PrimitiveTypeNode.FloatNode));
            AddParameter(new ParamDeclNode("right", PrimitiveTypeNode.FloatNode));

            m_bCustomCallLogic = true;
            m_bInline = true;
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex = slave.AddFloatFloat(leftParam.Write(), rightParam.Write());
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_addInt extends FuncDeclNode_Core {
        public FuncDeclNode_addInt() {
            super("operator+", PrimitiveTypeNode.FloatNode);

            AddParameter(new ParamDeclNode("left", PrimitiveTypeNode.FloatNode));
            AddParameter(new ParamDeclNode("right", PrimitiveTypeNode.IntNode));

            m_bCustomCallLogic = true;
            m_bInline = true;
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex = slave.AddFloatInt(rightParam.Write(), leftParam.Write());
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }
}

