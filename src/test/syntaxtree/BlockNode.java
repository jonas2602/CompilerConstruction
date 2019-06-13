package test.syntaxtree;

import gen.PascalParser;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BlockNode extends Node {
    private List<VariableDeclarationNode> variables;
    private List<Node> subs;
    private List<LabelDeclarationNode> labels;

    public BlockNode() {
        super();
        variables = new LinkedList<VariableDeclarationNode>();
        subs = new LinkedList<Node>();
        labels = new LinkedList<LabelDeclarationNode>();
    }

    public void addVariable(VariableDeclarationNode variable) {
        variables.add(variable);
    }

    public void addSub(Node sub) {
        subs.add(sub);
    }

    public void addLabel(LabelDeclarationNode label) {
        labels.add(label);
    }

    public List<Node> getVariables() {
        return Collections.unmodifiableList(variables);
    }

    public List<Node> getSubs() {
        return Collections.unmodifiableList(subs);
    }

    public void print(int level) {
        level += 2;

        for(Node n: variables) {
            n.print(level);
        }

        for(Node n: labels) {
            n.print(level);
        }

        for(Node n: subs) {
            n.print(level);
        }
    }
}
