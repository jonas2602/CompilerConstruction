import gen.PascalBaseVisitor;
import gen.PascalParser;

public class ProgramVisitor extends PascalBaseVisitor<Integer> {
    @Override
    public Integer visitProgram(PascalParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }
}
