package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.statements.LabelNode;

public class LabelDeclNode extends AbstractSyntaxTree {
    private String m_Name;

    private LabelNode m_LabelNode;

    public LabelDeclNode(String InName) {
        m_Name = InName;
        m_LabelNode = null;
    }

    public String GetName(){
        return m_Name;
    }

    public void SetLabelNode(LabelNode label) {
        m_LabelNode = label;
    }

    public LabelNode GetLabelNode() {
        return m_LabelNode;
    }
}
