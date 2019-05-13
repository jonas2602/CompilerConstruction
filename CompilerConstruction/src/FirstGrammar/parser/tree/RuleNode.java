package FirstGrammar.parser.tree;

import java.util.LinkedList;
import java.util.List;

public class RuleNode extends Node {
	private List<Node> kids;
	
	public RuleNode() {
		super();
		this.kids = new LinkedList<Node>();
	}
	
	public void append(Node n) {
		kids.add(n);
	}
}
