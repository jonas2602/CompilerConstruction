package ast.core;

import ast.BlockNode;
import ast.core.operators.Operator;
import ast.declarations.FuncDeclNode;
import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import ast.types.WildcardTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.HashSet;
import java.util.Set;

public abstract class FuncDeclNode_Core extends FuncDeclNode {
    protected boolean m_bCustomCallLogic = false;

    protected Set<WildcardTypeNode> m_Wildcards = new HashSet<>();

    public FuncDeclNode_Core(String name, TypeNode returnType) {
        super(name, returnType, new BlockNode());
    }

    public FuncDeclNode_Core(FunctionNameWrapper function, TypeNode returnType) {
        this(function.GetFunctionName(), returnType);
    }

    @Override
    public void AddParameter(ParamDeclNode InParam) {
        super.AddParameter(InParam);

        m_Wildcards.addAll(InParam.GetType().GetWildcards());
    }

    @Override
    public FuncDeclNode ValidateCall(FuncCallNode callNode) {
        for (WildcardTypeNode card : m_Wildcards) {
            card.Clear();
        }

        return super.ValidateCall(callNode);
    }

    public boolean HasCustomCallLogic() {
        return m_bCustomCallLogic;
    }

    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        return null;
    }
}
