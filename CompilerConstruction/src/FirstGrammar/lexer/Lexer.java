package FirstGrammar.lexer;

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
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		State s3 = new State("3", true);
		
		s1.addTransition(s2, '1', '9');
		s1.addTransition(s3, '0');
		s2.addTransition(s2, '0', '9');
		
		return new StateMachine(TokenType.NUM, s1);
	}
	
	public StateMachine buildWhiteSpace() {
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
		
		return new StateMachine(TokenType.WS, s1, true);
	}
	
	public StateMachine buildID() {
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, 'a', 'z');
		s1.addTransition(s2, 'A', 'Z');
		
		s2.addTransition(s2, 'a', 'z');
		s2.addTransition(s2, 'A', 'Z');
		s2.addTransition(s2, '0', '9');
		
		return new StateMachine(TokenType.ID, s1);
	}
	
	public StateMachine buildLeftBracket() {
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, '(');
		
		return new StateMachine(TokenType.LEFTBRACKET, s1);
	}
	
	public StateMachine buildRightBracket() {
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, ')');
		
		return new StateMachine(TokenType.RIGHTBRACKET, s1);
	}
	
	public StateMachine buildPlus() {
		State s1 = new State("1", false);
		State s2 = new State("2", true);
		
		s1.addTransition(s2, '+');
		
		return new StateMachine(TokenType.PLUS, s1);
	}
	
	public StateMachine buildIf() {
		State s1 = new State("1", false);
		State s2 = new State("2", false);
		State s3 = new State("3", true);
		
		s1.addTransition(s2, 'i');
		s2.addTransition(s3, 'f');
		
		return new StateMachine(TokenType.IF, s1);
	}
	
	public StateMachine buildThen() {
		State s1 = new State("1", false);
		State s2 = new State("2", false);
		State s3 = new State("3", false);
		State s4 = new State("4", false);
		State s5 = new State("5", true);
		
		s1.addTransition(s2, 't');
		s2.addTransition(s3, 'h');
		s3.addTransition(s4, 'e');
		s4.addTransition(s5, 'n');
		
		return new StateMachine(TokenType.THEN, s1);
	}
	
	public StateMachine buildElse() {
		State s1 = new State("1", false);
		State s2 = new State("2", false);
		State s3 = new State("3", false);
		State s4 = new State("4", false);
		State s5 = new State("5", true);
		
		s1.addTransition(s2, 'e');
		s2.addTransition(s3, 'l');
		s3.addTransition(s4, 's');
		s4.addTransition(s5, 'e');
		
		return new StateMachine(TokenType.ELSE, s1);
	}

	public List<Token> tokenize(InputStream input) throws IOException {
		
		List<Token> tokenStream = new LinkedList<Token>();
		
		StateMachine highestEndStateMachine = null;
		
		StringBuilder sb = new StringBuilder();
		
		int i = -1;
		while((i = input.read()) != -1) {
			char c = (char) i;
			
			boolean foundValidState = false;
			
			for(StateMachine s: stateMachines) {
				if(s.next(c)) {
					foundValidState = true;
					if(s.isInEndState()) {
						highestEndStateMachine = s;
					}
					
					System.out.println(s.getTokenType()+" "+s.getCurrentState()+" "+c);
				}
			}
			
			if(!foundValidState) {
				if(highestEndStateMachine == null) {
					throw new IOException("mööp");
				}
				
				input.reset();
				
				if(!highestEndStateMachine.isSkipable()) {
					tokenStream.add(new Token(highestEndStateMachine.getTokenType(), sb.toString()));
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
				input.mark(1);
			}
		}
		
		if(highestEndStateMachine != null) {
			if(!highestEndStateMachine.isSkipable()) {
				tokenStream.add(new Token(highestEndStateMachine.getTokenType(), sb.toString()));
			}
		}
		
		tokenStream.add(new Token(TokenType.EOF, "EOF"));
		
		return tokenStream;
	}
}
