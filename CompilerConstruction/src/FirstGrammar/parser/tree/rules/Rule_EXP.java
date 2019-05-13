package FirstGrammar.parser.tree.rules;

import FirstGrammar.parser.tree.RuleNode;

public class Rule_EXP extends RuleNode {
	private Rule_CONSTANT constant;
	private Rule_EXPPRIME expprime;
	
	public Rule_EXP() {
		super();
	}

	public Rule_CONSTANT getConstant() {
		return constant;
	}

	public void setConstant(Rule_CONSTANT constant) {
		this.constant = constant;
	}

	public Rule_EXPPRIME getExpprime() {
		return expprime;
	}

	public void setExpprime(Rule_EXPPRIME expprime) {
		this.expprime = expprime;
	}
}