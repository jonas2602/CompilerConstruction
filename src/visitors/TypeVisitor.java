package visitors;

import ast.AbstractSyntaxTree;
import ast.types.*;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;
import java.util.List;

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
    public TypeNode visitScalarType(PascalParser.ScalarTypeContext ctx) {
        List<String> names = new ArrayList<>();
        for (PascalParser.IdentifierContext ident : ctx.identifierList().identifier()) {
            names.add(ident.IDENT().getText());
        }

        EnumTypeNode enumNode = new EnumTypeNode(names);
        return enumNode;
    }

    @Override
    public TypeNode visitSubrangeType(PascalParser.SubrangeTypeContext ctx) {
        AbstractSyntaxTree minNode = new ExpressionVisitor().visitConstant(ctx.constant(0));
        AbstractSyntaxTree maxNode = new ExpressionVisitor().visitConstant(ctx.constant(1));
        RangeTypeNode rangeNode = new RangeTypeNode(minNode, maxNode);

        return rangeNode;
    }

    @Override
    public TypeNode visitStringtype(PascalParser.StringtypeContext ctx) {
        // TODO:
        return null;
    }

    @Override
    public TypeNode visitStructuredType(PascalParser.StructuredTypeContext ctx) {
        // TODO: packed keyword
        return visitUnpackedStructuredType(ctx.unpackedStructuredType());
    }

    @Override
    public TypeNode visitUnpackedStructuredType(PascalParser.UnpackedStructuredTypeContext ctx) {
        if (ctx.arrayType() != null) {
            return visitArrayType(ctx.arrayType());
        } else if (ctx.recordType() != null) {
            return visitRecordType(ctx.recordType());
        } else if (ctx.setType() != null) {
            return visitSetType(ctx.setType());
        } else {
            return visitFileType(ctx.fileType());
        }
    }

    @Override
    public TypeNode visitArrayType(PascalParser.ArrayTypeContext ctx) {
        TypeNode innerType = visitType(ctx.type());

        // build encapsuled arrays from inside out
        for (int i = ctx.typeList().simpleType().size() - 1; i >= 0; i--) {
            AbstractSyntaxTree counter = visitSimpleType(ctx.typeList().simpleType(i));
            innerType = new ArrayTypeNode(counter, innerType);
        }

        return innerType;
    }

    @Override
    public TypeNode visitRecordType(PascalParser.RecordTypeContext ctx) {
        // TODO:
        return null;
    }

    @Override
    public TypeNode visitSetType(PascalParser.SetTypeContext ctx) {
        TypeNode setType = visitSimpleType(ctx.simpleType());
        return new SetTypeNode(setType);
    }

    @Override
    public TypeNode visitFileType(PascalParser.FileTypeContext ctx) {
        // TODO:
        return null;
    }

    @Override
    public TypeNode visitPointerType(PascalParser.PointerTypeContext ctx) {
        // TODO:
        return null;
    }

    @Override
    public TypeNode visitTypeIdentifier(PascalParser.TypeIdentifierContext ctx) {
        if (ctx.CHAR() != null) return NamedTypeNode.CharNode;
        if (ctx.BOOLEAN() != null) return NamedTypeNode.BoolNode;
        if (ctx.INTEGER() != null) return PrimitiveTypeNode.IntNode;
        if (ctx.REAL() != null) return PrimitiveTypeNode.FloatNode;
        if (ctx.STRING() != null) return NamedTypeNode.StringNode;

        return new NamedTypeNode(ctx.identifier().IDENT().getText());
    }
}
