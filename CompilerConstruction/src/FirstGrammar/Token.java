package FirstGrammar;

public class Token {
	
	private String value;
	private TokenType type;
	
	public Token(TokenType t, String s) {
		this.type = t;
		this.value = s;
	}
	
	public TokenType getTokenType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("[%s '%s']", type.name(), value);
	}
}
