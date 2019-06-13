package ast.statement;

import ast.AbstractSyntaxTree;

public class GotoNode extends AbstractSyntaxTree {
    private String m_Label;

    public GotoNode(String InLabel) {
        this.m_Label = InLabel;
    }
}
