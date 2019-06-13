package visitors;

import ast.AbstractSyntaxTree;
import ast.types.NamedTypeNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;

public class TypeVisitor extends PascalBaseVisitor<AbstractSyntaxTree> {
    @Override
    public AbstractSyntaxTree visitType(PascalParser.TypeContext ctx) {
        if (ctx.simpleType() != null) {
            return visitSimpleType(ctx.simpleType());
        } else if (ctx.structuredType() != null) {
            return visitStructuredType(ctx.structuredType());
        } else {
            return visitPointerType(ctx.pointerType());
        }
    }

    @Override
    public AbstractSyntaxTree visitSimpleType(PascalParser.SimpleTypeContext ctx) {
        if (ctx.scalarType() != null) {
            return visitScalarType(ctx.scalarType());
        } else if (ctx.subrangeType() != null) {
            return visitSubrangeType(ctx.subrangeType());
        } else if (ctx.typeIdentifier() != null) {
            return visitTypeIdentifier(ctx.typeIdentifier());
        } else {
            return visitStringtype(ctx.stringtype());
        }
    }

    @Override
    public AbstractSyntaxTree visitStructuredType(PascalParser.StructuredTypeContext ctx) {
        return super.visitStructuredType(ctx);
    }

    @Override
    public AbstractSyntaxTree visitPointerType(PascalParser.PointerTypeContext ctx) {
        return super.visitPointerType(ctx);
    }

    @Override
    public AbstractSyntaxTree visitTypeIdentifier(PascalParser.TypeIdentifierContext ctx) {
        // TODO: How to handle predefinec Types? ToLower?
        if(ctx.CHAR() != null) return new NamedTypeNode("char");
        if(ctx.BOOLEAN() != null) return new NamedTypeNode("bool");
        if(ctx.INTEGER() != null) return new NamedTypeNode("int");
        if(ctx.REAL() != null) return new NamedTypeNode("real");
        if(ctx.STRING() != null) return new NamedTypeNode("string");

        return new NamedTypeNode(ctx.identifier().IDENT().getText());
    }
}
