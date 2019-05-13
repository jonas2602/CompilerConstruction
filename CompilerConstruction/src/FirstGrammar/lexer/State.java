package FirstGrammar.lexer;

import java.util.LinkedList;
import java.util.List;

public class State {

	private List<Transition> transitions;
	private boolean endState;
	private String name;
	
	public State(String name, boolean endState) {
		this.name = name;
		this.endState = endState;
		
		transitions = new LinkedList<Transition>();
	}
	
	public void addTransition(State s, char c) {
		transitions.add(new Transition(s,c));
	}
	
	public void addTransition(State s, char min, char max) {
		transitions.add(new Transition(s, min, max));
	}
	
	public State next(char c) {
		for(Transition t: transitions) {
			if(t.check(c)) {
				return t.getNextState();
			}
		}
		
		return null;
	}

	public boolean isEndState() {
		return endState;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
