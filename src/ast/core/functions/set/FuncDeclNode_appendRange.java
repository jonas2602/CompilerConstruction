package ast.core.functions.set;

import ast.core.FuncDeclNode_Core;
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

public class FuncDeclNode_appendRange extends FuncDeclNode_Core {
    public FuncDeclNode_appendRange() {
        super(".appendRange", VoidTypeNode.VoidNode);

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
