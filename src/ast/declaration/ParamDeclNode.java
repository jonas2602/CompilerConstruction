package ast.declaration;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class ParamDeclNode extends VarDeclNode {
    public ParamDeclNode(String InName, TypeNode InType) {
        super(InName, InType);
    }
}
