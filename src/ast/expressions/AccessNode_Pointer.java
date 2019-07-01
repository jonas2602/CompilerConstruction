package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.VarDeclNode;
import ast.types.PointerTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class AccessNode_Pointer extends AbstractSyntaxTree implements AccessInterface {
    private AbstractSyntaxTree m_Child;

    public AccessNode_Pointer(AbstractSyntaxTree InChild) {
        this.m_Child = InChild;
        m_Child.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode childType = m_Child.CheckType();
        if (!(childType instanceof PointerTypeNode)) {
            throw new TypeCheckException(this, "Dereferencing is only possible on pointer types");
        }

        return GetType();
    }

    @Override
    public TypeNode GetType() {
        return m_Child.GetType().GetTypeDetails();
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        ParamContainer varAccess = m_Child.CreateSnippet(slave);
        return slave.LoadFromVariable(varAccess);
    }

    @Override
    public VarDeclNode GetVarDeclNode() {
        return ((AccessInterface) m_Child).GetVarDeclNode();
    }

    @Override
    public ParamContainer LoadValue(GeneratorSlave InSlave, ParamContainer InPointer) {
        return InSlave.LoadFromVariable(InPointer);
    }
}
