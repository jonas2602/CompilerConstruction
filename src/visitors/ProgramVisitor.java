package visitors;

import ast.BlockNode;
import ast.ProgramNode;
import ast.expression.VariableNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;


public class ProgramVisitor extends PascalBaseVisitor<ProgramNode> {

    @Override
    public ProgramNode visitProgram(PascalParser.ProgramContext ctx) {
        ProgramNode programNode = visitProgramHeading(ctx.programHeading());
        BlockNode blockNode = (BlockNode) new BlockVisitor().visit(ctx.block());
        programNode.SetBlock(blockNode);

        return programNode;
    }

    @Override
    public ProgramNode visitProgramHeading(PascalParser.ProgramHeadingContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        ProgramNode prog = new ProgramNode(name);

        if (ctx.identifierList() != null) {
            for (PascalParser.IdentifierContext ident : ctx.identifierList().identifier()) {
                // TODO: How to handle Program Parameters?
                // prog.AddParameter(new VariableNode(ident.IDENT().getText()));
            }
        }

        return prog;
    }
}
