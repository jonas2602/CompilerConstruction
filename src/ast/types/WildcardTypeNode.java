package ast.types;

import java.util.Set;

public class WildcardTypeNode extends TypeNode {
    private TypeNode m_FilledType;


    public void Clear() {
        m_FilledType = null;
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

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
