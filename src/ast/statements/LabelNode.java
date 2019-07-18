package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.LabelDeclNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.ValueWrapper;

public class LabelNode extends AbstractSyntaxTree {
    private String m_LabelName;
    private ParamContainer m_LabelContainer;

    public LabelNode(String labelName) {
        m_LabelName = labelName;
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
        return VoidTypeNode.VoidNode;
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

        return ParamContainer.VOIDCONTAINER();
    }
}

