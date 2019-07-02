package ast;

import ast.AbstractSyntaxTree;

public class TypeCheckException extends RuntimeException {
    public TypeCheckException(AbstractSyntaxTree node, String message){
        super("Type checking failed at " + node.getClass().getName() + ": " + message);
    }
}
