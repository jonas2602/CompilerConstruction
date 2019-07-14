package ast.core.functions.set;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.operators.Operator;
import ast.declarations.FuncDeclNode;
import ast.expressions.*;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.ForNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Array;
import writer.wrappers.TypeWrapper_Other;
import writer.wrappers.TypeWrapper_Primitive;

import java.util.Set;

public class SetOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AddSetWrapper());
        std.AddFunctionDeclaration(new SubSetWrapper());

        std.AddFunctionDeclaration(new IntInSet());
        std.AddFunctionDeclaration(new CharInSet());
        std.AddFunctionDeclaration(new BoolInSet());
    }

    public static class FuncSetWrapper extends FuncDeclNode_Core {
        private FuncDeclNode m_WrappedFunc;

        public FuncSetWrapper(Operator operator, FuncDeclNode wrappedFunc) {
            super(operator, SetTypeNode.WildcardSetNode());

            AddParameter("set1", m_ReturnType);
            AddParameter("set2", m_ReturnType);

            m_bInline = true;
            m_bCustomCallLogic = true;

            m_WrappedFunc = wrappedFunc;
            m_Block.AddFunctionDeclaration(wrappedFunc);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
            AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);
            ParamContainer leftParam = lParam.CreateSnippet(slave);
            ParamContainer rightParam = rParam.CreateSnippet(slave);

            // allocate temp set array
            ParamContainer temp = slave.AllocateMemory(new TypeWrapper_Array(TypeWrapper_Primitive.CHAR, 256));
            slave.SetMemory(ParamContainer.CHARCONTAINER((char) 0), temp);

            // create call to inner function
            slave.CreateFunctionCall(m_WrappedFunc.GetHierarchicalName(), TypeWrapper_Other.VOID, true);
            slave.CreateFunctionCallParameter(temp);
            slave.CreateFunctionCallParameter(leftParam);
            slave.CreateFunctionCallParameter(rightParam);

            // construct inner function
            m_WrappedFunc.CreateSnippet(slave);

            slave.ExitScope();
            return temp;
        }
    }

    public static class AddSetWrapper extends FuncSetWrapper {
        public AddSetWrapper() {
            super(Operator.ADD, new AddSet());
        }
    }

    public static class AddSet extends FuncDeclNode_Core {
        public AddSet() {
            super("addsetcore", VoidTypeNode.VoidNode);

            SetTypeNode subSet = SetTypeNode.WildcardSetNode();

            AddParameter("target", subSet, true);
            AddParameter("set1", subSet, true);
            AddParameter("set2", subSet, true);

            // Create temp variables
            m_Block.AddVariableDeclaration("i", PrimitiveTypeNode.IntNode);

            AccessNode_Variable set1Access = new AccessNode_Variable("set1");
            AccessNode_Variable set2Access = new AccessNode_Variable("set2");
            AccessNode_Variable tempSetAccess = new AccessNode_Variable("target");
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

    public static class SubSetWrapper extends FuncSetWrapper {
        public SubSetWrapper() {
            super(Operator.SUB, new SubSet());
        }
    }

    public static class SubSet extends FuncDeclNode_Core {
        public SubSet() {
            super("subsetcore", VoidTypeNode.VoidNode);

            SetTypeNode subSet = SetTypeNode.WildcardSetNode();

            AddParameter("target", subSet, true);
            AddParameter("set1", subSet, true);
            AddParameter("set2", subSet, true);

            // Create temp variables
            m_Block.AddVariableDeclaration("i", PrimitiveTypeNode.IntNode);

            AccessNode_Variable set1Access = new AccessNode_Variable("set1");
            AccessNode_Variable set2Access = new AccessNode_Variable("set2");
            AccessNode_Variable tempSetAccess = new AccessNode_Variable("target");
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

    public static class IntInSet extends FuncDeclNode_Core {
        public IntInSet() {
            super(Operator.IN, PrimitiveTypeNode.BoolNode);

            // WildcardTypeNode primWildcard = PrimitiveTypeNode.WildcardPrimitiveNode();
            AddParameter("element", PrimitiveTypeNode.IntNode); // primWildcard);
            AddParameter("set", SetTypeNode.WildcardSetNode()); // new SetTypeNode(primWildcard));


            AccessNode_Variable elementAccess = new AccessNode_Variable("element");
            FuncCallNode compCall = new FuncCallNode(".inrange", ConstantNode.IntNode(0), ConstantNode.IntNode(256), elementAccess);

            AccessNode_Variable setAccess = new AccessNode_Variable("set");
            AccessNode_Array indexAccess = new AccessNode_Array(setAccess, elementAccess);
            FuncCallNode castCharBool = new FuncCallNode("bool", indexAccess);
            AssignmentNode assignValid = new AssignmentNode(new AccessNode_Variable(m_Name), castCharBool);

            AssignmentNode assignInvalid = new AssignmentNode(new AccessNode_Variable(m_Name), ConstantNode.BoolNode(false));


            BranchNode branch = new BranchNode(compCall, assignValid, assignInvalid);

            m_Block.SetCompoundStatement(branch);
        }
    }

    public static class CharInSet extends FuncDeclNode_Core {

        public CharInSet() {
            super(Operator.IN, PrimitiveTypeNode.BoolNode);

            m_bInline = true;

            AddParameter("element", PrimitiveTypeNode.CharNode);
            AddParameter("set", SetTypeNode.CharSetNode);

            FuncCallNode ordCall = new FuncCallNode("ord", new AccessNode_Variable("element"));
            FuncCallNode appendCall = new FuncCallNode(Operator.IN);
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

            FuncCallNode ordCall = new FuncCallNode("ord", new AccessNode_Variable("element"));
            FuncCallNode appendCall = new FuncCallNode(Operator.IN);
            appendCall.AddParameter(ordCall);
            appendCall.AddParameter(new AccessNode_Variable("set"));

            m_Block.SetCompoundStatement(appendCall);
        }
    }
}
