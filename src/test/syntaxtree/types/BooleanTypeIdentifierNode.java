package test.syntaxtree.types;

import test.syntaxtree.scopes.ScopeNode;

public class BooleanTypeIdentifierNode extends TypeIdentifierNode {
    public BooleanTypeIdentifierNode(ScopeNode parent) {
        super(parent);
        setType("boolean");
    }
}
