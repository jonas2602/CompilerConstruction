package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Parameter;
import writer.GeneratorSlave;

public class ParameterNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Expression;

    public ParameterNode(AbstractSyntaxTree InExpression) {
        m_Expression = InExpression;
        m_Expression.SetParent(this);
    }

    public AbstractSyntaxTree GetExpression() {
        return m_Expression;
    }

    @Override
    public TypeNode CheckType() {
        // Function or Procedure call will take care of actual type checking
        return m_Expression.CheckType();
    }

    @Override
    public TypeNode GetType() {
        return m_Expression.GetType();
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        TypeNode typeNode = m_Expression.GetType();
        CodeSnippet_Base typeSnippet = typeNode.CreateSnippet(slave, ctx);
        CodeSnippet_Base dataSnippet = m_Expression.CreateSnippet(slave, ctx);

        return new CodeSnippet_Parameter(typeSnippet, dataSnippet);
    }
}
