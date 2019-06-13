package ast.expression;

import ast.AbstractSyntaxTree;

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

    public MultiplicativeNode(AbstractSyntaxTree InLeft,AbstractSyntaxTree InRight, EMultiplicativeOperator InOperator) {
        this.m_Left = InLeft;
        this.m_Right = InRight;
        this.m_Operator = InOperator;
    }
}
