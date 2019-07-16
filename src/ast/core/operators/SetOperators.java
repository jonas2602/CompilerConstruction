package ast.core.operators;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.functions.casts.Cast;
import ast.core.functions.set.Set;
import ast.expressions.*;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.ForNode;
import ast.types.*;

public class SetOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AGNSet());

        std.AddFunctionDeclaration(new AddSet());
        std.AddFunctionDeclaration(new SubSet());

        std.AddFunctionDeclaration(new InternInSet());
        std.AddFunctionDeclaration(new IntInSet());
        std.AddFunctionDeclaration(new CharInSet());
        std.AddFunctionDeclaration(new BoolInSet());
    }

    public static class AGNSet extends PascalType_Assignment {
        public AGNSet() {
            super(Operator.AGN, new VoidTypeNode(), SetTypeNode.WildcardSetNode(), (slave, lParam, rParam) -> {
                if (!rParam.IsPointer()) {
                    slave.StoreInVariable(lParam, rParam);
                } else {
                    slave.CopyMemory(rParam, lParam);
                }
                return null;
            });
        }
    }

    public static class AddSet extends FuncDeclNode_Core {
        public AddSet() {
            super(Operator.ADD, SetTypeNode.WildcardSetNode());

            AddParameter("set1", m_ReturnType);
            AddParameter("set2", m_ReturnType);

            // Create temp variables
            m_Block.AddVariableDeclaration("i", PrimitiveTypeNode.IntNode);

            AccessNode_Variable set1Access = new AccessNode_Variable("set1");
            AccessNode_Variable set2Access = new AccessNode_Variable("set2");
            AccessNode_Variable tempSetAccess = new AccessNode_Variable(m_Name);
            AccessNode_Variable loopIndexAccess = new AccessNode_Variable("i");
            AccessNode_Array set1IndexAccess = new AccessNode_Array(set1Access, loopIndexAccess);
            AccessNode_Array set2IndexAccess = new AccessNode_Array(set2Access, loopIndexAccess);
            AccessNode_Array tempSetIndexAccess = new AccessNode_Array(tempSetAccess, loopIndexAccess);

            // Body that adds element
            FuncCallNode orCall = new FuncCallNode(Operator.OR, set1IndexAccess, set2IndexAccess);
            AssignmentNode assignIndex = new AssignmentNode(tempSetIndexAccess, orCall);

            ForNode loop = new ForNode(new AccessNode_Variable("i"), ConstantNode.IntNode(0), ConstantNode.IntNode(255), true, assignIndex);
            m_Block.SetCompoundStatement(loop);
        }
    }

    public static class SubSet extends FuncDeclNode_Core {
        public SubSet() {
            super(Operator.SUB, SetTypeNode.WildcardSetNode());

            AddParameter("set1", m_ReturnType);
            AddParameter("set2", m_ReturnType);

            // Create temp variables
            m_Block.AddVariableDeclaration("i", PrimitiveTypeNode.IntNode);

            AccessNode_Variable set1Access = new AccessNode_Variable("set1");
            AccessNode_Variable set2Access = new AccessNode_Variable("set2");
            AccessNode_Variable tempSetAccess = new AccessNode_Variable(m_Name);
            AccessNode_Variable loopIndexAccess = new AccessNode_Variable("i");
            AccessNode_Array set1IndexAccess = new AccessNode_Array(set1Access, loopIndexAccess);
            AccessNode_Array set2IndexAccess = new AccessNode_Array(set2Access, loopIndexAccess);
            AccessNode_Array tempSetIndexAccess = new AccessNode_Array(tempSetAccess, loopIndexAccess);

            // Body that adds element
            FuncCallNode invCall = new FuncCallNode(Operator.INV, set2IndexAccess);
            FuncCallNode andCall = new FuncCallNode(Operator.AND, set1IndexAccess, invCall);
            AssignmentNode assignIndex = new AssignmentNode(tempSetIndexAccess, andCall);

            ForNode loop = new ForNode(new AccessNode_Variable("i"), ConstantNode.IntNode(0), ConstantNode.IntNode(255), true, assignIndex);
            m_Block.SetCompoundStatement(loop);
        }
    }

    public static class InternInSet extends FuncDeclNode_Core {
        public InternInSet(){
            super(Operator.IN + "_intern.", PrimitiveTypeNode.BoolNode);

            // WildcardTypeNode primWildcard = PrimitiveTypeNode.WildcardPrimitiveNode();
            AddParameter("element", PrimitiveTypeNode.IntNode); // primWildcard);
            AddParameter("set", SetTypeNode.WildcardSetNode()); // new SetTypeNode(primWildcard));


            AccessNode_Variable elementAccess = new AccessNode_Variable("element");
            FuncCallNode compCall = new FuncCallNode(Set.INRANGE, ConstantNode.IntNode(0), ConstantNode.IntNode(256), elementAccess);

            AccessNode_Variable setAccess = new AccessNode_Variable("set");
            AccessNode_Array indexAccess = new AccessNode_Array(setAccess, elementAccess);
            FuncCallNode castCharBool = new FuncCallNode(Cast.BOOL, indexAccess);
            AssignmentNode assignValid = new AssignmentNode(new AccessNode_Variable(m_Name), castCharBool);

            AssignmentNode assignInvalid = new AssignmentNode(new AccessNode_Variable(m_Name), ConstantNode.BoolNode(false));


            BranchNode branch = new BranchNode(compCall, assignValid, assignInvalid);

            m_Block.SetCompoundStatement(branch);
        }
    }

    public static class IntInSet extends FuncDeclNode_Core {
        public IntInSet() {
            super(Operator.IN, PrimitiveTypeNode.BoolNode);

            m_bInline = true;

            AddParameter("element", PrimitiveTypeNode.IntNode);
            AddParameter("set", SetTypeNode.IntSetNode);

            FuncCallNode appendCall = new FuncCallNode(Operator.IN + "_intern.");
            appendCall.AddParameter(new AccessNode_Variable("element"));
            appendCall.AddParameter(new AccessNode_Variable("set"));

            m_Block.SetCompoundStatement(appendCall);
        }
    }

    public static class CharInSet extends FuncDeclNode_Core {

        public CharInSet() {
            super(Operator.IN, PrimitiveTypeNode.BoolNode);

            m_bInline = true;

            AddParameter("element", PrimitiveTypeNode.CharNode);
            AddParameter("set", SetTypeNode.CharSetNode);

            FuncCallNode ordCall = new FuncCallNode(Cast.ORD, new AccessNode_Variable("element"));
            FuncCallNode appendCall = new FuncCallNode(Operator.IN + "_intern.");
            appendCall.AddParameter(ordCall);
            appendCall.AddParameter(new AccessNode_Variable("set"));

            m_Block.SetCompoundStatement(appendCall);
        }
    }

    public static class BoolInSet extends FuncDeclNode_Core {

        public BoolInSet() {
            super(Operator.IN, PrimitiveTypeNode.BoolNode);

            m_bInline = true;

            AddParameter("element", PrimitiveTypeNode.BoolNode);
            AddParameter("set", SetTypeNode.BoolSetNode);

            FuncCallNode ordCall = new FuncCallNode(Cast.ORD, new AccessNode_Variable("element"));
            FuncCallNode appendCall = new FuncCallNode(Operator.IN + "_intern.");
            appendCall.AddParameter(ordCall);
            appendCall.AddParameter(new AccessNode_Variable("set"));

            m_Block.SetCompoundStatement(appendCall);
        }
    }
}
