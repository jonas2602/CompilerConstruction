package ast;

public class AdditiveNode extends AbstractSyntaxTree {
    public enum EAdditiveOperator {
        PLUS,
        MINUS,
        OR
    }

    private AbstractSyntaxTree m_Left;
    private AbstractSyntaxTree m_Right;
    private EAdditiveOperator m_Operator;

    public AdditiveNode(AbstractSyntaxTree InLeft,AbstractSyntaxTree InRight, EAdditiveOperator InOperator) {
        this.m_Left = InLeft;
        this.m_Right = InRight;
        this.m_Operator = InOperator;
    }
}
