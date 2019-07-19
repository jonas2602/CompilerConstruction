package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import ast.types.VarTypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

public class ParamDeclNode extends VarDeclNode {

    public ParamDeclNode(String name, TypeNode type) {
        super(name, type);
    }

    public ParamDeclNode(String name, TypeNode type, boolean bReference) {
        super(name, type);

        if (bReference) {
            SetByReference();
        }
    }

    public void SetByReference() {
        // Already by reference?
        if (IsByReference()) return;

        // Wrap type with pointer
        m_TypeNode = new VarTypeNode(m_TypeNode);
        m_TypeNode.SetParent(this);
    }

    public boolean IsByReference() {
        return m_TypeNode instanceof VarTypeNode;
    }

    public boolean IsByValue() {
        return !IsByReference();
    }

    public void SetInlineContainer(ParamContainer container) {
        m_ScopeContainer = container;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return m_TypeNode.CreateSnippet(slave, ctx);
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_ScopeContainer == null) {
            // Add parameter to function header
            TypeWrapper paramType = m_TypeNode.GetWrappedType();
            ParamContainer paramContainer = slave.CreateFunctionParameter(paramType);

            // Parameter is part of a Function Definition or Declaration?
            if (!slave.IsDefinitionInScope()) {
                // ... In Declarations no more work is required
                return null;
            }

            // Create local variable
            TypeWrapper wrappedType = m_TypeNode.GetWrappedType();
            m_ScopeContainer = slave.AllocateMemory(wrappedType);

            // Write parameter value into local variable
            slave.StoreInVariable(m_ScopeContainer, paramContainer);

            // dereference if passed by value
            if (m_TypeNode instanceof VarTypeNode) {
                // TODO: Validate that a single load works ins all situations
                m_ScopeContainer = slave.LoadFromVariable(m_ScopeContainer);
            }
        }

        return m_ScopeContainer;
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new ParamDeclNode(m_Name, (TypeNode) m_TypeNode.Copy());
    }
}
