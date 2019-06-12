package ast;

public class BranchNode extends AbstractSyntaxTree {
    private Object m_Condition;
    private Object m_Then;
    private Object m_Else;

    public BranchNode(Object inCondition, Object inThen, Object inElse)
    {
        this.m_Condition = inCondition;
        this.m_Then = inThen;
        this.m_Else = inElse;
    }
}
