package FirstGrammar;

import java.util.LinkedList;
import java.util.List;

public abstract class Node {
	
}

class RuleNode extends Node {
	private List<Node> kids;
	
	public RuleNode() {
		super();
		this.kids = new LinkedList<Node>();
	}
	
	public void append(Node n) {
		kids.add(n);
	}
}

class TokenNode extends Node {
	private Token token;
	
	public TokenNode(Token token) {
		this.token = token;
	}
	
	public String getText() {
		return token.getValue();
	}
}
