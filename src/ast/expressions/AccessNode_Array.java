package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.ArrayTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

import java.util.List;
import java.util.ArrayList;

public class AccessNode_Array extends AbstractSyntaxTree implements AccessInterface {
    private AbstractSyntaxTree m_Child;
    private List<AbstractSyntaxTree> m_IndexExpressions = new ArrayList<>();

    public AccessNode_Array(AbstractSyntaxTree InChild) {
        this.m_Child = InChild;
        m_Child.SetParent(this);
    }

    public void AddIndexExpression(AbstractSyntaxTree exp) {
        m_IndexExpressions.add(exp);
        exp.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode childType = m_Child.CheckType();
        if (!(childType instanceof ArrayTypeNode)) {
            throw new TypeCheckException(this, "Indexed access is only possible on arrays");
        }

        PrimitiveTypeNode IntTypeNode = PrimitiveTypeNode.IntNode;
        for (AbstractSyntaxTree index : m_IndexExpressions) {
            // Is IndexNode of primitive type INT?
            TypeNode IndexType = index.CheckType();
            if (!IntTypeNode.CompareType(IndexType)) {
                return null;
            }
        }

        return GetType();
    }

    @Override
    public TypeNode GetType() {
        return m_Child.GetType().GetTypeDetails();
        // TODO: if multiple indices, type is set of arraytype
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        ParamContainer varAccess = m_Child.CreateSnippet(slave);
        ParamContainer indexContainer = m_IndexExpressions.get(0).CreateSnippet(slave);
        return slave.CreateArrayElementPtr(varAccess, indexContainer);
    }

    @Override
    public ParamContainer LoadValue(GeneratorSlave InSlave, ParamContainer InPointer) {
        return InSlave.LoadFromVariable(InPointer);
    }
}
