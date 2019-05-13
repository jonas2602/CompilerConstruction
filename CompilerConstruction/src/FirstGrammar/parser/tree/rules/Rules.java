package FirstGrammar;

class Rule_STM extends RuleNode {
	public TokenNode ID;
	public Rule_STM thenStm;
	public Rule_STM elseStm;
	
	public Rule_EXP exp;
	
	public Rule_STM() {
		super();
	}
}

class Rule_EXP extends RuleNode {
	public Rule_CONSTANT constant;
	public Rule_EXPPRIME expprime;
	
	public Rule_EXP() {
		super();
	}
}

class Rule_EXPPRIME extends RuleNode {
	public Rule_CONSTANT constant;
	public Rule_EXPPRIME expprime;
	
	public Rule_EXPPRIME() {
		super();
	}
}

class Rule_CONSTANT extends RuleNode {
	public TokenNode NUM;
	public TokenNode ID;
	
	public Rule_EXP exp;
	
	public Rule_CONSTANT() {
		super();
	}
}
