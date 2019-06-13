package ast.statement;

import ast.AbstractSyntaxTree;

public class WhileNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Condition;
    private AbstractSyntaxTree m_Body;

    public WhileNode(AbstractSyntaxTree InCondition, AbstractSyntaxTree InBody)
    {
        this.m_Condition = InCondition;
        this.m_Body = InBody;
    }
}
