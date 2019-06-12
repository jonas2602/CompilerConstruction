package test.visitors;

import gen.PascalBaseVisitor;
import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.DeclarationNode;
import test.syntaxtree.Node;
import test.syntaxtree.ProgramNode;

import java.util.LinkedList;
import java.util.List;

public class PascalVisitor extends PascalBaseVisitor<Node> {
    @Override
    public Node visitProgram(PascalParser.ProgramContext ctx) {
        ProgramNode program = new ProgramNode();
        program.setName(ctx.programHeading().identifier().getText());
        program.setBlock(visitBlock(ctx.block()));
        return program;
    }

    @Override
    public Node visitBlock(PascalParser.BlockContext ctx) {
        BlockNode block = new BlockNode();
        List<PascalParser.VariableDeclarationPartContext> variables = ctx.variableDeclarationPart();
        for(PascalParser.VariableDeclarationPartContext vars: variables) {
            visitVariableDeclarationPart(vars, block);
        }

        List<PascalParser.ProcedureAndFunctionDeclarationPartContext> subs = ctx.procedureAndFunctionDeclarationPart();
        for(PascalParser.ProcedureAndFunctionDeclarationPartContext sub: subs) {
            block.addSub(visitProcedureOrFunctionDeclaration(sub.procedureOrFunctionDeclaration()));
        }
        return block;
    }

    public void visitVariableDeclarationPart(PascalParser.VariableDeclarationPartContext ctx, BlockNode block) {
        List<PascalParser.VariableDeclarationContext> vars = ctx.variableDeclaration();
        for(PascalParser.VariableDeclarationContext var: vars) {
            for(String s: visitIdentifierListCustom(var.identifierList())) {
                DeclarationNode d = new DeclarationNode();
                d.setName(s);
                block.addVariable(d);
            }
        }
    }

    public List<String> visitIdentifierListCustom(PascalParser.IdentifierListContext ctx) {
        List<String> identifiers = new LinkedList<String>();
        List<PascalParser.IdentifierContext> list = ctx.identifier();

        for(PascalParser.IdentifierContext id: list) {
            identifiers.add(id.getText());
        }

        return identifiers;
    }

    @Override
    public Node visitProcedureOrFunctionDeclaration(PascalParser.ProcedureOrFunctionDeclarationContext ctx) {
        if(ctx.procedureDeclaration() != null) {
            return visitProcedureDeclaration(ctx.procedureDeclaration());
        }
        else {
            return visitFunctionDeclaration(ctx.functionDeclaration());
        }
    }
}
