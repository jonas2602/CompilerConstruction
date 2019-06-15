package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.LabelDeclNode;
import ast.types.TypeNode;

public class GotoNode extends AbstractSyntaxTree {
    private String m_LabelName;

    public GotoNode(String InLabelName) {
        this.m_LabelName = InLabelName;
    }

    @Override
    public TypeNode CheckType() {
        LabelDeclNode LabelDecl = GetOwningBlock().GetLabelDeclaration(m_LabelName);
        if (LabelDecl == null) {
            throw new TypeCheckException(this, "Label called " + m_LabelName + " is not defined");
        }

        return null;
    }
}
