package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.declarations.ParamDeclNode;
import ast.declarations.VarDeclNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public interface AccessInterface {
    public abstract VarDeclNode GetVarDeclNode();

    public abstract ParamContainer LoadValue(GeneratorSlave slave, ParamContainer pointer);

    // TODO: find more suitable solution!!
    //  Maybe add a second parameter to CreateSnippet that requests "by value" or "by reference"
    public static ParamContainer TryLoadValue(GeneratorSlave slave, AbstractSyntaxTree node, ParamContainer pointer) {
        if (node instanceof AccessInterface) {
            return ((AccessInterface) node).LoadValue(slave, pointer);
        }

        if (node instanceof ConstantNode_String) {
            return slave.LoadFromVariable(pointer);
        }

        return pointer;
    }
}
