package ast.types;

import java.util.Set;

public class WildcardTypeNode extends TypeNode {
    private TypeNode m_FilledType;

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
        } else {
            m_FilledType = otherTypeNode;
            return true;
        }
    }

    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        return Set.of(this);
    }
}
