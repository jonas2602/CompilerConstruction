package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.statements.LabelNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class LabelDeclNode extends AbstractSyntaxTree {
    private String m_Name;

    private LabelNode m_LabelNode;

    public LabelDeclNode(String name) {
        m_Name = name;
        m_LabelNode = null;
    }

    public String GetName() {
        return m_Name;
    }

    public void SetLabelNode(LabelNode label) {
        m_LabelNode = label;
    }

    public LabelNode GetLabelNode() {
        return m_LabelNode;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return m_LabelNode.GetLabelParam();
    }
}
