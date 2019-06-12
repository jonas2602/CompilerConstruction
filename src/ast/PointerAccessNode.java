package ast;

public class PointerAccessNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Child;

    public PointerAccessNode(AbstractSyntaxTree InChild) {
        this.m_Child = InChild;
    }
}
