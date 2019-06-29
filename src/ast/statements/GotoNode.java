package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.LabelDeclNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class GotoNode extends AbstractSyntaxTree {
    private String m_LabelName;
    private LabelDeclNode m_LabelDecl;

    public GotoNode(String InLabelName) {
        this.m_LabelName = InLabelName;
    }

    @Override
    public TypeNode CheckType() {
        m_LabelDecl = GetOwningBlock().GetLabelDeclaration(m_LabelName);
        if (m_LabelDecl == null) {
            throw new TypeCheckException(this, "Label called " + m_LabelName + " is not declared");
        }

        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        slave.CreateJump(m_LabelDecl.CreateSnippet(slave), false);

        return null;
    }
}
