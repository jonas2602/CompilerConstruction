import ast.BlockNode;
import ast.ProgramNode;
import ast.core.Type_Int;
import ast.core.FuncDeclNode_writeln;
import gen.PascalLexer;
import gen.PascalParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.ProgramVisitor;
import writer.CodeGenerator;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("START");

        // create block with predefined functions of pascal
        BlockNode stdBlock = new BlockNode();
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_writeln());
        stdBlock.AddFunctionDeclaration(new Type_Int.FuncDeclNode_addInt());
        stdBlock.AddFunctionDeclaration(new Type_Int.FuncDeclNode_addReal());

        // Tokenize input file
        PascalLexer lexer = new PascalLexer(CharStreams.fromFileName("res/examples/test.pas"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Build parse tree
        PascalParser parser = new PascalParser(tokens);
        ParseTree tree = parser.program();

        // convert parse tree to abstract syntax tree
        ProgramVisitor vis = new ProgramVisitor();
        ProgramNode prog = vis.visit(tree);

        // attach parsed tree to the stdBlock to grant access to the additional functionality
        prog.SetParent(stdBlock);


        // type checking
        try {
            stdBlock.CheckType();
            prog.CheckType();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        CodeGenerator.CreateIntermediate(prog, "gen");

        //Visitor for old grammar won't work anymore
        //new Interpreter().ProcessExpression("3 + 4.5");
    }
}
