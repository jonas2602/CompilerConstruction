package visitors;

import ast.*;
import ast.expression.ArrayAccessNode;
import ast.expression.FieldAccessNode;
import ast.expression.PointerAccessNode;
import ast.expression.VariableNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;

public class VariableAccessVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    private AbstractSyntaxTree m_Root;

    @Override
    public AbstractSyntaxTree visitVariable(PascalParser.VariableContext ctx) {
        m_Root = new VariableNode(ctx.identifier().IDENT().getText());

        for (PascalParser.VariableAccessContext child : ctx.variableAccess()) {
            m_Root = visit(child);
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
        ArrayAccessNode accessNode = new ArrayAccessNode(m_Root);
        for (PascalParser.ExpressionContext exp : ctx.expression()) {
            accessNode.AddIndexExpression(new ExpressionVisitor().visit(exp));
        }

        return accessNode;
    }

    @Override
    public AbstractSyntaxTree visitFieldDesignator(PascalParser.FieldDesignatorContext ctx) {
        return new FieldAccessNode(m_Root, ctx.identifier().IDENT().getText());
    }

    @Override
    public AbstractSyntaxTree visitPointerVariable(PascalParser.PointerVariableContext ctx) {
        return new PointerAccessNode(m_Root);
    }
}
