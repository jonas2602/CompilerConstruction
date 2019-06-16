package test.syntaxtree.scopes;

import test.syntaxtree.Node;
import test.syntaxtree.declarations.LabelDeclarationNode;
import test.syntaxtree.subs.SubNode;

import java.util.LinkedList;
import java.util.List;

public abstract class ScopeNode extends Node {

    private List<Node> statements;

    public ScopeNode(ScopeNode parent) {
        super(parent);
        statements = new LinkedList<Node>();
    }

    public void addStatement(Node statement) {
        statements.add(statement);
    }

    public abstract LabelDeclarationNode searchLabelDeclaration(String name);

    public LabelDeclarationNode searchLabelDeclarationGoto(String name) {
        return searchLabelDeclaration(name);
    }

    public abstract SubNode searchFunctionOrProcedure(String name);

    @Override
    public void print(int level) {
        super.println(level, "Begin");

        level += 2;

        for(Node n: statements) {
            n.print(level);
        }

        level -= 2;

        super.println(level, "End");
    }
}
