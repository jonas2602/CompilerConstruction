package ast.types;

import writer.wrapper.TypeWrapper;
import writer.wrapper.TypeWrapper_Other;

public class NullTypeNode extends TypeNode {

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode instanceof NullTypeNode)) {
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
