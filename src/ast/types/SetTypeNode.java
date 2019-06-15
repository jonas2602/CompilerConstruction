package ast.types;

public class SetTypeNode extends TypeNode {
    private TypeNode m_ElementType;

    public SetTypeNode(TypeNode InType) {
        m_ElementType = InType;
    }

    @Override
    public TypeNode CheckType() {
        m_ElementType.CheckType();

        return super.CheckType();
    }

    @Override
    public TypeNode GetType() {
        return this;
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_ElementType;
    }
}
