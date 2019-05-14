package FirstGrammar;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import FirstGrammar.lexer.Lexer;
import FirstGrammar.lexer.Token;
import FirstGrammar.parser.Parser;
import FirstGrammar.parser.tree.RuleNode;
import FirstGrammar.parser.visitors.PrettyPrintVisitor;

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
		
		List<String> result =new PrettyPrintVisitor().visit(root);
		for(String line : result) {
			System.out.println(line);
		}
	}
}
