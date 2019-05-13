package FirstGrammar.lexer;

public class StateMachine {

	private TokenType tokentype;
	private State startState;
	private State currentState;
	private boolean skipable;
	
	public StateMachine(TokenType t, boolean skip) {
		tokentype = t;
		currentState = null;
		skipable = skip;
	}
	
	public StateMachine(TokenType t) {
		this(t, false);
	}
	
	public boolean next(char c) {
		if(currentState == null) {
			return false;
		}
		
		currentState = currentState.next(c);
		
		return currentState != null;
	}
	
	public void setStartState(State s) {
		startState = s;
		reset();
	}
	
	public void reset() {
		currentState = startState;
	}
	
	public boolean isInEndState() {
		return currentState.isEndState();
	}
	
	public boolean isSkipable() {
		return skipable;
	}
	
	public TokenType getTokenType() {
		return tokentype;
	}
	
	public State getCurrentState() {
		return currentState;
	}
}
