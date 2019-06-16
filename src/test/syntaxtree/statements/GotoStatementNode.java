package test.syntaxtree.statements;

import gen.PascalParser.GotoStatementContext;

import test.syntaxtree.ASTBuilder;
import test.syntaxtree.declarations.LabelDeclarationNode;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.visitors.PascalVisitor;

public class GotoStatementNode extends Node implements ASTBuilder<GotoStatementContext> {

    private String value;
    private LabelDefinitionNode target;

    public GotoStatementNode(ScopeNode parent) {
        super(parent);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void buildAST(GotoStatementContext ctx) {
        value = ctx.label().unsignedInteger().getText();
        LabelDeclarationNode label = parentBlock.searchLabelDeclarationGoto(value);
        if(label == null) {
            throw new RuntimeException("Unable to find declaration for label "+value+"!");
        }
        target = label.getDefinitionNode();
    }

    @Override
    public String toString() {
        return "GOTO "+value;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
    }
}
