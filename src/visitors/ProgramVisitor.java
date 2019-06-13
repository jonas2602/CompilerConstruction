package visitors;

import ast.AbstractSyntaxTree;
import ast.ProgramNode;
import ast.expression.VariableNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;

public class ProgramVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    @Override
    public AbstractSyntaxTree visitProgram(PascalParser.ProgramContext ctx) {
        AbstractSyntaxTree programNode = visitProgramHeading(ctx.programHeading());
        AbstractSyntaxTree blockNode = new BlockVisitor().visit(ctx.block());
        ((ProgramNode) programNode).SetBlock(blockNode);

        return programNode;
    }

    @Override
    public AbstractSyntaxTree visitProgramHeading(PascalParser.ProgramHeadingContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        ArrayList<AbstractSyntaxTree> params = new ArrayList<AbstractSyntaxTree>();
        if (ctx.identifierList() != null) {
            for (PascalParser.IdentifierContext ident : ctx.identifierList().identifier()) {
                params.add(new VariableNode(ident.IDENT().getText()));
            }
        }

        return new ProgramNode(name, params.toArray(AbstractSyntaxTree[]::new));
    }
}
