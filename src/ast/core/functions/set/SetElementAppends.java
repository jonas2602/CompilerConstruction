package ast.core.functions.set;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.operators.Operator;
import ast.expressions.*;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.types.PrimitiveTypeNode;
import ast.types.SetTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class SetElementAppends implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AppendInt());
        std.AddFunctionDeclaration(new AppendChar());
    }

    public static class AppendInt extends FuncDeclNode_Core {
        public AppendInt() {
            super("append", VoidTypeNode.VoidNode);

            AddParameter("set", SetTypeNode.WildcardSetNode(), true);
            AddParameter("element", PrimitiveTypeNode.IntNode);

            // TODO: generic function with conversion of element to ordinal type first

            // check if element is in range element > 0 && element < 256
            AccessNode_Variable elementAccess = new AccessNode_Variable("element");
            FuncCallNode compCall = new FuncCallNode("inrange", ConstantNode.IntNode(0), ConstantNode.IntNode(256), elementAccess);


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
            super("append", VoidTypeNode.VoidNode);

            AddParameter("set", SetTypeNode.CharSetNode, true);
            AddParameter("element", PrimitiveTypeNode.CharNode);
        }

//        @Override
//        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
//            AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
//            AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);
//            ParamContainer leftParam = lParam.CreateSnippet(slave);
//            ParamContainer rightParam = rParam.CreateSnippet(slave);
//
//
//        }
    }
}