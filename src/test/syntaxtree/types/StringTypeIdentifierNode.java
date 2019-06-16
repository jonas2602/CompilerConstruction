package test.syntaxtree.types;

import test.syntaxtree.scopes.ScopeNode;

public class StringTypeIdentifierNode extends TypeIdentifierNode {
    public StringTypeIdentifierNode(ScopeNode parent) {
        super(parent);
        setType("string");
    }
}
