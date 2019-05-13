package FirstGrammar;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class App {
	public static void main(String[] args) throws IOException {
		String test = "if test then test + test2 else if test123 then (a + (b + c)) else moop";
		
		//LEXER
		Lexer l = new Lexer();
		List<Token> tokens = l.tokenize(new ByteArrayInputStream(test.getBytes()));
		
		for(Token t: tokens) {
			System.out.println(t);
		}
		
		//PARSER
		Parser p = new Parser();
		RuleNode root = p.parse(tokens);
		
		List<String> result =new Visitor().visit(root);
		for(String line : result) {
			System.out.println(line);
		}
	}
}
