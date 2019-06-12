package test.syntaxtree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class ScopedNode extends Node {
    protected List<Node> childs;

    public ScopedNode() {
        super();
        childs = new LinkedList<Node>();
    }

    public void addChild(Node node) {
        this.childs.add(node);
    }

    public List<Node> getChilds() {
        return Collections.unmodifiableList(this.childs);
    }
}
