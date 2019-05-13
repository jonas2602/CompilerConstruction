package FirstGrammar.parser.tree.rules;

import FirstGrammar.parser.tree.RuleNode;
import FirstGrammar.parser.tree.TokenNode;

public class Rule_CONSTANT extends RuleNode {
	private TokenNode num;
	private TokenNode id;
	
	private Rule_EXP exp;
	
	public Rule_CONSTANT() {
		super();
	}

	public TokenNode getNum() {
		return num;
	}

	public void setNum(TokenNode num) {
		this.num = num;
	}

	public TokenNode getId() {
		return id;
	}

	public void setId(TokenNode id) {
		this.id = id;
	}

	public Rule_EXP getExp() {
		return exp;
	}

	public void setExp(Rule_EXP exp) {
		this.exp = exp;
	}
}