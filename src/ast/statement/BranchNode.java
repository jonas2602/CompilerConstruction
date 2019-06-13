package ast.statement;

import ast.AbstractSyntaxTree;

public class BranchNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Condition;
    private AbstractSyntaxTree m_Then;
    private AbstractSyntaxTree m_Else;

    public BranchNode(AbstractSyntaxTree InCondition, AbstractSyntaxTree InThen, AbstractSyntaxTree InElse)
    {
        this.m_Condition = InCondition;
        this.m_Then = InThen;
        this.m_Else = InElse;
    }
}
