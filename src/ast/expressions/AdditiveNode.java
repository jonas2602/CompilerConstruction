package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

public class AdditiveNode extends AbstractSyntaxTree {
    public enum EAdditiveOperator {
        PLUS,
        MINUS,
        OR  // TODO: move OR somewhere else
    }

    private AbstractSyntaxTree m_Left;
    private AbstractSyntaxTree m_Right;
    private EAdditiveOperator m_Operator;

    private TypeNode m_CachedType = null;

    public AdditiveNode(AbstractSyntaxTree InLeft, AbstractSyntaxTree InRight, EAdditiveOperator InOperator) {
        this.m_Left = InLeft;
        this.m_Right = InRight;
        this.m_Operator = InOperator;

        m_Left.SetParent(this);
        m_Right.SetParent(this);
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
                throw new TypeCheckException(this, "Additive operations are not defined for " + leftType);
            }
            outType = realType;
        }

        if (!intType.CompareType(rightType)) {
            // -> right is no INT
            if (!realType.CompareType(rightType)) {
                // -> right is also no REAL
                throw new TypeCheckException(this, "Additive operations are not defined for " + rightType);
            }
            outType = realType;
        }

        m_CachedType = outType;
        return outType;
    }

    @Override
    public TypeNode GetType() {
        if (m_CachedType == null) {
            NamedTypeNode intType = NamedTypeNode.IntNode;

            // Assume that the Addition was already checked
            // -> if both types are int, result is int else result is double
            TypeNode leftType = m_Left.CheckType();
            TypeNode rightType = m_Right.CheckType();
            if (intType.CompareType(leftType) && intType.CompareType((rightType))) {
                m_CachedType = intType;
            } else {
                m_CachedType = NamedTypeNode.RealNode;
            }

        }

        return m_CachedType;
    }
}
