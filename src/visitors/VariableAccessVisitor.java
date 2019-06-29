package visitors;

import ast.*;
import ast.expressions.*;
import gen.PascalBaseVisitor;
import gen.PascalParser;

public class VariableAccessVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    private AbstractSyntaxTree m_Root;

    @Override
    public AbstractSyntaxTree visitVariable(PascalParser.VariableContext ctx) {
        m_Root = new AccessNode_Variable(ctx.identifier().IDENT().getText());

        for (PascalParser.VariableAccessContext child : ctx.variableAccess()) {
            m_Root = visit(child);
        }

        if (ctx.AT() != null) {
            m_Root = new AccessNode_Address(m_Root);
        }

        return m_Root;
    }

    @Override
    public AbstractSyntaxTree visitVariableAccess(PascalParser.VariableAccessContext ctx) {
        if (ctx.indexedVariable() != null) {
            return visitIndexedVariable(ctx.indexedVariable());
        } else if (ctx.fieldDesignator() != null) {
            return visitFieldDesignator(ctx.fieldDesignator());
        } else {
            return visitPointerVariable(ctx.pointerVariable());
        }
    }

    @Override
    public AbstractSyntaxTree visitIndexedVariable(PascalParser.IndexedVariableContext ctx) {
        AccessNode_Array accessNode = new AccessNode_Array(m_Root);
        for (PascalParser.ExpressionContext exp : ctx.expression()) {
            accessNode.AddIndexExpression(new ExpressionVisitor().visit(exp));
        }

        return accessNode;
    }

    @Override
    public AbstractSyntaxTree visitFieldDesignator(PascalParser.FieldDesignatorContext ctx) {
        return new AccessNode_Field(m_Root, ctx.identifier().IDENT().getText());
    }

    @Override
    public AbstractSyntaxTree visitPointerVariable(PascalParser.PointerVariableContext ctx) {
        return new AccessNode_Pointer(m_Root);
    }
}
