package test.syntaxtree.types;

import test.syntaxtree.BlockNode;

public class IntegerTypeIdentifierNode extends TypeIdentifierNode {
    public IntegerTypeIdentifierNode(BlockNode parent) {
        super(parent);
        setType("integer");
    }
}
