package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.ProcDeclNode;
import ast.types.TypeNode;

import java.util.ArrayList;
import java.util.List;

public class ProcCallNode extends AbstractSyntaxTree {
    private String m_ProcName;
    private List<AbstractSyntaxTree> m_Params = new ArrayList<>();

    public ProcCallNode(String InProcName) {
        this.m_ProcName = InProcName;
    }

    public void AddParameter(AbstractSyntaxTree InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);
    }

    @Override
    public TypeNode CheckType() {
        // TODO: Also check defined functions?
        // Procedure with Name exists?
        ProcDeclNode procDecl = GetOwningBlock().GetProcedureDeclaration(m_ProcName);
        if (procDecl == null) {
            throw new TypeCheckException(this, "Procedure with Name " + m_ProcName + " is not defined");
        }

        // Parameter count fits the definition?
        if (procDecl.GetParameterCount() != m_Params.size()) {
            throw new TypeCheckException(this, "Procedure expected " + procDecl.GetParameterCount() + " Arguments but received " + m_Params.size());
        }

        // Compare given parameters to expected types
        for (int i = 0; i < m_Params.size(); i++) {
            TypeNode ParamType = m_Params.get(i).CheckType();
            if (!procDecl.GetParameter(i).GetType().CompareType(ParamType)) {
                throw new TypeCheckException(this, "Procedure received unexpected type");
            }
        }

        // In contrast to functions, procedures does not return a value
        return null;
    }
}
