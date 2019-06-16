package test.syntaxtree.declarations;

import gen.PascalParser;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.statements.LabelDefinitionNode;
import test.visitors.PascalVisitor;

public class LabelDeclarationNode extends Node {

    private String value;
    private LabelDefinitionNode definitionNode;

    public LabelDeclarationNode(ScopeNode parent) {
        super(parent);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public LabelDefinitionNode getDefinitionNode() {
        return definitionNode;
    }

    public void setDefinitionNode(LabelDefinitionNode definitionNode) {
        this.definitionNode = definitionNode;
    }

    public void buildAST(PascalParser.LabelContext ctx) {
        value = ctx.unsignedInteger().getText();
    }

    @Override
    public String toString() {
        return "LABELDEC "+value;
    }

    @Override
    public void print(int level) {
        println(level, this);
    }
}
