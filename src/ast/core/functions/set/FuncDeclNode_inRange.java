package ast.core.functions.set;

import ast.core.FuncDeclNode_Core;
import ast.core.functions.FuncDeclNode_Generic;
import ast.core.operators.Operator;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.statements.AssignmentNode;
import ast.statements.CompStmtNode;
import ast.types.PrimitiveTypeNode;
import ast.types.WildcardTypeNode;

public class FuncDeclNode_inRange extends FuncDeclNode_Generic {
    public FuncDeclNode_inRange() {
        super("inrange", PrimitiveTypeNode.BoolNode);

        // TODO: replace with wildcards
        WildcardTypeNode wildcard = new WildcardTypeNode();
        AddParameter("min", wildcard);
        AddParameter("max", wildcard);
        AddParameter("value", wildcard);
    }

    @Override
    protected void GenerateBody(CompStmtNode compNode) {
        // (value - min) <= (max - min)
        AccessNode_Variable minAccess = new AccessNode_Variable("min");
        AccessNode_Variable maxAccess = new AccessNode_Variable("max");
        AccessNode_Variable valueAccess = new AccessNode_Variable("value");

        FuncCallNode offsetCall = new FuncCallNode(Operator.SUB, valueAccess, minAccess);
        FuncCallNode rangeCall = new FuncCallNode(Operator.SUB, maxAccess, minAccess);
        FuncCallNode compCall = new FuncCallNode(Operator.LE, offsetCall, rangeCall);

        AccessNode_Variable outAccess = new AccessNode_Variable(m_Name);
        AssignmentNode assignNode = new AssignmentNode(outAccess, compCall);

        compNode.AddStatement(assignNode);
    }
}
