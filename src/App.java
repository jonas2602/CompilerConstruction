import gen.PascalLexer;
import gen.PascalParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import test.syntaxtree.Node;
import test.visitors.AntlrBridge;
import test.visitors.PascalVisitor;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("START");
        PascalLexer lexer = new PascalLexer(CharStreams.fromFileName("res/examples/test.pas"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PascalParser parser = new PascalParser(tokens);
        ParseTree tree = parser.program();

        //ExpressionVisitor vis = new ExpressionVisitor();
        //vis.visit(tree);

        AntlrBridge brigde = new AntlrBridge();
        Node abs = brigde.visit(tree);

        abs.print();

        //Visitor for old grammar won't work anymore
        //new Interpreter().ProcessExpression("3 + 4.5");
    }
}
