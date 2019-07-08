package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import ast.types.WildcardTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.HashSet;
import java.util.Set;

public abstract class PascalType_Operator extends FuncDeclNode_Core {

    protected FunctionCallTwoParams m_Operation;
    private Set<WildcardTypeNode> m_Wildcards = new HashSet<>();

    public PascalType_Operator(String name, TypeNode returnType, TypeNode lparam, TypeNode rparam, FunctionCallTwoParams operation) {
        super(name, returnType);

        AddParameter(new ParamDeclNode("left", lparam));
        AddParameter(new ParamDeclNode("right", rparam));

        m_bCustomCallLogic = true;
        m_bInline = true;

        m_Wildcards.addAll(lparam.GetWildcards());
        m_Wildcards.addAll(rparam.GetWildcards());

        this.m_Operation = operation;
    }

    public PascalType_Operator(Operator operator, TypeNode returnType, TypeNode lparam, TypeNode rparam, FunctionCallTwoParams operation) {
        this(operator.GetOperatorFunctionName(), returnType, lparam, rparam, operation);
    }

    @Override
    public boolean ValidateCall(FuncCallNode callNode) {
        for (WildcardTypeNode card : m_Wildcards) {
            card.Clear();
        }

        return super.ValidateCall(callNode);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
        AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);
        ParamContainer leftParam = lParam.CreateSnippet(slave);
        ParamContainer rightParam = rParam.CreateSnippet(slave);

        // load values if requested from a variable
        leftParam = AccessInterface.TryLoadValue(slave, lParam, leftParam);
        rightParam = AccessInterface.TryLoadValue(slave, rParam, rightParam);

        return m_Operation.createFunctionCall(slave, leftParam, rightParam);
    }
}