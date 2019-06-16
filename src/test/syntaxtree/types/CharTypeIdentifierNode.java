package test.syntaxtree.types;

import test.syntaxtree.scopes.ScopeNode;

public class CharTypeIdentifierNode extends TypeIdentifierNode {
    public CharTypeIdentifierNode(ScopeNode parent) {
        super(parent);
        setType("char");
    }
}
