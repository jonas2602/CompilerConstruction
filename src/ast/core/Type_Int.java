package ast.core;

import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.NamedTypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;

public class Type_Int {
    public static class FuncDeclNode_addInt extends FuncDeclNode_Core {
        public FuncDeclNode_addInt() {
            super("operator+", NamedTypeNode.IntNode);

            AddParameter(new ParamDeclNode("left", NamedTypeNode.IntNode));
            AddParameter(new ParamDeclNode("right", NamedTypeNode.IntNode));

            m_bCustomCallLogic = true;
            m_bInline = true;
        }

        @Override
        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);

            int scopeIndex = slave.AddIntInt(leftParam.Write(), rightParam.Write());
            return new CodeSnippet_Plain("%" + scopeIndex);
        }
    }

    public static class FuncDeclNode_addReal extends FuncDeclNode_Core {
        public FuncDeclNode_addReal() {
            super("operator+", NamedTypeNode.RealNode);

            AddParameter(new ParamDeclNode("left", NamedTypeNode.IntNode));
            AddParameter(new ParamDeclNode("right", NamedTypeNode.RealNode));
        }
    }
}

