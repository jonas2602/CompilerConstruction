package test.syntaxtree;

import gen.PascalParser;
import test.syntaxtree.declarations.ConstantDeclarationNode;
import test.syntaxtree.declarations.LabelDeclarationNode;
import test.syntaxtree.declarations.VariableDeclarationNode;
import test.syntaxtree.statements.LabelDefinitionNode;
import test.syntaxtree.statements.StatementBlock;
import test.syntaxtree.subs.SubNode;
import test.visitors.PascalVisitor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BlockNode extends Node {
    private List<VariableDeclarationNode> variables;
    private List<SubNode> subs;
    private List<LabelDeclarationNode> labels;
    private List<ConstantDeclarationNode> constants;

    private List<Node> statements;

    public BlockNode() {
        init();
    }

    public BlockNode(BlockNode parent) {
        super(parent);
        init();
    }

    private void init() {
        variables = new LinkedList<VariableDeclarationNode>();
        subs = new LinkedList<SubNode>();
        labels = new LinkedList<LabelDeclarationNode>();
        constants = new LinkedList<ConstantDeclarationNode>();

        statements = new LinkedList<Node>();
    }

    public void addVariable(VariableDeclarationNode variable) {
        variables.add(variable);
    }

    public void addSub(SubNode sub) {
        subs.add(sub);
    }

    public void addLabel(LabelDeclarationNode label) {
        labels.add(label);
    }

    public void addConstant(ConstantDeclarationNode con) {
        constants.add(con);
    }

    public void addStatement(Node statement) {
        statements.add(statement);
    }

    public List<VariableDeclarationNode> getVariables() {
        return Collections.unmodifiableList(variables);
    }

    public List<SubNode> getSubs() {
        return Collections.unmodifiableList(subs);
    }

    public void buildAST(PascalParser.BlockContext ctx) {
        //Declarations
        //vars
        List<PascalParser.VariableDeclarationPartContext> variables = ctx.variableDeclarationPart();
        for(PascalParser.VariableDeclarationPartContext varblock: variables) {
            List<PascalParser.VariableDeclarationContext> varlist = varblock.variableDeclaration();
            for(PascalParser.VariableDeclarationContext vars: varlist) {
                for (String s : PascalVisitor.visitIdentifierList(vars.identifierList())) {
                    VariableDeclarationNode variable = new VariableDeclarationNode(this);
                    variable.setName(s);
                    addVariable(variable);
                }
            }
        }

        //subs
        List<PascalParser.ProcedureAndFunctionDeclarationPartContext> subs = ctx.procedureAndFunctionDeclarationPart();
        for(PascalParser.ProcedureAndFunctionDeclarationPartContext sub: subs) {
            addSub(SubNode.buildAST(sub.procedureOrFunctionDeclaration(), this));
        }

        //labels
        List<PascalParser.LabelDeclarationPartContext> labels = ctx.labelDeclarationPart();
        for(PascalParser.LabelDeclarationPartContext labelblock: labels) {
            for(PascalParser.LabelContext label: labelblock.label()) {
                LabelDeclarationNode l = new LabelDeclarationNode(this);
                l.buildAST(label);
                addLabel(l);
            }
        }

        //const
        List<PascalParser.ConstantDefinitionPartContext> constblocks = ctx.constantDefinitionPart();
        for(PascalParser.ConstantDefinitionPartContext constlist: constblocks) {
            for(PascalParser.ConstantDefinitionContext con: constlist.constantDefinition()) {
                ConstantDeclarationNode constant = new ConstantDeclarationNode(this);
                constant.buildAST(con);
                addConstant(constant);
            }
        }


        //Now the "body" ...
        StatementBlock.processStatementBlock(ctx.compoundStatement(), this);
    }

    public void print(int level) {
        level += 2;

        for(Node n: constants) {
            n.print(level);
        }

        for(Node n: variables) {
            n.print(level);
        }

        for(Node n: labels) {
            n.print(level);
        }

        for(Node n: subs) {
            n.print(level);
        }

        super.println(level, "Begin");

        level += 2;

        for(Node n: statements) {
            n.print(level);
        }

        level -= 2;

        super.println(level, "End");
    }
}
