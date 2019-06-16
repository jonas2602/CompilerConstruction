package test.syntaxtree.statements.variables;

import gen.PascalParser.VariableAccessContext;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;

public abstract class VariableAccessNode extends Node {

    public VariableAccessNode(ScopeNode parent) {
        super(parent);
    }

    public static VariableAccessNode buildAST(VariableAccessContext ctx, ScopeNode parent) {
        if(ctx.fieldDesignator() != null) {
            FieldAccessNode field = new FieldAccessNode(parent);
            field.buildAST(ctx.fieldDesignator());
            return field;
        }
        else if(ctx.indexedVariable() != null) {
            ArrayAccessNode array = new ArrayAccessNode(parent);
            array.buildAST(ctx.indexedVariable());
            return array;
        }
        else {
            return new PointerAccessNode(parent);
        }
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
