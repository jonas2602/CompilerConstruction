package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;

public class ArrayTypeNode extends TypeNode {
    private AbstractSyntaxTree m_ElementCounter;
    private TypeNode m_ElementType;

    public ArrayTypeNode(AbstractSyntaxTree InElementCounter, TypeNode InElementType) {
        m_ElementCounter = InElementCounter;
        m_ElementType = InElementType;

        m_ElementCounter.SetParent(this);
        m_ElementType.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        m_ElementType.CheckType();

        TypeNode counterType = m_ElementCounter.CheckType();
        if(!(counterType instanceof RangeTypeNode)){
            throw new TypeCheckException(this, "Element Count of an array must be defined by a range");
        }

        return GetType();
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
