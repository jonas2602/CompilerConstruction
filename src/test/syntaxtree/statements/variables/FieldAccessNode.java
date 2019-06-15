package test.syntaxtree.statements.variables;

import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;
import gen.PascalParser.FieldDesignatorContext;

public class FieldAccessNode extends VariableAccessNode implements ASTBuilder<FieldDesignatorContext> {

    public String name;

    public FieldAccessNode(BlockNode parent) {
        super(parent);
    }

    @Override
    public void buildAST(FieldDesignatorContext ctx) {
        name = ctx.identifier().getText();
    }

    @Override
    public String toString() {
        return "."+name;
    }
}
