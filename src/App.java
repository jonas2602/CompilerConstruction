import ast.BlockNode;
import ast.ProgramNode;
import ast.core.*;
import gen.PascalLexer;
import gen.PascalParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import test.LocalTester;
import test.TestBattery;
import visitors.ProgramVisitor;
import writer.CodeGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            System.out.println("Too few arguments.");
            System.out.println("Usage: <input file> <output file>");
            return;
        }

        File input = new File(args[0]);
        if (!input.exists() || !input.isFile()) {
            System.out.println("Input file does not exist or is not an directory");
            return;
        }

        Compile(args[0], args[1]);

        System.out.println();
        System.out.println("Starting program exectuion ...");
        LocalTester t = new LocalTester();
        t.Compile(args[1]);

        // TestBattery battery = new TestBattery();
        // battery.Fire();
    }

    public static void Compile(String sourcePath, String targetFileName) throws IOException {
        // create block with predefined functions of pascal
        BlockNode stdBlock = StdLib.CreateStdLib();

        // TODO: Add primitive types to std block
        // TODO: Remove all NamedTypeNodes and TypeDeclNodes while typechecking with actual type

        System.out.println("Parsing the inputfile ...");

        // Tokenize input file
        PascalLexer lexer = new PascalLexer(CharStreams.fromFileName(sourcePath));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Build parse tree
        PascalParser parser = new PascalParser(tokens);
        ParseTree tree = parser.program();

        if (parser.getNumberOfSyntaxErrors() != 0) {
            System.out.println("Failed to parse the inputfile");
            System.exit(0);
        }

        System.out.println("Sucessfully parsed the inputfile");


        System.out.print("Building AST ...");

        // convert parse tree to abstract syntax tree
        ProgramVisitor vis = new ProgramVisitor();
        ProgramNode prog = vis.visit(tree);

        // attach parsed tree to the stdBlock to grant access to the additional functionality
        prog.SetParent(stdBlock);

        System.out.println("SUCCEEDED");

        System.out.print("Typechecking ...");
        // type checking
        try {
            stdBlock.CheckType();
            prog.CheckType();
        } catch (RuntimeException e) {
            System.out.println("FAILED");
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("SUCCEEDED");

        System.out.println("Generating code ...");
        CodeGenerator.CreateIntermediate(prog, stdBlock, targetFileName, true);
        System.out.println("Finished code generation");
    }
}
