package ast.statements;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.TypeCheckException;
import ast.declarations.ParamDeclNode;
import ast.declarations.RecordFieldWrapper;
import ast.declarations.VarDeclNode;
import ast.types.ArrayTypeNode_Dynamic;
import ast.types.RecordTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;

import java.util.Collections;
import java.util.List;

public class WithNode extends BlockNode {
    private List<AbstractSyntaxTree> m_Variables;

    public WithNode(List<AbstractSyntaxTree> variables, AbstractSyntaxTree statement){
        this.m_Variables = variables;

        SetCompoundStatement(statement);
        Collections.reverse(m_Variables);
    }

    @Override
    public TypeNode CheckType() {
        for (AbstractSyntaxTree var : m_Variables) {
            var.SetParent(GetParent());
            TypeNode type = var.CheckType();
            if (!(type instanceof RecordTypeNode) || type instanceof ArrayTypeNode_Dynamic) {
                throw new TypeCheckException(this, "All variables must have a record as type");
            }

            RecordTypeNode record = (RecordTypeNode) type;
        }

        super.CheckType();

        return VoidTypeNode.VoidNode;
    }

    @Override
    public VarDeclNode GetVariableDeclaration(String variableName) {
        VarDeclNode outDecl = m_VarDeclMap.get(variableName);
        if (outDecl != null) {
            return outDecl;
        }

        for (AbstractSyntaxTree var : m_Variables) {
            RecordTypeNode record = (RecordTypeNode) var.GetType().GetTypeDetails();
            int idx = record.GetFieldIndex(variableName);
            if (idx != -1) {
                RecordFieldWrapper field = new RecordFieldWrapper(variableName ,var, record, idx);
                AddVariableDeclaration(field);
                return field;
            }
        }

        return super.GetVariableDeclaration(variableName);
    }
}
