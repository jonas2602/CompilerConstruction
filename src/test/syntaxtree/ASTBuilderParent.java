package test.syntaxtree;

import org.antlr.v4.runtime.RuleContext;

public interface ASTBuilderParent <T extends RuleContext> {
    public void buildAST(T ctx, BlockNode parent);
}
