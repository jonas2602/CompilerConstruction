package test.visitors;

import gen.PascalBaseVisitor;
import gen.PascalParser;
import test.syntaxtree.Node;

public class AntlrBridge extends PascalBaseVisitor<Node> {

    @Override
    public Node visitProgram(PascalParser.ProgramContext ctx) {
        PascalVisitor visitor = new PascalVisitor();
        return visitor.visitProgram(ctx);
    }
}
