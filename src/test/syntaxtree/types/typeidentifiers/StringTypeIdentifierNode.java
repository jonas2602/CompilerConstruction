package test.syntaxtree.types.typeidentifiers;

import test.syntaxtree.scopes.ScopeNode;

public class StringTypeIdentifierNode extends TypeIdentifierNode {
    public StringTypeIdentifierNode(ScopeNode parent) {
        super(parent);
        setType("string");
    }
}
