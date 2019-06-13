package visitors;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.declaration.*;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;
import java.util.List;

public class BlockVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    @Override
    public AbstractSyntaxTree visitBlock(PascalParser.BlockContext ctx) {
        // Label Declarations
        ArrayList<AbstractSyntaxTree> labelDeclList = new ArrayList<AbstractSyntaxTree>();
        for (PascalParser.LabelDeclarationPartContext labelPart : ctx.labelDeclarationPart()) {
            for (PascalParser.LabelContext label : labelPart.label()) {
                AbstractSyntaxTree labelDecl = visitLabel(label);
                labelDeclList.add(labelDecl);
            }
        }

        // Constant Declarations
        ArrayList<AbstractSyntaxTree> constDeclList = new ArrayList<AbstractSyntaxTree>();
        for (PascalParser.ConstantDefinitionPartContext constPart : ctx.constantDefinitionPart()) {
            for (PascalParser.ConstantDefinitionContext constant : constPart.constantDefinition()) {
                AbstractSyntaxTree constDecl = visitConstantDefinition(constant);
                constDeclList.add(constDecl);
            }
        }

        // Type Declarations
        ArrayList<AbstractSyntaxTree> typeDeclList = new ArrayList<AbstractSyntaxTree>();
        for (PascalParser.TypeDefinitionPartContext typePart : ctx.typeDefinitionPart()) {
            for (PascalParser.TypeDefinitionContext type : typePart.typeDefinition()) {
                AbstractSyntaxTree typeDecl = visitTypeDefinition(type);
                typeDeclList.add(typeDecl);
            }
        }

        // Variable Declarations
        ArrayList<AbstractSyntaxTree> varDeclList = new ArrayList<AbstractSyntaxTree>();
        for (PascalParser.VariableDeclarationPartContext varPart : ctx.variableDeclarationPart()) {
            for (PascalParser.VariableDeclarationContext variable : varPart.variableDeclaration()) {
                List<AbstractSyntaxTree> varDecl = visitVariableDeclarationList(variable);
                varDeclList.addAll(varDecl);
            }
        }

        // Procedure + Function Declarations
        ArrayList<AbstractSyntaxTree> funcDeclList = new ArrayList<AbstractSyntaxTree>();
        ArrayList<AbstractSyntaxTree> procDeclList = new ArrayList<AbstractSyntaxTree>();
        for (PascalParser.ProcedureAndFunctionDeclarationPartContext procOrFuncPart : ctx.procedureAndFunctionDeclarationPart()) {
            if (procOrFuncPart.procedureOrFunctionDeclaration().procedureDeclaration() != null) {
                AbstractSyntaxTree procDecl = visitProcedureDeclaration(procOrFuncPart.procedureOrFunctionDeclaration().procedureDeclaration());
                procDeclList.add(procDecl);
            } else {
                AbstractSyntaxTree funcDecl = visitFunctionDeclaration(procOrFuncPart.procedureOrFunctionDeclaration().functionDeclaration());
                funcDeclList.add(funcDecl);
            }
        }

        // TODO: UnitPart

        // Compound Statement
        AbstractSyntaxTree compound = new StatementVisitor().visit(ctx.compoundStatement());

        return new BlockNode(labelDeclList.toArray(AbstractSyntaxTree[]::new), constDeclList.toArray(AbstractSyntaxTree[]::new), typeDeclList.toArray(AbstractSyntaxTree[]::new), varDeclList.toArray(AbstractSyntaxTree[]::new), procDeclList.toArray(AbstractSyntaxTree[]::new), funcDeclList.toArray(AbstractSyntaxTree[]::new), compound);
    }

    @Override
    public AbstractSyntaxTree visitLabel(PascalParser.LabelContext ctx) {
        return new LabelDeclNode(ctx.unsignedInteger().NUM_INT().getText());
    }

    @Override
    public AbstractSyntaxTree visitConstantDefinition(PascalParser.ConstantDefinitionContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        AbstractSyntaxTree constant = new ConstantVisitor().visit(ctx.constant());
        return new ConstDeclNode(name, constant);
    }

    @Override
    public AbstractSyntaxTree visitTypeDefinition(PascalParser.TypeDefinitionContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        AbstractSyntaxTree type = null;
        if (ctx.type() != null) {
            type = new TypeVisitor().visit(ctx.type());
        } else if (ctx.functionType() != null) {
            type = new TypeVisitor().visit(ctx.functionType());
        } else {
            type = new TypeVisitor().visit(ctx.procedureType());
        }

        return new TypeDeclNode(name, type);
    }

    public List<AbstractSyntaxTree> visitVariableDeclarationList(PascalParser.VariableDeclarationContext ctx) {
        AbstractSyntaxTree type = new TypeVisitor().visit(ctx.type());

        ArrayList<AbstractSyntaxTree> varDeclList = new ArrayList<AbstractSyntaxTree>();
        for (PascalParser.IdentifierContext ident : ctx.identifierList().identifier()) {
            varDeclList.add(new VarDeclNode(ident.IDENT().getText(), type));
        }

        return varDeclList;
    }

    @Override
    public AbstractSyntaxTree visitFunctionDeclaration(PascalParser.FunctionDeclarationContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        ArrayList<AbstractSyntaxTree> params = new ArrayList<AbstractSyntaxTree>();
        AbstractSyntaxTree returnType = new TypeVisitor().visit(ctx.resultType());
        AbstractSyntaxTree body = new BlockVisitor().visit(ctx.block());

        return new FuncDeclNode(name, params.toArray(AbstractSyntaxTree[]::new), returnType, body);
    }

    @Override
    public AbstractSyntaxTree visitProcedureDeclaration(PascalParser.ProcedureDeclarationContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        ArrayList<AbstractSyntaxTree> params = new ArrayList<AbstractSyntaxTree>();
        AbstractSyntaxTree body = new BlockVisitor().visit(ctx.block());

        return new ProcDeclNode(name, params.toArray(AbstractSyntaxTree[]::new), body);
    }
}
