package ast;

import ast.declarations.*;
import ast.types.TypeNode;

import java.util.HashMap;

public class BlockNode extends AbstractSyntaxTree {

    private HashMap<String, LabelDeclNode> m_LabelDeclMap = new HashMap<>();
    private HashMap<String, ConstDeclNode> m_ConstDeclMap = new HashMap<>();
    private HashMap<String, TypeDeclNode> m_TypeDeclMap = new HashMap<>();
    private HashMap<String, VarDeclNode> m_VarDeclMap = new HashMap<>();
    private HashMap<String, ProcDeclNode> m_ProcDeclMap = new HashMap<>();
    private HashMap<String, FuncDeclNode> m_FuncDeclMap = new HashMap<>();

    private AbstractSyntaxTree m_CompoundStatement;

    public BlockNode() {

    }

    public void AddLabelDeclaration(LabelDeclNode Label) {
        if (m_VarDeclMap.containsKey(Label.GetName())) {
            throw new RuntimeException("Variable with Name " + Label.GetName() + " already defined in Scope");
        }

        Label.SetParent(this);
        m_LabelDeclMap.put(Label.GetName(), Label);
    }

    public void AddConstantDeclaration(ConstDeclNode Constant) {
        if (m_VarDeclMap.containsKey(Constant.GetName())) {
            throw new RuntimeException("Variable with Name " + Constant.GetName() + " already defined in Scope");
        }

        Constant.SetParent(this);
        m_ConstDeclMap.put(Constant.GetName(), Constant);
    }

    public void AddTypeDeclaration(TypeDeclNode Type) {
        if (m_VarDeclMap.containsKey(Type.GetName())) {
            throw new RuntimeException("Variable with Name " + Type.GetName() + " already defined in Scope");
        }

        Type.SetParent(this);
        m_TypeDeclMap.put(Type.GetName(), Type);
    }

    public void AddParameterDeclaration(VarDeclNode Parameter) {
        if (m_VarDeclMap.containsKey(Parameter.GetName())) {
            throw new RuntimeException("Variable with Name " + Parameter.GetName() + " already defined in Scope");
        }

        // Don't set parent, because function/procedure should be the parent;
        m_VarDeclMap.put(Parameter.GetName(), Parameter);
    }

    public void AddVariableDeclaration(VarDeclNode Variable) {
        if (m_VarDeclMap.containsKey(Variable.GetName())) {
            throw new RuntimeException("Variable with Name " + Variable.GetName() + " already defined in Scope");
        }

        Variable.SetParent(this);
        m_VarDeclMap.put(Variable.GetName(), Variable);
    }

    public void AddProcedureDeclaration(ProcDeclNode Procedure) {
        if (m_VarDeclMap.containsKey(Procedure.GetName())) {
            throw new RuntimeException("Variable with Name " + Procedure.GetName() + " already defined in Scope");
        }

        Procedure.SetParent(this);
        m_ProcDeclMap.put(Procedure.GetName(), Procedure);
    }

    public void AddFunctionDeclaration(FuncDeclNode Function) {
        if (m_VarDeclMap.containsKey(Function.GetName())) {
            throw new RuntimeException("Variable with Name " + Function.GetName() + " already defined in Scope");
        }

        Function.SetParent(this);
        m_FuncDeclMap.put(Function.GetName(), Function);
    }

    public void SetCompoundStatement(AbstractSyntaxTree InCompoundStatement) {
        m_CompoundStatement = InCompoundStatement;
        // TODO: Validate if the compund Statement should be child of the function/procedure/program or of the block
        m_CompoundStatement.SetParent(this);
    }

    public LabelDeclNode GetLabelDeclaration(String LabelName) {
        LabelDeclNode OutDecl = m_LabelDeclMap.get(LabelName);
        if (OutDecl == null && GetOwningBlock() != null) {
            OutDecl = GetOwningBlock().GetLabelDeclaration(LabelName);
        }

        return OutDecl;
    }

    public TypeDeclNode GetTypeDeclaration(String TypeName) {
        TypeDeclNode OutDecl = m_TypeDeclMap.get(TypeName);
        if (OutDecl == null && GetOwningBlock() != null) {
            OutDecl = GetOwningBlock().GetTypeDeclaration(TypeName);
        }

        return OutDecl;
    }

    public VarDeclNode GetVariableDeclaration(String VariableName) {
        VarDeclNode OutDecl = m_VarDeclMap.get(VariableName);
        if (OutDecl == null && GetOwningBlock() != null) {
            OutDecl = GetOwningBlock().GetVariableDeclaration(VariableName);
        }

        // TODO: Check constants?
        return OutDecl;
    }

    // TODO: How to check for functions that are not defined by the user? e.g. writeln(), chr() or Length()
    public FuncDeclNode GetFunctionDeclaration(String FunctionName) {
        FuncDeclNode OutDecl = m_FuncDeclMap.get(FunctionName);
        if (OutDecl == null && GetOwningBlock() != null) {
            OutDecl = GetOwningBlock().GetFunctionDeclaration(FunctionName);
        }

        return OutDecl;
    }

    public ProcDeclNode GetProcedureDeclaration(String ProcedureName) {
        ProcDeclNode OutDecl = m_FuncDeclMap.get(ProcedureName);
        if (OutDecl == null && GetOwningBlock() != null) {
            OutDecl = GetOwningBlock().GetProcedureDeclaration(ProcedureName);
        }

        return OutDecl;
    }

    @Override
    public TypeNode CheckType() {
        for (LabelDeclNode labelDecl : m_LabelDeclMap.values()) {
            labelDecl.CheckType();
        }
        for (ConstDeclNode constDecl : m_ConstDeclMap.values()) {
            constDecl.CheckType();
        }
        for (TypeDeclNode typeDecl : m_TypeDeclMap.values()) {
            typeDecl.CheckType();
        }
        for (VarDeclNode varDecl : m_VarDeclMap.values()) {
            varDecl.CheckType();
        }
        for (ProcDeclNode procDecl : m_ProcDeclMap.values()) {
            procDecl.CheckType();
        }
        for (FuncDeclNode funcDecl : m_FuncDeclMap.values()) {
            funcDecl.CheckType();
        }

        m_CompoundStatement.CheckType();

        return null;
    }
}
