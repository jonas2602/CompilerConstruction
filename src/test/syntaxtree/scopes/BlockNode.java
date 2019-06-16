package test.syntaxtree.scopes;

import gen.PascalParser;
import test.syntaxtree.Node;
import test.syntaxtree.declarations.ConstantDeclarationNode;
import test.syntaxtree.declarations.LabelDeclarationNode;
import test.syntaxtree.declarations.VariableDeclarationNode;
import test.syntaxtree.statements.LabelDefinitionNode;
import test.syntaxtree.statements.StatementBlock;
import test.syntaxtree.subs.SubNode;
import test.visitors.PascalVisitor;

import java.util.*;

public class BlockNode extends ScopeNode {
    private List<VariableDeclarationNode> variables;
    private HashMap<String, SubNode> subs;
    private HashMap<String, LabelDeclarationNode> labels;
    private List<ConstantDeclarationNode> constants;

    public BlockNode() {
        super(null);
        init();
    }

    public BlockNode(ScopeNode parent) {
        super(parent);
        init();
    }

    private void init() {
        variables = new LinkedList<VariableDeclarationNode>();
        subs = new HashMap<String, SubNode>();
        labels = new HashMap<String, LabelDeclarationNode>();
        constants = new LinkedList<ConstantDeclarationNode>();
    }

    public void addVariable(VariableDeclarationNode variable) {
        variables.add(variable);
    }

    public void addSub(SubNode sub) {
        subs.put(sub.getName(), sub);
    }

    public void addLabel(LabelDeclarationNode label) {
        labels.put(label.getValue(), label);
    }

    public void addConstant(ConstantDeclarationNode con) {
        constants.add(con);
    }

    public LabelDeclarationNode searchLabelDeclaration(String name) {
        //search local labels
        LabelDeclarationNode local = labels.get(name);
        if(local != null) {
            return local;
        }

        //check if there is an outer scope
        if(parentBlock != null) {
            return parentBlock.searchLabelDeclaration(name);
        }

        return null;
    }

    public SubNode searchFunctionOrProcedure(String name) {
        //search local
        SubNode local = subs.get(name);
        if(local != null) {
            return local;
        }

        //check if there is an outer scope
        if(parentBlock != null) {
            return parentBlock.searchFunctionOrProcedure(name);
        }

        return null;
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

        printMap(level, labels);
        printMap(level, subs);

        super.print(level);
    }

    private <T extends Node> void printMap(int level, Map<?, T> map) {
        for(Map.Entry<?, T> entry : map.entrySet()) {
            entry.getValue().print(level);
        }
    }
}
