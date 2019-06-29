package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.LabelDeclNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.ValueWrapper;
import writer.VariableWrapper;

public class LabelNode extends AbstractSyntaxTree {
    private String m_LabelName;
    private ParamContainer m_LabelContainer;

    public LabelNode(String InLabelName) {
        m_LabelName = InLabelName;
        m_LabelContainer = ParamContainer.LABELCONTAINER();
    }

    @Override
    public TypeNode CheckType() {
        LabelDeclNode labelDecl = GetOwningBlock().GetLabelDeclaration(m_LabelName);
        if (labelDecl == null) {
            throw new TypeCheckException(this, "Label " + m_LabelName + " defined but not declared!");
        }

        if (labelDecl.GetLabelNode() != null) {
            throw new TypeCheckException(this, "Label " + m_LabelName + " is already defined!");
        }

        labelDecl.SetLabelNode(this);
        return null;
    }

    public ParamContainer GetLabelParam() {
        return m_LabelContainer;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // create jump to label
        slave.CreateJump(m_LabelContainer);

        // Create new block starting with the label
        ValueWrapper labelValue = slave.CreateLabel();
        m_LabelContainer.SetValueAccessor(labelValue);

        return null;
    }
}

