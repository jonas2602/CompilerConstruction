package FirstGrammar.parser;

import java.util.List;

import FirstGrammar.lexer.Token;
import FirstGrammar.lexer.TokenType;
import FirstGrammar.parser.tree.Node;
import FirstGrammar.parser.tree.TokenNode;
import FirstGrammar.parser.tree.rules.Rule_CONSTANT;
import FirstGrammar.parser.tree.rules.Rule_EXP;
import FirstGrammar.parser.tree.rules.Rule_EXPPRIME;
import FirstGrammar.parser.tree.rules.Rule_STM;

public class Parser {
	
	private List<Token> tokenStream;
	private int current;
	
	public Parser() {
		
	}
	
	public void error() {
		throw new RuntimeException("brech");
	}
	
	public TokenNode eat(TokenType t) {
		Token token = tokenStream.get(current);
		if(token.getTokenType() == t) {
			current++;
			return new TokenNode(token);
		}
		else {
			System.err.println("Expected "+t+" got "+token);
			error();
			return null;
		}
	}
	
	public TokenType getCurrent() {
		if(current == tokenStream.size()) {
			return TokenType.EOF;
		}
		
		return tokenStream.get(current).getTokenType();
	}
	
	public Node parse(List<Token> t) {
		tokenStream = t;
		current = 0;
		return stm();
	}
	
	public Rule_STM stm() {
		Rule_STM n = new Rule_STM();
		switch(getCurrent()) {
			case IF: {
				eat(TokenType.IF);
				n.setID(eat(TokenType.ID));
				eat(TokenType.THEN);
				n.setThenStm(stm());
				eat(TokenType.ELSE);
				n.setElseStm(stm());
				break;
			}
			default: {
				n.setExp(exp());
				break;
			}
		}
		return n;
	}
	
	public Rule_EXP exp() {
		Rule_EXP n = new Rule_EXP();
		n.setConstant(constants());
		n.setExpprime(expPrime());
		return n;
	}
	
	public Rule_EXPPRIME expPrime() {
		Rule_EXPPRIME n = new Rule_EXPPRIME();
		switch(getCurrent()) {
			case PLUS: {
				eat(TokenType.PLUS);
				n.setConstant(constants());
				n.setExpprime(expPrime());
				break;
			}
			default: {
				break;
			}
		}
		return n;
	}
	
	public Rule_CONSTANT constants() {
		Rule_CONSTANT n = new Rule_CONSTANT();
		switch(getCurrent()) {
			case ID: {
				n.setId(eat(TokenType.ID));
				break;
			}
			case NUM: {
				n.setNum(eat(TokenType.NUM));
				break;
			}
			case LEFTBRACKET: {
				eat(TokenType.LEFTBRACKET);
				n.setExp(exp());
				eat(TokenType.RIGHTBRACKET);
				break;
			}
			default: {
				break;
			}
		}
		return n;
	}
}
