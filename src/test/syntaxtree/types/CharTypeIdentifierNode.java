package test.syntaxtree.types;

import test.syntaxtree.BlockNode;

public class CharTypeIdentifierNode extends TypeIdentifierNode {
    public CharTypeIdentifierNode(BlockNode parent) {
        super(parent);
        setType("char");
    }
}
