package test.syntaxtree.types;

import test.syntaxtree.BlockNode;

public class StringTypeIdentifierNode extends TypeIdentifierNode {
    public StringTypeIdentifierNode(BlockNode parent) {
        super(parent);
        setType("string");
    }
}
