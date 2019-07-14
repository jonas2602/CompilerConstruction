package ast.core.functions.set;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.operators.Operator;
import ast.expressions.AccessNode_Array;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.ForNode;
import ast.types.PrimitiveTypeNode;
import ast.types.SetTypeNode;
import ast.types.VoidTypeNode;

public class SetRangeAppends implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AppendRangeInternal());
        std.AddFunctionDeclaration(new AppendRangeInt());
        std.AddFunctionDeclaration(new AppendRangeChar());
        std.AddFunctionDeclaration(new AppendRangeBool());
    }

    public static class AppendRangeInternal extends FuncDeclNode_Core {
        public AppendRangeInternal() {
            super(".appendRange_internal", VoidTypeNode.VoidNode);

            AddParameter("set", SetTypeNode.WildcardSetNode(), true);
            AddParameter("min", PrimitiveTypeNode.IntNode);
            AddParameter("max", PrimitiveTypeNode.IntNode);

            // Body that adds element
            FuncCallNode appendCall = new FuncCallNode(".append");
            AccessNode_Variable setAccess = new AccessNode_Variable("set");
            AccessNode_Variable indexAccess = new AccessNode_Variable("i");
            appendCall.AddParameter(setAccess);
            appendCall.AddParameter(indexAccess);

            // Head that iterates from min to max
            AccessNode_Variable minAccess = new AccessNode_Variable("min");
            AccessNode_Variable maxAccess = new AccessNode_Variable("max");
            m_Block.AddVariableDeclaration("i", PrimitiveTypeNode.IntNode);
            ForNode loop = new ForNode(new AccessNode_Variable("i"), minAccess, maxAccess, true, appendCall);
            m_Block.SetCompoundStatement(loop);
        }
    }

    public static class AppendRangeInt extends FuncDeclNode_Core {
        public AppendRangeInt() {
            super("appendRange", VoidTypeNode.VoidNode);

            m_bInline = true;

            AddParameter("set", SetTypeNode.IntSetNode, true);
            AddParameter("min", PrimitiveTypeNode.IntNode);
            AddParameter("max", PrimitiveTypeNode.IntNode);

            FuncCallNode appendCall = new FuncCallNode(".appendRange_internal");
            appendCall.AddParameter(new AccessNode_Variable("set"));
            appendCall.AddParameter(new AccessNode_Variable("min"));
            appendCall.AddParameter(new AccessNode_Variable("max"));

            m_Block.SetCompoundStatement(appendCall);
        }
    }

    public static class AppendRangeChar extends FuncDeclNode_Core {
        public AppendRangeChar() {
            super("appendRange", VoidTypeNode.VoidNode);

            m_bInline = true;

            AddParameter("set", SetTypeNode.CharSetNode, true);
            AddParameter("min", PrimitiveTypeNode.CharNode);
            AddParameter("max", PrimitiveTypeNode.CharNode);

            // convert range to int
            FuncCallNode ordMinCall = new FuncCallNode("ord", new AccessNode_Variable("min"));
            FuncCallNode ordMaxCall = new FuncCallNode("ord", new AccessNode_Variable("max"));

            FuncCallNode appendCall = new FuncCallNode(".appendRange_internal");
            appendCall.AddParameter(new AccessNode_Variable("set"));
            appendCall.AddParameter(ordMinCall);
            appendCall.AddParameter(ordMaxCall);

            m_Block.SetCompoundStatement(appendCall);
        }
    }

    public static class AppendRangeBool extends FuncDeclNode_Core {
        public AppendRangeBool() {
            super("appendRange", VoidTypeNode.VoidNode);

            m_bInline = true;

            AddParameter("set", SetTypeNode.BoolSetNode, true);
            AddParameter("min", PrimitiveTypeNode.BoolNode);
            AddParameter("max", PrimitiveTypeNode.BoolNode);

            // convert range to int
            FuncCallNode ordMinCall = new FuncCallNode("ord", new AccessNode_Variable("min"));
            FuncCallNode ordMaxCall = new FuncCallNode("ord", new AccessNode_Variable("max"));

            FuncCallNode appendCall = new FuncCallNode(".appendRange_internal");
            appendCall.AddParameter(new AccessNode_Variable("set"));
            appendCall.AddParameter(ordMinCall);
            appendCall.AddParameter(ordMaxCall);

            m_Block.SetCompoundStatement(appendCall);
        }
    }
}