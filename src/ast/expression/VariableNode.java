package ast.expression;

import ast.AbstractSyntaxTree;

public class VariableNode extends AbstractSyntaxTree {
    private String m_Name;

    public VariableNode(String InName){
        this.m_Name = InName;
    }
}
