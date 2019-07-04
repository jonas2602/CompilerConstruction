package visitors;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.declarations.ConstDeclNode;
import ast.expressions.ConstantNode;
import ast.types.*;
import gen.PascalBaseVisitor;
import gen.PascalParser;

import java.util.ArrayList;
import java.util.List;

public class TypeVisitor extends PascalBaseVisitor<TypeNode> {

    private BlockNode m_OwningBlock;

    public TypeVisitor(BlockNode owningBlock) {
        m_OwningBlock = owningBlock;
    }

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
        int i = 0;
        for (PascalParser.IdentifierContext ident : ctx.identifierList().identifier()) {
            ConstDeclNode constDecl = new ConstDeclNode(ident.getText(), new ConstantNode(""+i, PrimitiveTypeNode.ConstIntNode));
            m_OwningBlock.AddConstantDeclaration(constDecl);
            i++;
        }

        return PrimitiveTypeNode.IntNode;
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
        if (ctx.identifier() != null) {
            System.err.println("Stringtype only supports numbers right now");
        }

        int size = Integer.parseInt(ctx.NUM_INT().getText());
        return new ArrayTypeNode(size, PrimitiveTypeNode.CharNode);
    }

    @Override
    public TypeNode visitStructuredType(PascalParser.StructuredTypeContext ctx) {
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
        TypeNode innerType = visitTypeIdentifier(ctx.typeIdentifier());
        return new PointerTypeNode(innerType);
    }

    @Override
    public TypeNode visitTypeIdentifier(PascalParser.TypeIdentifierContext ctx) {
        if (ctx.CHAR() != null) return PrimitiveTypeNode.CharNode;
        if (ctx.BOOLEAN() != null) return PrimitiveTypeNode.BoolNode;
        if (ctx.INTEGER() != null) return PrimitiveTypeNode.IntNode;
        if (ctx.REAL() != null) return PrimitiveTypeNode.FloatNode;
        if (ctx.STRING() != null) return NamedTypeNode.StringNode;

        return new NamedTypeNode(ctx.identifier().IDENT().getText());
    }
}
