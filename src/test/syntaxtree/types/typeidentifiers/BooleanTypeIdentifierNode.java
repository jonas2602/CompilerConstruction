package test.syntaxtree.types.typeidentifiers;

import test.syntaxtree.scopes.ScopeNode;

public class BooleanTypeIdentifierNode extends TypeIdentifierNode {
    public BooleanTypeIdentifierNode(ScopeNode parent) {
        super(parent);
        setType("boolean");
    }
}
