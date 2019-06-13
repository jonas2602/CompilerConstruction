package test.syntaxtree.types;

import test.syntaxtree.BlockNode;

public class RealTypeIdentifierNode extends TypeIdentifierNode {
    public RealTypeIdentifierNode(BlockNode parent) {
        super(parent);
        setType("real");
    }
}
