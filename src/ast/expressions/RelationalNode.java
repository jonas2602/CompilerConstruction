package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

public class RelationalNode extends AbstractSyntaxTree {
    public enum ERelationalOperator {
        EQUAL,
        NOTEQUAL,
        LESS,
        LESSEQUAL,
        GREATER,
        GREATEREQUAL,
        IN
    }


    private AbstractSyntaxTree m_Left;
    private AbstractSyntaxTree m_Right;
    private ERelationalOperator m_Operator;

    public RelationalNode(AbstractSyntaxTree InLeft, AbstractSyntaxTree InRight, ERelationalOperator InOperator) {
        this.m_Left = InLeft;
        this.m_Right = InRight;
        this.m_Operator = InOperator;
    }

    @Override
    public TypeNode CheckType() {
        TypeNode leftType = m_Left.CheckType();
        TypeNode rightType = m_Right.CheckType();

        // same types?
        if (leftType.CompareType(rightType)) {
            return GetType();
        }

        // check special case of REAL <-> INT comparability
        NamedTypeNode intType = NamedTypeNode.IntNode;
        NamedTypeNode realType = NamedTypeNode.RealNode;
        if ((leftType.CompareType(intType) || leftType.CompareType(realType))
        && (leftType.CompareType(intType) || leftType.CompareType(realType))) {
            return GetType();
        }

        throw new RuntimeException("Type checking failed " + leftType + " and " + rightType + " are not comparable");

        // TODO: IN-Operator
    }

    @Override
    public TypeNode GetType() {
        return NamedTypeNode.BoolNode;
    }
}
