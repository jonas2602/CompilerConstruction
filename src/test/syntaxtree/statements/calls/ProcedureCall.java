package test.syntaxtree.statements.calls;

import gen.PascalParser.ProcedureStatementContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.subs.FunctionDeclarationNode;

public class ProcedureCall extends SubCall implements ASTBuilder<ProcedureStatementContext> {
    public ProcedureCall(ScopeNode parent) {
        super(parent);
    }

    @Override
    public void buildAST(ProcedureStatementContext ctx) {
        name = ctx.identifier().getText();

        target = parentBlock.searchFunctionOrProcedure(name);
        if(target == null) {
            throw new RuntimeException("Couldn't find procedure with name "+name);
        }

        if(ctx.LPAREN() != null) {
            super.visitParameterList(ctx.parameterList());
        }

        if(target.getParameters().size() != parameters.size()) {
            throw new RuntimeException("Mismatching number of arguments for procedure "+name+" (actual: "+target.getParameters().size()+" call: "+parameters.size()+")");
        }
    }

    @Override
    public void print(int level) {
        super.println(level, this);
    }
}
