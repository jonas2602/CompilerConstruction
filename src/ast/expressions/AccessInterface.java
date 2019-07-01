package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.declarations.ParamDeclNode;
import ast.declarations.VarDeclNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public interface AccessInterface {
    public abstract VarDeclNode GetVarDeclNode();

    public abstract ParamContainer LoadValue(GeneratorSlave InSlave, ParamContainer InPointer);

    // TODO: find more suitable solution!!
    //  Maybe add a second parameter to CreateSnippet that requests "by value" or "by reference"
    public static ParamContainer TryLoadValue(GeneratorSlave InSlave, AbstractSyntaxTree InNode, ParamContainer InPointer) {
        if (InNode instanceof AccessInterface) {
            return ((AccessInterface) InNode).LoadValue(InSlave, InPointer);
        }

        return InPointer;
    }
}
