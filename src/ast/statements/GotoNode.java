package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.LabelDeclNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class GotoNode extends AbstractSyntaxTree {
    private String m_LabelName;
    private LabelDeclNode m_Label;

    public GotoNode(String InLabelName) {
        this.m_LabelName = InLabelName;
    }

    @Override
    public TypeNode CheckType() {
        m_Label = GetOwningBlock().GetLabelDeclaration(m_LabelName);
        if (m_Label == null) {
            throw new TypeCheckException(this, "Label called " + m_LabelName + " is not declared");
        }

        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        LabelNode n = m_Label.GetLabelNode();
        if(n == null) {
            throw new TypeCheckException(this, "Label called " + m_LabelName + " is not defined");
        }

        slave.CreateJump(n.GetLabel(slave));

        return null;
    }
}
