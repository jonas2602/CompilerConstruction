package ast;

import ast.AbstractSyntaxTree;

public class TypeCheckException extends RuntimeException {
    public TypeCheckException(AbstractSyntaxTree Node, String message){
        super("Type checking failed at " + Node.getClass().getName() + ": " + message);
    }
}
