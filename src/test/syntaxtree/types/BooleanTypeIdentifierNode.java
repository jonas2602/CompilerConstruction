package test.syntaxtree.types;

import test.syntaxtree.BlockNode;

public class BooleanTypeIdentifierNode extends TypeIdentifierNode {
    public BooleanTypeIdentifierNode(BlockNode parent) {
        super(parent);
        setType("boolean");
    }
}
