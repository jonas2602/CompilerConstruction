package FirstGrammar;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class Lexer {
	
	private List<StateMachine> stateMachines;
	
	public Lexer() {
		stateMachines = new LinkedList<StateMachine>();
		
		//ordered by priorty (rising)
		stateMachines.add(buildNum());
		stateMachines.add(buildID());
		
		//keywords
		stateMachines.add(buildIf());
		stateMachines.add(buildThen());
		stateMachines.add(buildElse());
		stateMachines.add(buildLeftBracket());
		stateMachines.add(buildRightBracket());
		stateMachines.add(buildPlus());
		
		//whitespace
		stateMachines.add(buildWhiteSpace());
	}
	
	public StateMachine buildNum() {
		StateMachine s = new StateMachine(TokenType.NUM);
		
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		State s3 = new State("3", true);
		
		s1.addTransition(s2, '1', '9');
		s1.addTransition(s3, '0');
		s2.addTransition(s2, '0', '9');
		
		s.setStartState(s1);
		
		return s;
	}
	
	public StateMachine buildWhiteSpace() {
		StateMachine s = new StateMachine(TokenType.WS, true);
		
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, ' ');
		s1.addTransition(s2, '\t');
		s1.addTransition(s2, '\n');
		s1.addTransition(s2, '\r');
		
		s2.addTransition(s2, ' ');
		s2.addTransition(s2, '\t');
		s2.addTransition(s2, '\n');
		s2.addTransition(s2, '\r');
		
		s.setStartState(s1);
		
		return s;
	}
	
	public StateMachine buildID() {
		StateMachine s = new StateMachine(TokenType.ID);
		
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, 'a', 'z');
		s1.addTransition(s2, 'A', 'Z');
		
		s2.addTransition(s2, 'a', 'z');
		s2.addTransition(s2, 'A', 'Z');
		s2.addTransition(s2, '0', '9');
		
		s.setStartState(s1);
		
		return s;
	}
	
	public StateMachine buildLeftBracket() {
		StateMachine s = new StateMachine(TokenType.LEFTBRACKET);
		
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, '(');
		
		s.setStartState(s1);
		
		return s;
	}
	
	public StateMachine buildRightBracket() {
		StateMachine s = new StateMachine(TokenType.RIGHTBRACKET);
		
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, ')');
		
		s.setStartState(s1);
		
		return s;
	}
	
	public StateMachine buildPlus() {
		StateMachine s = new StateMachine(TokenType.PLUS);
		
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, '+');
		
		s.setStartState(s1);
		
		return s;
	}
	
	public StateMachine buildIf() {
		StateMachine s = new StateMachine(TokenType.IF);
		
		State s1 = new State("1", false);
		State s2 = new State("2", false);
		State s3 = new State("3", true);
		
		s1.addTransition(s2, 'i');
		s2.addTransition(s3, 'f');
		
		s.setStartState(s1);
		
		return s;
	}
	
	public StateMachine buildThen() {
		StateMachine s = new StateMachine(TokenType.THEN);
		
		State s1 = new State("1", false);
		State s2 = new State("2", false);
		State s3 = new State("3", false);
		State s4 = new State("4", false);
		State s5 = new State("5", true);
		
		s1.addTransition(s2, 't');
		s2.addTransition(s3, 'h');
		s3.addTransition(s4, 'e');
		s4.addTransition(s5, 'n');
		
		s.setStartState(s1);
		
		return s;
	}
	
	public StateMachine buildElse() {
		StateMachine s = new StateMachine(TokenType.ELSE);
		
		State s1 = new State("1", false);
		State s2 = new State("2", false);
		State s3 = new State("3", false);
		State s4 = new State("4", false);
		State s5 = new State("5", true);
		
		s1.addTransition(s2, 'e');
		s2.addTransition(s3, 'l');
		s3.addTransition(s4, 's');
		s4.addTransition(s5, 'e');
		
		s.setStartState(s1);
		
		return s;
	}

	public List<Token> tokenize(InputStream input) throws IOException {
		
		List<Token> tokenstream = new LinkedList<Token>();
		
		StateMachine highestEndStateMachine = null;
		
		StringBuilder sb = new StringBuilder();
		
		int i = -1;
		while((i = input.read()) != -1) {
			char c = (char) i;
			
			boolean stillvalid = false;
			
			for(StateMachine s: stateMachines) {
				// System.out.println(s.getTokenType());
				
				if(s.next(c)) {
					stillvalid = true;
					highestEndStateMachine = s;
					System.out.println(s.getTokenType()+" "+s.getCurrentState()+" "+c);
				}
			}
			
			if(!stillvalid) {
				if(highestEndStateMachine == null) {
					throw new IOException("mööp");
				}
				
				input.reset();
				
				System.out.println("test");
				
				if(!highestEndStateMachine.isSkipable()) {
					tokenstream.add(new Token(highestEndStateMachine.getTokenType(), sb.toString()));
				}
				
				highestEndStateMachine = null;
				sb = new StringBuilder();
				for(StateMachine s: stateMachines) {
					s.reset();
				}
			}
			else {
				sb.append(c);
				System.out.println("Adding "+c);
				input.mark(1000);
			}
		}
		
		if(highestEndStateMachine != null && highestEndStateMachine.isInEndState()) {
			if(!highestEndStateMachine.isSkipable()) {
				tokenstream.add(new Token(highestEndStateMachine.getTokenType(), sb.toString()));
			}
		}
		
		return tokenstream;
	}
}
