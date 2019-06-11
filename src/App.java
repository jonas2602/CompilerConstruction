import gen.GramLexer;
import gen.GramParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("START");
        GramLexer lexer = new GramLexer(CharStreams.fromFileName("examples/helloworld.pas"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GramParser parser = new GramParser(tokens);
        ParseTree tree = parser.startRule();

        //Visitor for old grammar won't work anymore
        //new Interpreter().ProcessExpression("3 + 4.5");
    }
}
