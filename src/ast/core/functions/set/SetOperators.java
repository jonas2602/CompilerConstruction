package ast.core.functions.set;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.operators.Operator;
import ast.expressions.AccessNode_Array;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.statements.BranchNode;
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

            WildcardTypeNode primWildcard = PrimitiveTypeNode.WildcardPrimitiveNode();
            AddParameter("set", new SetTypeNode(primWildcard));
            AddParameter("element", primWildcard);


            AccessNode_Variable elementAccess = new AccessNode_Variable("element");
            FuncCallNode compCall = new FuncCallNode("inrange", ConstantNode.IntNode(0), ConstantNode.IntNode(256), elementAccess);

            AccessNode_Variable setAccess = new AccessNode_Variable("set");
            AccessNode_Array indexAccess = new AccessNode_Array(setAccess, elementAccess);



            // BranchNode branch = new BranchNode(compCall, assignment);

            // m_Block.SetCompoundStatement();
        }
    }
}
