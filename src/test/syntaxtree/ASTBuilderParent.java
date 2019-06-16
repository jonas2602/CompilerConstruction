package test.syntaxtree;

import org.antlr.v4.runtime.RuleContext;
import test.syntaxtree.scopes.ScopeNode;

public interface ASTBuilderParent <T extends RuleContext> {
    public void buildAST(T ctx, ScopeNode parent);
}
