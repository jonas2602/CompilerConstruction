package ast.core.functions.set;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.operators.Operator;
import ast.expressions.*;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.ForNode;
import ast.types.PrimitiveTypeNode;
import ast.types.SetTypeNode;
import ast.types.WildcardTypeNode;

public class SetOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new SetOperators.AddSet());
        std.AddFunctionDeclaration(new SetOperators.SubSet());
        std.AddFunctionDeclaration(new SetOperators.InSet());
    }

    public static class AddSet extends FuncDeclNode_Core {
        public AddSet() {
            super(Operator.ADD, SetTypeNode.WildcardSetNode());

            AddParameter("set1", m_ReturnType);
            AddParameter("set2", m_ReturnType);

            // Create temp variables
            m_Block.AddVariableDeclaration("tempSet", m_ReturnType);
            m_Block.AddVariableDeclaration("i", PrimitiveTypeNode.IntNode);

            AccessNode_Variable set1Access = new AccessNode_Variable("set1");
            AccessNode_Variable set2Access = new AccessNode_Variable("set2");
            AccessNode_Variable loopIndexAccess = new AccessNode_Variable("i");
            AccessNode_Array set1IndexAccess = new AccessNode_Array(set1Access, loopIndexAccess);
            AccessNode_Array set2IndexAccess = new AccessNode_Array(set2Access, loopIndexAccess);

            // Body that adds element
            AccessNode_Variable tempSetAccess = new AccessNode_Variable("tempSet");
            FuncCallNode orCall = new FuncCallNode(Operator.OR, set1IndexAccess, set2IndexAccess);
            AssignmentNode assignIndex = new AssignmentNode(tempSetAccess, orCall);

            ForNode loop = new ForNode(new AccessNode_Variable("i"), ConstantNode.IntNode(0), ConstantNode.IntNode(255), true, assignIndex);
            m_Block.SetCompoundStatement(loop);
        }
    }

    public static class SubSet extends FuncDeclNode_Core {
        public SubSet() {
            super(Operator.SUB, SetTypeNode.WildcardSetNode());

            AddParameter("set1", m_ReturnType);
            AddParameter("set2", m_ReturnType);
        }


    }

    public static class InSet extends FuncDeclNode_Core {
        public InSet() {
            super(Operator.IN, PrimitiveTypeNode.BoolNode);

            // WildcardTypeNode primWildcard = PrimitiveTypeNode.WildcardPrimitiveNode();
            AddParameter("element", PrimitiveTypeNode.IntNode); // primWildcard);
            AddParameter("set", SetTypeNode.WildcardSetNode()); // new SetTypeNode(primWildcard));


            AccessNode_Variable elementAccess = new AccessNode_Variable("element");
            FuncCallNode compCall = new FuncCallNode("inrange", ConstantNode.IntNode(0), ConstantNode.IntNode(256), elementAccess);

            AccessNode_Variable setAccess = new AccessNode_Variable("set");
            AccessNode_Array indexAccess = new AccessNode_Array(setAccess, elementAccess);
            FuncCallNode castCharBool = new FuncCallNode("bool", indexAccess);
            AssignmentNode assignValid = new AssignmentNode(new AccessNode_Variable(m_Name), castCharBool);

            AssignmentNode assignInvalid = new AssignmentNode(new AccessNode_Variable(m_Name), ConstantNode.BoolNode(false));


            BranchNode branch = new BranchNode(compCall, assignValid, assignInvalid);

            m_Block.SetCompoundStatement(branch);
        }
    }
}
