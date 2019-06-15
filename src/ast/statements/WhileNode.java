package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;

public class WhileNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Condition;
    private AbstractSyntaxTree m_Body;

    public WhileNode(AbstractSyntaxTree InCondition, AbstractSyntaxTree InBody)
    {
        this.m_Condition = InCondition;
        this.m_Body = InBody;

        m_Condition.SetParent(this);
        m_Body.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode condType = m_Condition.CheckType();
        NamedTypeNode boolType = NamedTypeNode.BoolNode;
        if(!boolType.CompareType(condType)){
            throw new TypeCheckException(this, "Condition must return a bool type");
        }

        m_Body.CheckType();

        return null;
    }
}
