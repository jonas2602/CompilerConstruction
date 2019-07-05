package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.VarDeclNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

public class AccessNode_Variable extends AbstractSyntaxTree implements AccessInterface {
    private String m_Name;
    private VarDeclNode m_Declaration;

    public AccessNode_Variable(String name) {
        this.m_Name = name;
    }

    public VarDeclNode GetDeclaration() {
        return m_Declaration;
    }

    public void SetDeclaration(VarDeclNode var) {
        m_Declaration = var;
    }

    @Override
    public TypeNode CheckType() {
        // Variable Declaration should already be checked by the owning Block
        // -> only return the Declaration Type
        return GetType();
    }

    @Override
    public TypeNode GetType() {
        // Try find declaration for variable in contained block
        if (m_Declaration == null) {
            m_Declaration = GetOwningBlock().GetVariableDeclaration(m_Name);
        }
        // TODO: Combine in Block to check constants, variables and enums with a single call
        if (m_Declaration == null) {
            m_Declaration = GetOwningBlock().GetConstantDeclaration(m_Name);
        }

        // still no declaration?
        if (m_Declaration == null) {
            throw new TypeCheckException(this, "Can't find variable declaration for " + m_Name);
        }

        return m_Declaration.GetType();
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return m_Declaration.CreateSnippet(slave);
    }

    @Override
    public VarDeclNode GetVarDeclNode() {
        return m_Declaration;
    }

    @Override
    public ParamContainer LoadValue(GeneratorSlave slave, ParamContainer pointer) {
        return slave.LoadFromVariable(pointer);
    }
}
