package test.syntaxtree.subs;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.subs.SubNode;
import test.syntaxtree.types.TypeIdentifierNode;

public class FunctionDeclarationNode extends SubNode {

    private TypeIdentifierNode returnType;

    public FunctionDeclarationNode(BlockNode parent) {
        super(parent);
    }

    public void buildAST(PascalParser.FunctionDeclarationContext ctx) {
        name = ctx.identifier().getText();
        block = new BlockNode(parentBlock);
        block.buildAST(ctx.block());

        returnType = TypeIdentifierNode.buildAST(ctx.resultType().typeIdentifier(), parentBlock);
    }

    @Override
    public String toString() {
        return "FUNC "+name+" "+returnType;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
        super.printParameters(level);
        block.print(level);
    }
}
