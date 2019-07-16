package ast.core.functions.math;

import ast.core.functions.FuncDeclNode_Generic;
import ast.core.operators.Operator;
import ast.expressions.AccessNode_Variable;
import ast.expressions.FuncCallNode;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.CompStmtNode;
import ast.types.PrimitiveTypeNode;
import ast.types.WildcardTypeNode;

import java.awt.image.renderable.RenderableImage;

public class FuncDeclNode_min extends FuncDeclNode_Generic {
    public FuncDeclNode_min() {
        super(Math.MIN, PrimitiveTypeNode.WildcardPrimitiveNode());

        AddParameter("left", m_ReturnType);
        AddParameter("right", m_ReturnType);
    }

    @Override
    protected void GenerateBody(CompStmtNode compNode) {
        AccessNode_Variable leftAccess = new AccessNode_Variable("left");
        AccessNode_Variable rightAccess = new AccessNode_Variable("right");

        FuncCallNode compCall = new FuncCallNode(Operator.LE, leftAccess, rightAccess);

        AccessNode_Variable outAccess = new AccessNode_Variable(m_Name);
        AssignmentNode leftAssignNode = new AssignmentNode(outAccess, leftAccess);
        AssignmentNode rightAssignNode = new AssignmentNode(outAccess, rightAccess);

        BranchNode branch = new BranchNode(compCall, leftAssignNode, rightAssignNode);
        compNode.AddStatement(branch);
    }
}
