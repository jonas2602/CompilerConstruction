package ast.statements;

import ast.AbstractSyntaxTree;

import java.util.List;

public class WithNode extends AbstractSyntaxTree {
    List<AbstractSyntaxTree> m_Variables;
    AbstractSyntaxTree m_Statement;

    public WithNode(List<AbstractSyntaxTree> InVariables, AbstractSyntaxTree InStatement){
        this.m_Variables = InVariables;
        this.m_Statement = InStatement;
    }
}
