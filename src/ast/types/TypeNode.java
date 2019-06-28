package ast.types;

import ast.AbstractSyntaxTree;
import writer.ParamContainer;
import writer.TypeWrapper;

public abstract class TypeNode extends AbstractSyntaxTree {
    public boolean CompareType(TypeNode OtherTypeNode) {
        return false;
    }

    // e.g. Type node is a named type and referes to a record type, the record definition would be returned
    public TypeNode GetTypeDetails() {
        return GetType();
    }

    public TypeNode GetCompareType() {
        return GetType();
    }

    public TypeWrapper GetWrappedType() {
        return null;
    }

    public ParamContainer GetDefaultValue() {
        return null;
    }
}
