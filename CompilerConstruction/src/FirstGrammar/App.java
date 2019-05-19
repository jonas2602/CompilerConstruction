package FirstGrammar;

import java.io.IOException;
import java.util.List;

import FirstGrammar.lexer.Lexer;
import FirstGrammar.lexer.Token;
import FirstGrammar.parser.Parser;
import FirstGrammar.parser.tree.Node;
import FirstGrammar.parser.visitors.PrettyPrintVisitor;
import FirstGrammar.utils.InputStreamUtils;

public class App {
	public static void main(String[] args) throws IOException {
		String test = "if test then test + test2 else if test123 then (a + (b + c)) else moop";
		
		//LEXER
		Lexer l = new Lexer();
		List<Token> tokenStream = l.tokenize(InputStreamUtils.streamFromString(test));
		
		for(Token t: tokenStream) {
			System.out.println(t);
		}
		
		//PARSER
		Parser p = new Parser();
		Node root = p.parse(tokenStream);
		
		List<String> result = new PrettyPrintVisitor().visit(root);
		for(String line : result) {
			System.out.println(line);
		}
	}
}
