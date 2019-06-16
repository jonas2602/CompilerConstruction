package test.syntaxtree.subs;

import gen.PascalParser.FunctionDeclarationContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.scopes.BlockNode;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.types.TypeIdentifierNode;

public class FunctionDeclarationNode extends SubNode implements ASTBuilder<FunctionDeclarationContext> {

    private TypeIdentifierNode returnType;

    public FunctionDeclarationNode(ScopeNode parent) {
        super(parent);
    }

    public void buildAST(FunctionDeclarationContext ctx) {
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
