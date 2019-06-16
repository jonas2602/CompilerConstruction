package test.syntaxtree.statements.calls;

import gen.PascalParser.FunctionDesignatorContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.scopes.ScopeNode;

public class FunctionCall extends SubCall implements ASTBuilder<FunctionDesignatorContext> {

    public FunctionCall(ScopeNode parent) {
        super(parent);
    }

    @Override
    public void buildAST(FunctionDesignatorContext ctx) {
        name = ctx.identifier().getText();
        super.visitParameterList(ctx.parameterList());
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
