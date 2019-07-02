package ast.statements;

import ast.AbstractSyntaxTree;

import java.util.List;

public class WithNode extends AbstractSyntaxTree {
    private List<AbstractSyntaxTree> m_Variables;
    private AbstractSyntaxTree m_Statement;

    public WithNode(List<AbstractSyntaxTree> variables, AbstractSyntaxTree statement){
        this.m_Variables = variables;
        this.m_Statement = statement;
    }
}
