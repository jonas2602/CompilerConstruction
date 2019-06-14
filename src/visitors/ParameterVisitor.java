package visitors;

import ast.AbstractSyntaxTree;
import ast.declaration.ParamDeclNode;
import ast.expression.ParameterNode;
import ast.types.TypeNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;
import java.util.List;

public class ParameterVisitor extends PascalBaseVisitor<List<AbstractSyntaxTree>> {
    @Override
    public List<AbstractSyntaxTree> visitFormalParameterList(PascalParser.FormalParameterListContext ctx) {
        List<AbstractSyntaxTree> outParams = new ArrayList<>();

        for (PascalParser.FormalParameterSectionContext section : ctx.formalParameterSection()) {
            outParams.addAll(visitFormalParameterSection(section));
        }

        return outParams;
    }

    @Override
    public List<AbstractSyntaxTree> visitFormalParameterSection(PascalParser.FormalParameterSectionContext ctx) {
        return visitParameterGroup(ctx.parameterGroup());
        // TODO: VAR, FUNCTION, PROCEDURE Keywords
    }

    @Override
    public List<AbstractSyntaxTree> visitParameterGroup(PascalParser.ParameterGroupContext ctx) {
        List<AbstractSyntaxTree> outParams = new ArrayList<>();

        TypeNode type = new TypeVisitor().visit(ctx.typeIdentifier());
        for (PascalParser.IdentifierContext ident : ctx.identifierList().identifier()) {
            outParams.add(new ParamDeclNode(ident.IDENT().getText(), type));
        }

        return outParams;
    }

    @Override
    public List<AbstractSyntaxTree> visitParameterList(PascalParser.ParameterListContext ctx) {
        List<AbstractSyntaxTree> outParams = new ArrayList<>();

        for (PascalParser.ActualParameterContext param : ctx.actualParameter()) {
            AbstractSyntaxTree exp = new ExpressionVisitor().visit(param.expression());
            outParams.add(new ParameterNode(exp));
        }

        return outParams;
    }
}
