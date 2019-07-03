package ast.types;

import java.util.List;

// TODO: !!!
public class EnumTypeNode extends TypeNode {
    private List<String> m_Names;

    public EnumTypeNode(List<String> names) {
        m_Names = names;
    }

    @Override
    public boolean CompareType(TypeNode otherNodeType) {
//        TypeNode otherCompareType = OtherTypeNode.GetCompareType();
//
//        // Is the given type a valid name node?
//        if (!(otherCompareType instanceof EnumTypeNode)) {
//            return false;
//        }
//
//        EnumTypeNode otherScalarNode = (EnumTypeNode) otherCompareType;
//
//        // equal element count?
//        if (otherScalarNode.m_NamedTypes.size() != m_NamedTypes.size()) {
//            return false;
//        }
//
//        // compare elements
//        for (int i = 0; i < m_NamedTypes.size(); i++) {
//            // types are equal?
//            if (!m_NamedTypes.get(i).CompareType(otherScalarNode.m_NamedTypes.get(i))) {
//                return false;
//            }
//        }

        return false;
    }

    @Override
    public TypeNode CheckType() {
//        for (NamedTypeNode Element : m_NamedTypes) {
//            Element.CheckType();
//        }

        return this;
    }
}
