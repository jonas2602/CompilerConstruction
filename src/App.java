import ast.BlockNode;
import ast.ProgramNode;
import ast.core.PascalType_Bool;
import ast.core.PascalType_Float;
import ast.core.PascalType_Int;
import ast.core.FuncDeclNode_writeln;
import ast.declarations.TypeDeclNode;
import ast.types.PrimitiveTypeNode;
import gen.PascalLexer;
import gen.PascalParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.ProgramVisitor;
import writer.CodeGenerator;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("START");

        // create block with predefined functions of pascal
        BlockNode stdBlock = new BlockNode();
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_writeln());

        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_addInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_addFloat());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_subInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_subFloat());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_mulInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_mulFloat());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_divInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_divFloat());

        stdBlock.AddFunctionDeclaration(new PascalType_Float.FuncDeclNode_addInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Float.FuncDeclNode_addFloat());
        stdBlock.AddFunctionDeclaration(new PascalType_Float.FuncDeclNode_subInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Float.FuncDeclNode_subFloat());
        stdBlock.AddFunctionDeclaration(new PascalType_Float.FuncDeclNode_mulInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Float.FuncDeclNode_mulFloat());
        stdBlock.AddFunctionDeclaration(new PascalType_Float.FuncDeclNode_divFloat());
        stdBlock.AddFunctionDeclaration(new PascalType_Float.FuncDeclNode_divInt());

        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_divTrucInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_modInt());

        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_eqInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_neInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_ltInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_leInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_gtInt());
        stdBlock.AddFunctionDeclaration(new PascalType_Int.FuncDeclNode_geInt());

        stdBlock.AddFunctionDeclaration(new PascalType_Bool.FuncDeclNode_orBool());
        stdBlock.AddFunctionDeclaration(new PascalType_Bool.FuncDeclNode_andBool());

        stdBlock.AddTypeDeclaration(new TypeDeclNode("integer", PrimitiveTypeNode.IntNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("real", PrimitiveTypeNode.FloatNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("char", PrimitiveTypeNode.CharNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("boolean", PrimitiveTypeNode.BoolNode));

        // TODO: Add primitive types to std block
        // TODO: Remove all NamedTypeNodes and TypeDeclNodes while typechecking with actual type

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
