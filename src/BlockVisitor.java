import gen.PascalBaseVisitor;
import gen.PascalParser;

public class BlockVisitor extends PascalBaseVisitor<Integer> {
    @Override
    public Integer visitBlock(PascalParser.BlockContext ctx) {
        return 0;
    }
}
