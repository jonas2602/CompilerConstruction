package test.syntaxtree.types;

import test.syntaxtree.scopes.ScopeNode;

public class IntegerTypeIdentifierNode extends TypeIdentifierNode {
    public IntegerTypeIdentifierNode(ScopeNode parent) {
        super(parent);
        setType("integer");
    }
}
