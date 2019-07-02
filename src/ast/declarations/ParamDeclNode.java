package ast.declarations;

import ast.types.TypeNode;
import ast.types.VarTypeNode;
import llvm.CodeSnippet_Base;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.TypeWrapper;

public class ParamDeclNode extends VarDeclNode {

    public ParamDeclNode(String name, TypeNode type) {
        super(name, type);
    }

    public void SetByReference() {
        // Already by reference?
        if (IsByReference()) return;

        // Wrap type with pointer
        m_Type = new VarTypeNode(m_Type);
    }

    public boolean IsByReference() {
        return m_Type instanceof VarTypeNode;
    }

    public boolean IsByValue() {
        return !IsByReference();
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

            // Parameter is part of a Function Definition or Declaration?
            if (!slave.IsDefinitionInScope()) {
                // ... In Declarations no more work is required
                return null;
            }

            // Create local variable
            TypeWrapper wrappedType = m_Type.GetWrappedType();
            m_ScopeContainer = slave.AllocateMemory(wrappedType);

            // Write parameter value into local variable
            slave.StoreInVariable(m_ScopeContainer, paramContainer);

            // dereference if passed by reference
            if (GetRawType() instanceof VarTypeNode) {
                // TODO: Validate that a single load works ins all situations
                m_ScopeContainer = slave.LoadFromVariable(m_ScopeContainer);
            }
        }

        return m_ScopeContainer;
    }
}
