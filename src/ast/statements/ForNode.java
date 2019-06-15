package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.TypeNode;

public class ForNode extends AbstractSyntaxTree {
    AbstractSyntaxTree m_Variable;
    AbstractSyntaxTree m_InitialValue;
    AbstractSyntaxTree m_FinalValue;
    boolean m_Increment;
    AbstractSyntaxTree m_Body;

    public ForNode(AbstractSyntaxTree InVariable, AbstractSyntaxTree InInitialValue, AbstractSyntaxTree InFinalValue, boolean bIncrement, AbstractSyntaxTree InBody) {
        this.m_Variable = InVariable;
        this.m_InitialValue = InInitialValue;
        this.m_FinalValue = InFinalValue;
        this.m_Increment = bIncrement;
        this.m_Body = InBody;

        m_Variable.SetParent(this);
        m_InitialValue.SetParent(this);
        m_FinalValue.SetParent(this);
        m_Body.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        // TODO: Which types are allowed for loops?
        TypeNode varType = m_Variable.CheckType();
        TypeNode initType = m_InitialValue.CheckType();
        TypeNode finalType = m_FinalValue.CheckType();
        if (!varType.CompareType(initType)) {
            throw new TypeCheckException(this, "Initial Value " + initType + " does not match variable " + varType);
        }

        if (!varType.CompareType(finalType)) {
            throw new TypeCheckException(this, "Final Value " + finalType + " does not match variable " + varType);
        }

        m_Body.CheckType();

        return null;
    }
}
