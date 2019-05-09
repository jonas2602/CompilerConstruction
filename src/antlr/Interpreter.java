package antlr;

import gen.GramLexer;
import gen.GramParser;
import gen.GramParser.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class Interpreter {
    public void ProcessExpression(String source){
        GramLexer lexer = new GramLexer(CharStreams.fromString(source));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        GramParser parser = new GramParser(tokens);
        ExpressionContext context = parser.expression();

        Float result = new ExpressionVisitor().visit(context);
        System.out.println("Result: " + result);
    }
}
