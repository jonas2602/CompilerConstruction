package ast.core.functions.math;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction;
import writer.natives.math.NativeMathFunction;
import writer.wrappers.ParamContainer;

import java.lang.reflect.InvocationTargetException;

public abstract class MathFunction extends FuncDeclNode_Core {

    protected boolean m_Cast;
    private Class<? extends NativeMathFunction> m_Function;

    public MathFunction(String name, PrimitiveTypeNode returnType, PrimitiveTypeNode param, Class<? extends NativeMathFunction> function) {
        super(name, returnType);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("param", param));

        if(!param.CompareType(PrimitiveTypeNode.FloatNode)) {
            m_Cast = true;
        }

        m_Function = function;
    }

    public MathFunction(Math functionName, PrimitiveTypeNode returnType, PrimitiveTypeNode param, Class<? extends NativeMathFunction> function) {
        this(functionName.GetFunctionName(), returnType, param, function);
    }

    public MathFunction(String name, PrimitiveTypeNode param, Class<? extends NativeMathFunction> function) {
        this(name, PrimitiveTypeNode.FloatNode, param, function);
    }

    public MathFunction(Math functionName, PrimitiveTypeNode param, Class<? extends NativeMathFunction> function) {
        this(functionName.GetFunctionName(), PrimitiveTypeNode.FloatNode, param, function);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree param = callNode.GetParameter(0);
        ParamContainer container = param.CreateSnippet(slave);

        container = AccessInterface.TryLoadValue(slave, param, container);

        //only int casting supported atm
        if(m_Cast) {
            container = slave.CastIntToFloat(container);
        }

        NativeFunction func = null;

        try {
            func = m_Function.getDeclaredConstructor(ParamContainer.class).newInstance(container);
        }
        catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ParamContainer result = slave.CreateNativeCall(func);

        return result;
    }
}
