package ast.core.functions.io;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.natives.io.NativeFunction_printf;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Primitive;
import writer.wrappers.ValueWrapper_Constant;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode_writeln extends FuncDeclNode_write {
    public FuncDeclNode_writeln() {
        super(IO.WRITELN, "\n");
    }

    @Override
    protected FuncDeclNode_write Instantiate() {
        return new FuncDeclNode_writeln();
    }
}
