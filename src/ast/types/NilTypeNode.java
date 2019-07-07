package ast.types;

import writer.wrappers.TypeWrapper;
import writer.wrappers.TypeWrapper_Other;

public class NilTypeNode extends PointerTypeNode {
    // Expands to: ((void *)0)

    public NilTypeNode() {
        super(VoidTypeNode.VoidNode);
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode instanceof PointerTypeNode)) {
            return false;
        }

        return true;
    }

    @Override
    public TypeWrapper GetWrappedType() {
        // TODO: are there other named types with special wrapped type?
        return TypeWrapper_Other.NULL;
    }
}
