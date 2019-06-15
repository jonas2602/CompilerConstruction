package test.syntaxtree;

import org.antlr.v4.runtime.ParserRuleContext;

public interface ASTBuilder <T extends ParserRuleContext> {
    public void buildAST(T ctx);
}
