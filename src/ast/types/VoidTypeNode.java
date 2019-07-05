package ast.types;

import writer.wrapper.TypeWrapper;
import writer.wrapper.TypeWrapper_Other;

public class VoidTypeNode extends TypeNode {

    public static final VoidTypeNode VoidNode = new VoidTypeNode();

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode instanceof VoidTypeNode)) {
            return false;
        }

        return true;
    }

    @Override
    public TypeWrapper GetWrappedType() {
        // TODO: are there other named types with special wrapped type?
        return TypeWrapper_Other.VOID;
    }
}
