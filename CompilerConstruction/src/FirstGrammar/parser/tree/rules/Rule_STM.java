package FirstGrammar.parser.tree.rules;

import FirstGrammar.parser.tree.RuleNode;
import FirstGrammar.parser.tree.TokenNode;

public class Rule_STM extends RuleNode {
	private TokenNode ID;
	private Rule_STM thenStm;
	private Rule_STM elseStm;
	
	private Rule_EXP exp;
	
	public Rule_STM() {
		super();
	}

	public TokenNode getID() {
		return ID;
	}

	public void setID(TokenNode iD) {
		ID = iD;
	}

	public Rule_STM getThenStm() {
		return thenStm;
	}

	public void setThenStm(Rule_STM thenStm) {
		this.thenStm = thenStm;
	}

	public Rule_STM getElseStm() {
		return elseStm;
	}

	public void setElseStm(Rule_STM elseStm) {
		this.elseStm = elseStm;
	}

	public Rule_EXP getExp() {
		return exp;
	}

	public void setExp(Rule_EXP exp) {
		this.exp = exp;
	}
}
