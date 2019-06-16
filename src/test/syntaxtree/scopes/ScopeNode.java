package test.syntaxtree.scopes;

import gen.PascalParser;
import test.syntaxtree.Node;
import test.syntaxtree.declarations.ConstantDeclarationNode;
import test.syntaxtree.declarations.LabelDeclarationNode;
import test.syntaxtree.declarations.VariableDeclarationNode;
import test.syntaxtree.statements.StatementBlock;
import test.syntaxtree.subs.SubNode;
import test.visitors.PascalVisitor;

import java.util.Collections;
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
