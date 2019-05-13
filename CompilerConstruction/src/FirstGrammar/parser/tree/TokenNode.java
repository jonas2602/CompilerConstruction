package FirstGrammar.parser.tree;

import FirstGrammar.lexer.Token;

public class TokenNode extends Node {
	private Token token;
	
	public TokenNode(Token token) {
		this.token = token;
	}
	
	public String getValue() {
		return token.getValue();
	}
}