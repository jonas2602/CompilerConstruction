package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.TypeNode;

public class AssignmentNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Variable;
    private AbstractSyntaxTree m_Expression;

    public AssignmentNode(AbstractSyntaxTree InVariable, AbstractSyntaxTree InExpression) {
        this.m_Variable = InVariable;
        this.m_Expression = InExpression;

        m_Variable.SetParent(this);
        m_Expression.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        // Only Equal types are allowed, implicit conversion is not possible
        // even primitive conversion int->real, real->int is not possible
        TypeNode varType = m_Variable.CheckType();
        TypeNode expType = m_Expression.CheckType();
        if (!varType.CompareType(expType)) {
            throw new TypeCheckException(this, "Assignment of " + expType + " to " + varType + " is not allowed");
        }

        // Assignment is defined to not return a type.
        // In contrast to java,... a = b = 2; is not possible
        return null;
    }

}
