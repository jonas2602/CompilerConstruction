package ast.types;

import java.util.Set;

public class WildcardTypeNode extends TypeNode {
    private TypeNode m_FilledType;
    private Class m_TypeRestriction;

    public WildcardTypeNode() {
        m_TypeRestriction = TypeNode.class;
    }

    public WildcardTypeNode(Class typeRestriction) {
        m_TypeRestriction = typeRestriction;
    }

    //reset filled type
    public void Clear() {
        m_FilledType = null;
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        //either fill again with a type or compare types (works because in the background all wildcards share same object)
        if (m_FilledType != null) {
            return m_FilledType.CompareType(otherTypeNode);
        }

        // in type node fits the given type restriction?
        if (!m_TypeRestriction.isInstance(otherTypeNode)) {
            return false;
        }

        // fill wildcard with compare type
        m_FilledType = otherTypeNode;
        return true;
    }

    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        return Set.of(this);
    }
}
