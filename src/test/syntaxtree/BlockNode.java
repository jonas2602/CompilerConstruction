package test.syntaxtree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BlockNode extends Node {
    private List<Node> variables;
    private List<Node> subs;

    public BlockNode() {
        super();
        variables = new LinkedList<Node>();
        subs = new LinkedList<Node>();
    }

    public void addVariable(Node variable) {
        variables.add(variable);
    }

    public void addSub(Node sub) {
        subs.add(sub);
    }

    public List<Node> getVariables() {
        return Collections.unmodifiableList(variables);
    }

    public List<Node> getSubs() {
        return Collections.unmodifiableList(subs);
    }
}
