package test.syntaxtree.types.typeidentifiers;

import test.syntaxtree.scopes.ScopeNode;

public class RealTypeIdentifierNode extends TypeIdentifierNode {
    public RealTypeIdentifierNode(ScopeNode parent) {
        super(parent);
        setType("real");
    }
}
