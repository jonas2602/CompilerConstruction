package test.visitors;

import gen.PascalBaseVisitor;
import gen.PascalParser;
import test.syntaxtree.Node;
import test.syntaxtree.ProgramNode;

public class AntlrBridge extends PascalBaseVisitor<Node> {

    @Override
    public Node visitProgram(PascalParser.ProgramContext ctx) {
        PascalVisitor visitor = new PascalVisitor();
        ProgramNode program = new ProgramNode();
        program.buildAST(ctx);
        return program;
    }
}
