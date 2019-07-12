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

import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("START");

        List<String> sourceFiles = List.of("arrays", "branch", "constant", "dynamics", "enum", "goto", "innerfunctions", "innerfunctions2", "io", "loops", "memory", "pointer", "string", "switch", "types");

        // for (String fileName : sourceFiles) {
        //     try {
        //         Compile(String.format("res/examples/tests/%s.pas", fileName), "gen");
        //     } catch (Exception ex) {
        //         System.out.println("Compiling failed at " + fileName);
        //         ex.printStackTrace();
        //     }
        //
        //     System.out.println(fileName + " succeeded");
        // }

        Compile("res/examples/tests/loops.pas", "gen");

        LocalTester t = new LocalTester();
        t.compile("gen");

        // TestBattery battery = new TestBattery();
        // battery.Fire();
    }

    public static void Compile(String sourcePath, String targetFileName) throws IOException {
        // create block with predefined functions of pascal
        BlockNode stdBlock = StdLib.CreateStdLib();

        // TODO: Add primitive types to std block
        // TODO: Remove all NamedTypeNodes and TypeDeclNodes while typechecking with actual type

        // Tokenize input file
        PascalLexer lexer = new PascalLexer(CharStreams.fromFileName(sourcePath));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Build parse tree
        PascalParser parser = new PascalParser(tokens);
        ParseTree tree = parser.program();

        if (parser.getNumberOfSyntaxErrors() != 0) {
            System.exit(0);
        }

        // convert parse tree to abstract syntax tree
        ProgramVisitor vis = new ProgramVisitor();
        ProgramNode prog = vis.visit(tree);

        // attach parsed tree to the stdBlock to grant access to the additional functionality
        prog.SetParent(stdBlock);

        // add dynamic types
        // Set<TypeNode> dynamicTypes = TypeVisitor.m_DynamicTypes;
        // for (TypeNode baseType : dynamicTypes) {
        //     prog.GetBlock().AddTypeDeclaration(new TypeDeclNode(ArrayTypeNode_Dynamic.CreateDynamicArrayName(baseType), new ArrayTypeNode_Dynamic(baseType)));
        // }

        // type checking
        try {
            stdBlock.CheckType();
            prog.CheckType();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.exit(0);
        }

        CodeGenerator.CreateIntermediate(prog, stdBlock, targetFileName);
    }
}
