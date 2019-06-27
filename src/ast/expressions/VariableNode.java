package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.VarDeclNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.TypeContainer;

public class VariableNode extends AbstractSyntaxTree {
    private String m_Name;
    private VarDeclNode m_Declaration;

    public VariableNode(String InName) {
        this.m_Name = InName;
    }

    public VarDeclNode GetDeclaration() {
        return m_Declaration;
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

        // still no declaration?
        if (m_Declaration == null) {
            throw new TypeCheckException(this, "Can't find variable declaration for " + m_Name);
        }

        return m_Declaration.GetType();
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        // TODO: What if variable is not used, but assigned?
        int scopeIndex = m_Declaration.GetScopeIndex();
        if (scopeIndex >= 0) {
            return new CodeSnippet_Plain("%" + scopeIndex);
        } else {
            // TODO: handle non primitive default values;
            PrimitiveTypeNode varType = (PrimitiveTypeNode) m_Declaration.GetType();
            return new CodeSnippet_Plain(varType.GetTypeDefault());
        }
    }

    @Override
    public TypeContainer CreateSnippet(GeneratorSlave slave) {
        return m_Declaration.CreateSnippet(slave);
    }
}
