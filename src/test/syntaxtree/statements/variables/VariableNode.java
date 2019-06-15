package test.syntaxtree.statements.variables;

import gen.PascalParser;
import gen.PascalParser.VariableContext;
import test.syntaxtree.ASTBuilder;
import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.statements.variables.VariableAccessNode;

import java.util.LinkedList;
import java.util.List;

public class VariableNode extends Node implements ASTBuilder<VariableContext> {

    private String name;
    private List<VariableAccessNode> accessNodes;

    public VariableNode(BlockNode parent) {
        super(parent);
        accessNodes = new LinkedList<VariableAccessNode>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void buildAST(VariableContext ctx) {
        name = ctx.identifier().getText();

        for(PascalParser.VariableAccessContext context: ctx.variableAccess()) {
            accessNodes.add(VariableAccessNode.buildAST(context, parentBlock));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        for(VariableAccessNode v: accessNodes) {
            builder.append(v);
        }

        return builder.toString();
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
