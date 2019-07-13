package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.VarDeclNode;
import ast.types.ArrayTypeNode;
import ast.types.ArrayTypeNode_Dynamic;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.List;
import java.util.ArrayList;

public class AccessNode_Array extends AbstractSyntaxTree implements AccessInterface {
    private AbstractSyntaxTree m_Child;
    private List<AbstractSyntaxTree> m_IndexExpressions;

    public AccessNode_Array(AbstractSyntaxTree child) {
        m_Child = child;
        m_Child.SetParent(this);

        m_IndexExpressions = new ArrayList<>();
    }

    public AccessNode_Array(AbstractSyntaxTree child, AbstractSyntaxTree... indices) {
        this(child);

        for (AbstractSyntaxTree element : indices) {
            AddIndexExpression(element);
        }
    }

    public void AddIndexExpression(AbstractSyntaxTree exp) {
        m_IndexExpressions.add(exp);
        exp.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode childType = m_Child.CheckType().GetCompareType();
        if (!(childType instanceof ArrayTypeNode || childType instanceof ArrayTypeNode_Dynamic)) {
            throw new TypeCheckException(this, "Indexed access is only possible on arrays");
        }

        for (AbstractSyntaxTree index : m_IndexExpressions) {
            // Is IndexNode of primitive type INT?
            TypeNode IndexType = index.CheckType();
            if (!PrimitiveTypeNode.IntNode.CompareType(IndexType)) {
                throw new TypeCheckException(this, "Indexed access is only possible with integers");
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
        indexContainer = AccessInterface.TryLoadValue(slave, m_IndexExpressions.get(0), indexContainer);

        if (m_Child.GetType() instanceof ArrayTypeNode) {
            return slave.CreateArrayElementPtr(varAccess, indexContainer);
        } else {
            ParamContainer startPropPtr = slave.CreateArrayElementPtr(varAccess, ArrayTypeNode_Dynamic.DynamicStartIndex);
            ParamContainer startProp = slave.LoadFromVariable(startPropPtr);
            return slave.CreatePtrArrayElementPtr(startProp, indexContainer);
        }
    }

    @Override
    public VarDeclNode GetVarDeclNode() {
        return ((AccessInterface) m_Child).GetVarDeclNode();
    }

    @Override
    public ParamContainer LoadValue(GeneratorSlave slave, ParamContainer pointer) {
        return slave.LoadFromVariable(pointer);
    }
}
