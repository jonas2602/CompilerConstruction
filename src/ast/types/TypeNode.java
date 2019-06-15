package ast.types;

import ast.AbstractSyntaxTree;

public class TypeNode extends AbstractSyntaxTree {
    public boolean CompareType(TypeNode OtherTypeNode) {
        return false;
    }

    // e.g. Type node is a named type and referes to a record type, the record definition would be returned
    public TypeNode GetTypeDetails() {
        return GetType();
    }

    public TypeNode GetCompareType() { return GetType(); }
}
