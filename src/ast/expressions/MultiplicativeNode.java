package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

public class MultiplicativeNode extends AbstractSyntaxTree {
    public enum EMultiplicativeOperator {
        STAR,
        SLASH,
        DIV,
        MOD,
        AND
    }

    private AbstractSyntaxTree m_Left;
    private AbstractSyntaxTree m_Right;
    private EMultiplicativeOperator m_Operator;

    public MultiplicativeNode(AbstractSyntaxTree InLeft, AbstractSyntaxTree InRight, EMultiplicativeOperator InOperator) {
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

            // if operator is Div or MOD only Integers are allowed
            if(m_Operator == EMultiplicativeOperator.MOD || m_Operator == EMultiplicativeOperator.DIV){
                throw new TypeCheckException(this, m_Operator + " is not defined for " + leftType);
            }

            if (!realType.CompareType(leftType)) {
                // -> left is also no REAL
                throw new TypeCheckException(this, m_Operator + " is not defined for " + rightType);
            }

            outType = realType;
        }

        if (!intType.CompareType(rightType)) {
            // -> right is no INT

            // if operator is Div or MOD only Integers are allowed
            if(m_Operator == EMultiplicativeOperator.MOD || m_Operator == EMultiplicativeOperator.DIV){
                throw new TypeCheckException(this, m_Operator + " is not defined for " + rightType);
            }

            if (!realType.CompareType(rightType)) {
                // -> right is also no REAL
                throw new TypeCheckException(this, m_Operator + " is not defined for " + rightType);
            }

            outType = realType;
        }

        return outType;
    }

    @Override
    public TypeNode GetType() {
        switch (m_Operator) {
            case STAR: {
                NamedTypeNode intType = NamedTypeNode.IntNode;

                // Assume that the Addition was already checked
                // -> if both types are int, result is int else result is double
                TypeNode leftType = m_Left.CheckType();
                TypeNode rightType = m_Right.CheckType();
                if (intType.CompareType(leftType) && intType.CompareType((rightType))) {
                    return NamedTypeNode.IntNode;
                } else {
                    return NamedTypeNode.RealNode;
                }
            }
            case SLASH: {
                return NamedTypeNode.RealNode;
            }
            case DIV:
            case MOD: {
                return NamedTypeNode.IntNode;
            }
        }

        // AND
        return NamedTypeNode.BoolNode;
    }
}
