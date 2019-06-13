package test.visitors;

import gen.PascalParser;

import java.util.LinkedList;
import java.util.List;

public class PascalVisitor {

    public static int visitUnsignedInt(PascalParser.UnsignedIntegerContext ctx) {
        return Integer.parseInt(ctx.getText());
    }

    public static List<String> visitIdentifierList(PascalParser.IdentifierListContext ctx) {
        List<String> identifiers = new LinkedList<String>();
        List<PascalParser.IdentifierContext> list = ctx.identifier();

        for(PascalParser.IdentifierContext id: list) {
            identifiers.add(id.getText());
        }

        return identifiers;
    }
}
