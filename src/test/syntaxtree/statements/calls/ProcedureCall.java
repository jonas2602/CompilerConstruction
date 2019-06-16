package test.syntaxtree.statements.calls;

import gen.PascalParser.ProcedureStatementContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.scopes.ScopeNode;

public class ProcedureCall extends SubCall implements ASTBuilder<ProcedureStatementContext> {
    public ProcedureCall(ScopeNode parent) {
        super(parent);
    }

    @Override
    public void buildAST(ProcedureStatementContext ctx) {
        name = ctx.identifier().getText();

        if(ctx.LPAREN() != null) {
            super.visitParameterList(ctx.parameterList());
        }
    }

    @Override
    public void print(int level) {
        super.println(level, this);
    }
}
