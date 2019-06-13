package test.visitors;

import gen.PascalParser;
import test.syntaxtree.*;

import java.util.LinkedList;
import java.util.List;

public class PascalVisitor {

    public ProgramNode visitProgram(PascalParser.ProgramContext ctx) {
        ProgramNode program = new ProgramNode();
        program.setName(ctx.programHeading().identifier().getText());
        program.setBlock(visitBlock(ctx.block()));
        return program;
    }

    public BlockNode visitBlock(PascalParser.BlockContext ctx) {
        BlockNode block = new BlockNode();
        //vars
        List<PascalParser.VariableDeclarationPartContext> variables = ctx.variableDeclarationPart();
        for(PascalParser.VariableDeclarationPartContext vars: variables) {
            visitVariableDeclarationPart(vars, block);
        }

        //subs
        List<PascalParser.ProcedureAndFunctionDeclarationPartContext> subs = ctx.procedureAndFunctionDeclarationPart();
        for(PascalParser.ProcedureAndFunctionDeclarationPartContext sub: subs) {
            block.addSub(visitProcedureOrFunctionDeclaration(sub.procedureOrFunctionDeclaration()));
        }

        //labels
        List<PascalParser.LabelDeclarationPartContext> labels = ctx.labelDeclarationPart();
        for(PascalParser.LabelDeclarationPartContext labelblock: labels) {
            for(PascalParser.LabelContext label: labelblock.label()) {
                LabelDeclarationNode l = new LabelDeclarationNode();
                l.setValue(visitUnsignedInt(label.unsignedInteger()));
                block.addLabel(l);
            }
        }
        return block;
    }

    public int visitUnsignedInt(PascalParser.UnsignedIntegerContext ctx) {
        return Integer.parseInt(ctx.getText());
    }

    public void visitVariableDeclarationPart(PascalParser.VariableDeclarationPartContext ctx, BlockNode block) {
        List<PascalParser.VariableDeclarationContext> vars = ctx.variableDeclaration();
        for(PascalParser.VariableDeclarationContext var: vars) {
            for(String s: visitIdentifierList(var.identifierList())) {
                VariableDeclarationNode d = new VariableDeclarationNode();
                d.setName(s);
                block.addVariable(d);
            }
        }
    }

    public List<String> visitIdentifierList(PascalParser.IdentifierListContext ctx) {
        List<String> identifiers = new LinkedList<String>();
        List<PascalParser.IdentifierContext> list = ctx.identifier();

        for(PascalParser.IdentifierContext id: list) {
            identifiers.add(id.getText());
        }

        return identifiers;
    }

    public Node visitProcedureOrFunctionDeclaration(PascalParser.ProcedureOrFunctionDeclarationContext ctx) {
        if(ctx.procedureDeclaration() != null) {
            return visitProcedureDeclaration(ctx.procedureDeclaration());
        }
        else {
            return visitFunctionDeclaration(ctx.functionDeclaration());
        }
    }

    public ProcedureDeclarationNode visitProcedureDeclaration(PascalParser.ProcedureDeclarationContext ctx) {
        ProcedureDeclarationNode proc = new ProcedureDeclarationNode();
        proc.setName(ctx.identifier().getText());
        proc.setBlock(visitBlock(ctx.block()));
        return proc;
    }

    public FunctionDeclarationNode visitFunctionDeclaration(PascalParser.FunctionDeclarationContext ctx) {
        FunctionDeclarationNode func = new FunctionDeclarationNode();
        func.setName(ctx.identifier().getText());
        func.setBlock(visitBlock(ctx.block()));
        return func;
    }
}
