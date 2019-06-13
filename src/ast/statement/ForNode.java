package ast.statement;

import ast.AbstractSyntaxTree;

public class ForNode extends AbstractSyntaxTree {
    AbstractSyntaxTree m_Variable;
    AbstractSyntaxTree m_InitialValue;
    AbstractSyntaxTree m_FinalValue;
    boolean m_Increment;
    AbstractSyntaxTree m_Body;

    public ForNode(AbstractSyntaxTree InVariable, AbstractSyntaxTree InInitialValue, AbstractSyntaxTree InFinalValue, boolean bIncrement, AbstractSyntaxTree InBody)
    {
        this.m_Variable = InVariable;
        this.m_InitialValue = InInitialValue;
        this.m_FinalValue = InFinalValue;
        this.m_Increment = bIncrement;
        this.m_Body = InBody;
    }
}
