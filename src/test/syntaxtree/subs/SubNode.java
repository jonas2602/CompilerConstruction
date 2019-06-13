package test.syntaxtree.subs;

import gen.PascalParser;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.subs.parameters.*;
import test.syntaxtree.types.TypeIdentifierNode;
import test.visitors.PascalVisitor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class SubNode extends Node {
    protected String name;
    protected BlockNode block;
    protected List<NormalParameterNode> normalParameters;
    protected List<FunctionParameterNode> functionParameters;
    protected List<ProcedureParameterNode> procedureParameters;
    protected List<VarParameterNode> varParameters;

    public SubNode(BlockNode parent) {
        super(parent);
        normalParameters = new LinkedList<NormalParameterNode>();
        functionParameters = new LinkedList<FunctionParameterNode>();
        procedureParameters = new LinkedList<ProcedureParameterNode>();
        varParameters = new LinkedList<VarParameterNode>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBlock(BlockNode block) {
        this.block = block;
    }

    public BlockNode getBlock() {
        return this.block;
    }

    public List<NormalParameterNode> getParameters() {
        return Collections.unmodifiableList(normalParameters);
    }

    public static SubNode buildAST(PascalParser.ProcedureOrFunctionDeclarationContext ctx, BlockNode parent) {
        if(ctx.functionDeclaration() != null) {
            PascalParser.FunctionDeclarationContext fuctx = ctx.functionDeclaration();
            FunctionDeclarationNode func = new FunctionDeclarationNode(parent);
            func.buildAST(fuctx);

            if(fuctx.formalParameterList() != null) {
                func.buildASTParameter(fuctx.formalParameterList());
            }

            return func;
        }
        PascalParser.ProcedureDeclarationContext proctx = ctx.procedureDeclaration();
        ProcedureDeclarationNode proc = new ProcedureDeclarationNode(parent);
        proc.buildAST(proctx);

        if(proctx.formalParameterList() != null) {
            proc.buildASTParameter(proctx.formalParameterList());
        }

        return proc;
    }

    public void buildASTParameter(PascalParser.FormalParameterListContext ctx) {
        List<PascalParser.FormalParameterSectionContext> sections = ctx.formalParameterSection();
        for(PascalParser.FormalParameterSectionContext section: sections) {
            PascalParser.ParameterGroupContext group = section.parameterGroup();
            TypeIdentifierNode type = TypeIdentifierNode.buildAST(group.typeIdentifier(), parentBlock);
            List<String> names = PascalVisitor.visitIdentifierList(group.identifierList());

            for(String s: names) {
                if(section.FUNCTION() != null) {
                    FunctionParameterNode function = new FunctionParameterNode(parentBlock);
                    function.setIdentifier(s);
                    function.setType(type);
                    functionParameters.add(function);
                }
                else if(section.PROCEDURE() != null) {
                    ProcedureParameterNode procedure = new ProcedureParameterNode(parentBlock);
                    procedure.setIdentifier(s);
                    procedure.setType(type);
                    procedureParameters.add(procedure);
                }
                else if(section.VAR() != null) {
                    VarParameterNode var = new VarParameterNode(parentBlock);
                    var.setIdentifier(s);
                    var.setType(type);
                    varParameters.add(var);
                }
                else {
                    //normal
                    NormalParameterNode normal = new NormalParameterNode(parentBlock);
                    normal.setIdentifier(s);
                    normal.setType(type);
                    normalParameters.add(normal);
                }
            }
        }
    }

    public void printParameters(int level) {
        printParameter(level, functionParameters);
        printParameter(level, procedureParameters);
        printParameter(level, varParameters);
        printParameter(level, normalParameters);
    }

    private <T extends ParameterNode> void printParameter(int level, List<T> parameters) {
        if(parameters.size() == 0) {
            return;
        }

        super.print(level, "");
        for(Node n: parameters) {
            n.print(level);
            super.print(", ");
        }
        System.out.println();
    }
}
