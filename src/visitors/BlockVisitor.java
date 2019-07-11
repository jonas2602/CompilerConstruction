package visitors;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.declarations.*;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;
import java.util.List;

public class BlockVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    public BlockNode m_BlockNode;

    public BlockVisitor() {
        this(false);
    }

    public BlockVisitor(boolean mainBlock) {
        m_BlockNode = new BlockNode(mainBlock);
    }

    @Override
    public AbstractSyntaxTree visitBlock(PascalParser.BlockContext ctx) {
        // Label Declarations
        for (PascalParser.LabelDeclarationPartContext labelPart : ctx.labelDeclarationPart()) {
            for (PascalParser.LabelContext label : labelPart.label()) {
                visitLabel(label);
            }
        }

        // Constant Declarations
        for (PascalParser.ConstantDefinitionPartContext constPart : ctx.constantDefinitionPart()) {
            for (PascalParser.ConstantDefinitionContext constant : constPart.constantDefinition()) {
                visitConstantDefinition(constant);
            }
        }

        // Type Declarations
        ArrayList<AbstractSyntaxTree> typeDeclList = new ArrayList<AbstractSyntaxTree>();
        for (PascalParser.TypeDefinitionPartContext typePart : ctx.typeDefinitionPart()) {
            for (PascalParser.TypeDefinitionContext type : typePart.typeDefinition()) {
                visitTypeDefinition(type);
            }
        }

        // Variable Declarations
        for (PascalParser.VariableDeclarationPartContext varPart : ctx.variableDeclarationPart()) {
            for (PascalParser.VariableDeclarationContext variable : varPart.variableDeclaration()) {
                visitVariableDeclaration(variable);
            }
        }

        // Procedure + Function Declarations
        for (PascalParser.ProcedureAndFunctionDeclarationPartContext procOrFuncPart : ctx.procedureAndFunctionDeclarationPart()) {
            if (procOrFuncPart.procedureOrFunctionDeclaration().procedureDeclaration() != null) {
                visitProcedureDeclaration(procOrFuncPart.procedureOrFunctionDeclaration().procedureDeclaration());
            } else {
                visitFunctionDeclaration(procOrFuncPart.procedureOrFunctionDeclaration().functionDeclaration());
            }
        }

        // TODO: UnitPart

        // Compound Statement
        AbstractSyntaxTree compound = new StatementVisitor().visit(ctx.compoundStatement());
        if (compound != null) {
            m_BlockNode.SetCompoundStatement(compound);
        }

        return m_BlockNode;
    }

    @Override
    public AbstractSyntaxTree visitLabel(PascalParser.LabelContext ctx) {
        LabelDeclNode labelDecl = new LabelDeclNode(ctx.unsignedInteger().NUM_INT().getText());
        m_BlockNode.AddLabelDeclaration(labelDecl);
        return labelDecl;
    }

    @Override
    public AbstractSyntaxTree visitConstantDefinition(PascalParser.ConstantDefinitionContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        AbstractSyntaxTree constant = new ExpressionVisitor().visit(ctx.constant());

        ConstDeclNode constDecl = new ConstDeclNode(name, constant);
        m_BlockNode.AddConstantDeclaration(constDecl);
        return constDecl;
    }

    @Override
    public AbstractSyntaxTree visitTypeDefinition(PascalParser.TypeDefinitionContext ctx) {
        String name = ctx.identifier().IDENT().getText();
        TypeNode type = null;
        if (ctx.type() != null) {
            type = new TypeVisitor(m_BlockNode).visit(ctx.type());
        } else if (ctx.functionType() != null) {
            type = new TypeVisitor(m_BlockNode).visit(ctx.functionType());
        } else {
            type = new TypeVisitor(m_BlockNode).visit(ctx.procedureType());
        }

        TypeDeclNode typeDecl = new TypeDeclNode(name, type);
        m_BlockNode.AddTypeDeclaration(typeDecl);
        return typeDecl;
    }

    @Override
    public AbstractSyntaxTree visitVariableDeclaration(PascalParser.VariableDeclarationContext ctx) {
        TypeNode type = new TypeVisitor(m_BlockNode).visit(ctx.type());

        for (PascalParser.IdentifierContext ident : ctx.identifierList().identifier()) {
            m_BlockNode.AddVariableDeclaration(new VarDeclNode(ident.IDENT().getText(), type));
        }

        return null;
    }

    @Override
    public AbstractSyntaxTree visitFunctionDeclaration(PascalParser.FunctionDeclarationContext ctx) {
        // Create Function Node + Block
        String name = ctx.identifier().IDENT().getText();
        TypeNode returnType = new TypeVisitor(m_BlockNode).visit(ctx.resultType());
        // Make shure VarDeclNode is created before return type gets added to the FuncDeclNode, so that the parent of the return type gets overriden by the FuncDeclNode
        VarDeclNode funcTypeNode = new VarDeclNode(name, returnType);
        BlockNode body = (BlockNode) new BlockVisitor().visit(ctx.block());
        FuncDeclNode funcDecl = new FuncDeclNode(name, returnType, body);

        // Add return type to block as variable
        // body.AddParameterDeclaration(funcTypeNode);

        // Add Parameters to both, Procedure and Block
        if (ctx.formalParameterList() != null) {
            List<AbstractSyntaxTree> paramList = new ParameterVisitor().visit(ctx.formalParameterList());
            for (AbstractSyntaxTree param : paramList) {
                // TODO: Add params first to block for more percise Exceptions?
                funcDecl.AddParameter((ParamDeclNode) param);
                // body.AddParameterDeclaration((ParamDeclNode) param);
            }
        }

        // Add declarations to the new block
        m_BlockNode.AddFunctionDeclaration(funcDecl);
        return funcDecl;
    }

    @Override
    public AbstractSyntaxTree visitProcedureDeclaration(PascalParser.ProcedureDeclarationContext ctx) {
        // Create Procedure Node + Block
        String name = ctx.identifier().IDENT().getText();
        BlockNode body = (BlockNode) new BlockVisitor().visit(ctx.block());
        FuncDeclNode procDecl = new FuncDeclNode(name, new VoidTypeNode(), body);
        // ProcDeclNode procDecl = new ProcDeclNode(name, body);

        // Add Parameters to both, Procedure and Block
        List<AbstractSyntaxTree> paramList = new ParameterVisitor().visit(ctx.formalParameterList());
        for (AbstractSyntaxTree param : paramList) {
            // TODO: Add params first to block for more percise Exceptions?
            procDecl.AddParameter((ParamDeclNode) param);
            // body.AddParameterDeclaration((ParamDeclNode) param);
        }

        // Add declarations to the new block
//        m_BlockNode.AddProcedureDeclaration(procDecl);
        m_BlockNode.AddFunctionDeclaration(procDecl);
        return procDecl;
    }
}
