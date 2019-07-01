package ast.declarations;

import ast.types.TypeNode;
import ast.types.VarTypeNode;
import llvm.CodeSnippet_Base;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.TypeWrapper;

public class ParamDeclNode extends VarDeclNode {
    public ParamDeclNode(String InName, TypeNode InType) {
        super(InName, InType);
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return m_Type.CreateSnippet(slave, ctx);
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_ScopeContainer == null) {
            // Add parameter to function header
            TypeWrapper paramType = m_Type.GetWrappedType();
            ParamContainer paramContainer = slave.CreateFunctionParameter(paramType);

            // Create local variable
            TypeWrapper wrappedType = m_Type.GetWrappedType();
            m_ScopeContainer = slave.AllocateMemory(wrappedType);

            // Write parameter value into local variable
            slave.StoreInVariable(m_ScopeContainer, paramContainer);
        }

        //dereference if passed by reference
        if(GetRawType() instanceof VarTypeNode) {
             return slave.LoadFromVariable(m_ScopeContainer);
        }

        return m_ScopeContainer;
    }
}
