package ast.core.functions.set;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.expressions.*;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.types.PrimitiveTypeNode;
import ast.types.SetTypeNode;
import ast.types.VoidTypeNode;

public class SetElementAppends implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AppendInt());
        std.AddFunctionDeclaration(new AppendChar());
        std.AddFunctionDeclaration(new AppendBool());
    }

    public static class AppendInt extends FuncDeclNode_Core {
        public AppendInt() {
            super(".append", VoidTypeNode.VoidNode);

            AddParameter("set", SetTypeNode.WildcardSetNode(), true);
            AddParameter("element", PrimitiveTypeNode.IntNode);

            // TODO: generic function with conversion of element to ordinal type first

            // check if element is in range element > 0 && element < 256
            AccessNode_Variable elementAccess = new AccessNode_Variable("element");
            FuncCallNode compCall = new FuncCallNode(".inrange", ConstantNode.IntNode(0), ConstantNode.IntNode(256), elementAccess);


            // switch index in set to valid
            AccessNode_Variable setAccess = new AccessNode_Variable("set");
            AccessNode_Array indexAccess = new AccessNode_Array(setAccess);
            indexAccess.AddIndexExpression(elementAccess);
            AssignmentNode assignment = new AssignmentNode(indexAccess, ConstantNode.CharNode((char) 1));

            BranchNode branch = new BranchNode(compCall, assignment);
            m_Block.SetCompoundStatement(branch);
        }
    }

    public static class AppendChar extends FuncDeclNode_Core {

        public AppendChar() {
            super(".append", VoidTypeNode.VoidNode);

            m_bInline = true;

            AddParameter("set", SetTypeNode.CharSetNode, true);
            AddParameter("element", PrimitiveTypeNode.CharNode);

            FuncCallNode ordCall = new FuncCallNode("ord", new AccessNode_Variable("element"));
            FuncCallNode appendCall = new FuncCallNode(".append");
            appendCall.AddParameter(new AccessNode_Variable("set"));
            appendCall.AddParameter(ordCall);

            m_Block.SetCompoundStatement(appendCall);
        }
    }

    public static class AppendBool extends FuncDeclNode_Core {

        public AppendBool() {
            super(".append", VoidTypeNode.VoidNode);

            m_bInline = true;

            AddParameter("set", SetTypeNode.BoolSetNode, true);
            AddParameter("element", PrimitiveTypeNode.BoolNode);

            FuncCallNode ordCall = new FuncCallNode("ord", new AccessNode_Variable("element"));
            FuncCallNode appendCall = new FuncCallNode(".append");
            appendCall.AddParameter(new AccessNode_Variable("set"));
            appendCall.AddParameter(ordCall);

            m_Block.SetCompoundStatement(appendCall);
        }
    }
}