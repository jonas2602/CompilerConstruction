package ast.expression;

import ast.AbstractSyntaxTree;

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

    public RelationalNode(AbstractSyntaxTree InLeft,AbstractSyntaxTree InRight, ERelationalOperator InOperator) {
        this.m_Left = InLeft;
        this.m_Right = InRight;
        this.m_Operator = InOperator;
    }


}
