package test.syntaxtree.types;

import gen.PascalParser;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;

public class TypeIdentifierNode extends Node {

    protected String type;

    public TypeIdentifierNode(ScopeNode parent) {
        super(parent);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static TypeIdentifierNode buildAST(PascalParser.TypeIdentifierContext ctx, ScopeNode parent) {
        if(ctx.BOOLEAN() != null) {
            return new BooleanTypeIdentifierNode(parent);
        }
        else if(ctx.CHAR() != null) {
            return new CharTypeIdentifierNode(parent);
        }
        else if(ctx.INTEGER() != null) {
            return new IntegerTypeIdentifierNode(parent);
        }
        else if(ctx.REAL() != null) {
            return new RealTypeIdentifierNode(parent);
        }
        else if(ctx.STRING() != null) {
            return new StringTypeIdentifierNode(parent);
        }
        else {
            TypeIdentifierNode cust =  new TypeIdentifierNode(parent);
            cust.setType(ctx.identifier().getText());
            return cust;
        }
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public void print(int level) {
        print(level, this);
    }
}
