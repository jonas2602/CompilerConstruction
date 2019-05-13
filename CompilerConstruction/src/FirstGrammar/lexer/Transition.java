package FirstGrammar.lexer;

public class Transition {

	private char min;
	private char max;
	private State nextState;
	
	public Transition(State nextState, char min, char max) {
		this.nextState = nextState;
		this.min = min;
		this.max = max;
	}
	
	public Transition(State nextState, char c) {
		this(nextState, c, c);
	}
	
	public boolean check(char c) {
		//System.out.println(c+" "+(int)c+" | "+min+" "+(int)min+" | "+max+" "+(int)max);
		return min <= c && max >= c;
	}
	
	public State getNextState() {
		return nextState;
	}
}
