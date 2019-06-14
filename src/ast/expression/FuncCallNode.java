package ast.expression;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declaration.FuncDeclNode;
import ast.types.TypeNode;

import java.util.List;

public class FuncCallNode extends AbstractSyntaxTree {
    private String m_FuncName;
    private List<AbstractSyntaxTree> m_Params;

    public FuncCallNode(String InFuncName) {
        this.m_FuncName = InFuncName;
    }

    public void AddParameter(AbstractSyntaxTree InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);
    }

    @Override
    public TypeNode CheckType() {
        // Function with Name exists?
        FuncDeclNode funcDecl = GetOwningBlock().GetFunctionDeclaration(m_FuncName);
        if (funcDecl == null) {
            throw new TypeCheckException(this, "Function with Name " + m_FuncName + " is not defined");
        }

        // Parameter count fits the definition?
        if (funcDecl.GetParameterCount() != m_Params.size()) {
            throw new TypeCheckException(this, "Function expected " + funcDecl.GetParameterCount() + " Arguments but received " + m_Params.size());
        }

        // Compare given parameters to expected types
        for (int i = 0; i < m_Params.size(); i++) {
            TypeNode ParamType = m_Params.get(i).CheckType();
            if (!funcDecl.GetParameter(i).GetType().CompareType(ParamType)) {
                throw new TypeCheckException(this, "Function received unexpected type");
            }
        }

        return funcDecl.GetType();
    }
}
