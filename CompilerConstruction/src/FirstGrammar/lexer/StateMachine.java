package FirstGrammar.lexer;

public class StateMachine {

	private TokenType tokenType;
	private State startState;
	private State currentState;
	private boolean isSkipable;
	
	public StateMachine(TokenType t, State startState, boolean skip) {
		tokenType = t;
		isSkipable = skip;
		setStartState(startState);
	}
	
	public StateMachine(TokenType t, State startState) {
		this(t, startState, false);
	}
	
	public StateMachine(TokenType t, boolean skip) {
		this(t, null, skip);
	}
	
	public StateMachine(TokenType t) {
		this(t, null, false);
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
		if(currentState == null) {
			return false;
		}
		
		return currentState.isEndState();
	}
	
	public boolean isSkipable() {
		return isSkipable;
	}
	
	public TokenType getTokenType() {
		return tokenType;
	}
	
	public State getCurrentState() {
		return currentState;
	}
}
