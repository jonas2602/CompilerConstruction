package test.syntaxtree.subs.parameters;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.types.typeidentifiers.TypeIdentifierNode;

public abstract class ParameterNode extends Node {

    protected String identifier;
    protected TypeIdentifierNode type;

    public ParameterNode(ScopeNode parent) {
        super(parent);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public TypeIdentifierNode getType() {
        return type;
    }

    public void setType(TypeIdentifierNode type) {
        this.type = type;
    }
}
