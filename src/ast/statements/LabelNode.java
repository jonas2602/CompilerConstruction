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
    private ParamContainer m_Label;
    private VariableWrapper m_RawLabel;

    public LabelNode(String InLabelName) {
        this.m_LabelName = InLabelName;
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

    public ParamContainer GetLabel(GeneratorSlave slave) {
        //forward declaration
        if(m_Label == null) {
            m_Label = ParamContainer.LABELCONTAINER();
            m_RawLabel = slave.CreateLabelPlaceholder();
            m_Label.SetValueAccessor(m_RawLabel);
        }

        return m_Label;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        //create jumplabel
        ParamContainer label = GetLabel(slave);
        slave.PlaceLabel(m_RawLabel);

        return null;
    }
}

