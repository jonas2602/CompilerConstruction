import ast.AbstractSyntaxTree;
import gen.PascalLexer;
import gen.PascalParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.ProgramVisitor;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("START");
        PascalLexer lexer = new PascalLexer(CharStreams.fromFileName("res/examples/factorial.pas"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PascalParser parser = new PascalParser(tokens);
        ParseTree tree = parser.program();

        ProgramVisitor vis = new ProgramVisitor();
        AbstractSyntaxTree root = vis.visit(tree);

        // type checking
        try{
            root.CheckType();
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }

        //Visitor for old grammar won't work anymore
        //new Interpreter().ProcessExpression("3 + 4.5");
    }
}
