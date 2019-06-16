package test.syntaxtree.statements.calls;

import gen.PascalParser.FunctionDesignatorContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.subs.ProcedureDeclarationNode;

public class FunctionCall extends SubCall implements ASTBuilder<FunctionDesignatorContext> {

    public FunctionCall(ScopeNode parent) {
        super(parent);
    }

    @Override
    public void buildAST(FunctionDesignatorContext ctx) {
        name = ctx.identifier().getText();
        target = parentBlock.searchFunctionOrProcedure(name);
        if(target == null) {
            throw new RuntimeException("Couldn't find procedure with name "+name);
        }
        if(target instanceof ProcedureDeclarationNode) {
            throw new RuntimeException(name+" is not a function but a procedure in the current scope");
        }

        super.visitParameterList(ctx.parameterList());

        if(target.getParameters().size() != parameters.size()) {
            throw new RuntimeException("Mismatching number of arguments for procedure "+name+" (actual: "+target.getParameters().size()+" call: "+parameters.size()+")");
        }
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
