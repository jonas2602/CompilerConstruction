package ast.expression;

import ast.AbstractSyntaxTree;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

public class AdditiveNode extends AbstractSyntaxTree {
    public enum EAdditiveOperator {
        PLUS,
        MINUS,
        OR
    }

    private AbstractSyntaxTree m_Left;
    private AbstractSyntaxTree m_Right;
    private EAdditiveOperator m_Operator;

    public AdditiveNode(AbstractSyntaxTree InLeft, AbstractSyntaxTree InRight, EAdditiveOperator InOperator) {
        this.m_Left = InLeft;
        this.m_Right = InRight;
        this.m_Operator = InOperator;
    }

    @Override
    public TypeNode CheckType() {
        NamedTypeNode intType = NamedTypeNode.IntNode;
        NamedTypeNode realType = NamedTypeNode.RealNode;
        TypeNode outType = intType;

        TypeNode leftType = m_Left.CheckType();
        TypeNode rightType = m_Right.CheckType();
        if (!intType.CompareType(leftType)) {
            // -> left is no INT
            if (!realType.CompareType(leftType)) {
                // -> left is also no REAL
                return null;
            }
            outType = realType;
        }

        if (!intType.CompareType(rightType)) {
            // -> right is no INT
            if (!realType.CompareType(rightType)) {
                // -> right is also no REAL
                return null;
            }
            outType = realType;
        }

        return outType;
    }

    @Override
    public TypeNode GetType() {
        // TODO: Maybe cache result?
        return null;
    }
}
