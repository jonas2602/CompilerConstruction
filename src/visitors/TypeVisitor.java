package visitors;

import ast.AbstractSyntaxTree;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;
import gen.PascalBaseVisitor;
import gen.PascalParser;

public class TypeVisitor extends PascalBaseVisitor<TypeNode> {
    @Override
    public TypeNode visitType(PascalParser.TypeContext ctx) {
        if (ctx.simpleType() != null) {
            return visitSimpleType(ctx.simpleType());
        } else if (ctx.structuredType() != null) {
            return visitStructuredType(ctx.structuredType());
        } else {
            return visitPointerType(ctx.pointerType());
        }
    }

    @Override
    public TypeNode visitSimpleType(PascalParser.SimpleTypeContext ctx) {
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
    public TypeNode visitStructuredType(PascalParser.StructuredTypeContext ctx) {
        return super.visitStructuredType(ctx);
    }

    @Override
    public TypeNode visitPointerType(PascalParser.PointerTypeContext ctx) {
        return super.visitPointerType(ctx);
    }

    @Override
    public TypeNode visitTypeIdentifier(PascalParser.TypeIdentifierContext ctx) {
        if(ctx.CHAR() != null) return NamedTypeNode.CharNode();
        if(ctx.BOOLEAN() != null) return NamedTypeNode.BoolNode();
        if(ctx.INTEGER() != null) return NamedTypeNode.IntNode();
        if(ctx.REAL() != null) return NamedTypeNode.RealNode();
        if(ctx.STRING() != null) return NamedTypeNode.StringNode();

        return new NamedTypeNode(ctx.identifier().IDENT().getText());
    }
}
