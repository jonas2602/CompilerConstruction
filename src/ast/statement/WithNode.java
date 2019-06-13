package ast.statement;

import ast.AbstractSyntaxTree;

public class WithNode extends AbstractSyntaxTree {
    AbstractSyntaxTree[] m_Variables;
    AbstractSyntaxTree m_Statement;

    public WithNode(AbstractSyntaxTree[] InVariables, AbstractSyntaxTree InStatement){
        this.m_Variables = InVariables;
        this.m_Statement = InStatement;
    }
}
