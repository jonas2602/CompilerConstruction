package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.LabelDeclNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class GotoNode extends AbstractSyntaxTree {
    private String m_LabelName;
    private LabelDeclNode m_LabelDecl;

    public GotoNode(String labelName) {
        this.m_LabelName = labelName;
    }

    @Override
    public TypeNode CheckType() {
        m_LabelDecl = GetOwningBlock().GetLabelDeclaration(m_LabelName);
        if (m_LabelDecl == null) {
            throw new TypeCheckException(this, "Label called " + m_LabelName + " is not declared");
        }

        return VoidTypeNode.VoidNode;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        slave.CreateJump(m_LabelDecl.CreateSnippet(slave), false);

        return ParamContainer.VOIDCONTAINER();
    }
}
