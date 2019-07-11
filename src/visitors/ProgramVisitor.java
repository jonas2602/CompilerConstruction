package visitors;

import ast.BlockNode;
import ast.ProgramNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;


public class ProgramVisitor extends PascalBaseVisitor<ProgramNode> {

    @Override
    public ProgramNode visitProgram(PascalParser.ProgramContext ctx) {
        ProgramNode programNode = visitProgramHeading(ctx.programHeading());
        BlockVisitor blockVis = new BlockVisitor(true);
        programNode.SetBlock(blockVis.m_BlockNode);
        blockVis.visit(ctx.block());

        return programNode;
    }

    @Override
    public ProgramNode visitProgramHeading(PascalParser.ProgramHeadingContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        ProgramNode prog = new ProgramNode(name);

        if (ctx.identifierList() != null) {
            for (PascalParser.IdentifierContext ident : ctx.identifierList().identifier()) {
                // TODO: How to handle Program Parameters?
                // prog.AddParameter(new AccessNode_Variable(ident.IDENT().getText()));
            }
        }

        return prog;
    }
}
