package ast.types;

public class WildCardPointer extends PointerTypeNode {

    public WildCardPointer() {
        super(VoidTypeNode.VoidNode);
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof PointerTypeNode)) {
            return false;
        }

        return true;
    }
}
