package ast.statements;

import ast.AbstractSyntaxTree;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

public class BranchNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Condition;
    private AbstractSyntaxTree m_Then;
    private AbstractSyntaxTree m_Else;

    public BranchNode(AbstractSyntaxTree InCondition, AbstractSyntaxTree InThen, AbstractSyntaxTree InElse) {
        this.m_Condition = InCondition;
        this.m_Then = InThen;
        this.m_Else = InElse;

        m_Condition.SetParent(this);
        m_Then.SetParent(this);
        m_Else.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        if (!NamedTypeNode.BoolNode.CompareType(m_Condition.CheckType())) {
            throw new RuntimeException("Typecheck failed at Branch Node because Condition is not of type bool");
        }

        m_Then.CheckType();
        m_Else.CheckType();

        return null;
    }
}
