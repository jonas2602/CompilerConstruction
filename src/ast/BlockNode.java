package ast;

import ast.declarations.*;
import ast.types.TypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.GeneratorSlave;

import java.util.*;

public class BlockNode extends AbstractSyntaxTree {

    private HashMap<String, LabelDeclNode> m_LabelDeclMap;
    private HashMap<String, ConstDeclNode> m_ConstDeclMap;
    private HashMap<String, TypeDeclNode> m_TypeDeclMap;
    private HashMap<String, VarDeclNode> m_VarDeclMap;
    private HashMap<String, List<FuncDeclNode>> m_FuncDeclMap;

    // TODO: add all names of consts, types, vars, ... to avoid collision
    private HashSet<String> m_UsedNames;

    private AbstractSyntaxTree m_CompoundStatement;

    private boolean m_MainBlock;
    private List<ParamDeclNode> m_PassDownParams;

    public BlockNode() {
        this(false);
    }

    public BlockNode(boolean mainBlock) {
        m_LabelDeclMap = new HashMap<>();
        m_ConstDeclMap = new HashMap<>();
        m_TypeDeclMap = new HashMap<>();
        m_VarDeclMap = new HashMap<>();
        m_FuncDeclMap = new HashMap<>();

        m_MainBlock = mainBlock;
        m_PassDownParams = new ArrayList<>();
    }

    public List<ParamDeclNode> GetPassDownParams() {
        return m_PassDownParams;
    }

    public ParamDeclNode GetPassDownParam(int index) {
        return m_PassDownParams.get(index);
    }

    public int GetNumberPassDownParams() {
        return m_PassDownParams.size();
    }

    public void AddLabelDeclaration(LabelDeclNode label) {
        if (m_VarDeclMap.containsKey(label.GetName())) {
            throw new RuntimeException("Variable with Name " + label.GetName() + " already defined in Scope");
        }

        label.SetParent(this);
        m_LabelDeclMap.put(label.GetName(), label);
    }

    public void AddConstantDeclaration(ConstDeclNode constant) {
        if (m_VarDeclMap.containsKey(constant.GetName())) {
            throw new RuntimeException("Variable with Name " + constant.GetName() + " already defined in Scope");
        }

        constant.SetParent(this);
        m_ConstDeclMap.put(constant.GetName(), constant);
    }

    public void AddTypeDeclaration(String name, TypeNode type) {
        AddTypeDeclaration(new TypeDeclNode(name, type));
    }

    public void AddTypeDeclaration(TypeDeclNode type) {
        if (m_VarDeclMap.containsKey(type.GetName())) {
            throw new RuntimeException("Variable with Name " + type.GetName() + " already defined in Scope");
        }

        type.SetParent(this);
        m_TypeDeclMap.put(type.GetName(), type);
    }

    public void AddParameterDeclaration(VarDeclNode parameter) {
        if (m_VarDeclMap.containsKey(parameter.GetName())) {
            throw new RuntimeException("Variable with Name " + parameter.GetName() + " already defined in Scope");
        }

        // Don't set parent, because function/procedure should be the parent;
        m_VarDeclMap.put(parameter.GetName(), parameter);
    }

    public void AddVariableDeclaration(VarDeclNode variable) {
        if (m_VarDeclMap.containsKey(variable.GetName())) {
            throw new RuntimeException("Variable with Name " + variable.GetName() + " already defined in Scope");
        }

        variable.SetParent(this);
        m_VarDeclMap.put(variable.GetName(), variable);
    }

    public void AddFunctionDeclaration(FuncDeclNode function) {
        if (m_VarDeclMap.containsKey(function.GetName())) {
            throw new RuntimeException("Variable with Name " + function.GetName() + " already defined in Scope");
        }

        function.SetParent(this);
        List<FuncDeclNode> overloads = m_FuncDeclMap.get(function.GetName());
        if (overloads == null) {
            overloads = new ArrayList<>();
            m_FuncDeclMap.put(function.GetName(), overloads);
        }
        overloads.add(function);
    }

    public AbstractSyntaxTree GetCompoundStatement() {
        return m_CompoundStatement;
    }

    public void SetCompoundStatement(AbstractSyntaxTree compoundStatement) {
        m_CompoundStatement = compoundStatement;
        // TODO: Validate if the compund Statement should be child of the function/procedure/program or of the block
        m_CompoundStatement.SetParent(this);
    }

    public LabelDeclNode GetLabelDeclaration(String labelName) {
        LabelDeclNode outDecl = m_LabelDeclMap.get(labelName);
        if (outDecl == null && GetOwningBlock() != null) {
            outDecl = GetOwningBlock().GetLabelDeclaration(labelName);
        }

        return outDecl;
    }

    public ConstDeclNode GetConstantDeclaration(String constName) {
        ConstDeclNode outDecl = m_ConstDeclMap.get(constName);
        if (outDecl == null && GetOwningBlock() != null) {
            outDecl = GetOwningBlock().GetConstantDeclaration(constName);
        }

        return outDecl;
    }

    public TypeDeclNode GetTypeDeclaration(String typeName) {
        TypeDeclNode outDecl = m_TypeDeclMap.get(typeName);
        if (outDecl == null && GetOwningBlock() != null) {
            outDecl = GetOwningBlock().GetTypeDeclaration(typeName);
        }

        return outDecl;
    }

    public VarDeclNode GetVariableDeclaration(String variableName) {
        return GetVariableDeclaration(variableName, this);
    }

    public VarDeclNode GetVariableDeclaration(String variableName, BlockNode requestScope) {
        VarDeclNode outDecl = m_VarDeclMap.get(variableName);
        if (outDecl == null && GetOwningBlock() != null) {
            outDecl = GetOwningBlock().GetVariableDeclaration(variableName, requestScope);

            // TODO: scope stuff rework required!!!
            if (outDecl == null) {
                return null;
            }

            //check if this is the sourceblock for this request
            if (requestScope == this) {
                if (!outDecl.IsGlobalVariable()) {
                    //1: add variable to own scope
                    ParamDeclNode param = new ParamDeclNode(variableName, outDecl.GetType());
                    param.SetByReference();
                    m_VarDeclMap.put(variableName, param);
                    //2: add variable to extra list
                    m_PassDownParams.add(param);
                    return param;
                }
            }
        }

        //request is from a different scope
        if (requestScope != this) {
            if (m_MainBlock) {
                outDecl.SetGlobalVariable(true);
            }
        }

        // TODO: Check constants?
        return outDecl;
    }

    // TODO: How to check for functions that are not defined by the user? e.g. writeln(), chr() or Length()
    public List<FuncDeclNode> GetFunctionDeclaration(String functionName) {
        List<FuncDeclNode> outDecl = m_FuncDeclMap.get(functionName);
        if (outDecl == null && GetOwningBlock() != null) {
            outDecl = GetOwningBlock().GetFunctionDeclaration(functionName);
        }

        return outDecl;
    }

    @Override
    public TypeNode CheckType() {
        // Labels first because they don't rely on other block elements
        for (LabelDeclNode labelDecl : m_LabelDeclMap.values()) {
            labelDecl.CheckType();
            // TODO: Check for duplicate label names
        }

        // Types next because the following elements (vars, funcs, ...) could use the new types
        for (TypeDeclNode typeDecl : m_TypeDeclMap.values()) {
            typeDecl.CheckType();
            // TODO: Check for duplicate type names
        }

        // Constants and Variables must be visited next because they are required for everything coming
        for (ConstDeclNode constDecl : m_ConstDeclMap.values()) {
            constDecl.CheckType();
            // TODO: Check for duplicate const names
        }

        for (VarDeclNode varDecl : m_VarDeclMap.values()) {
            varDecl.CheckType();
            // TODO: Check for duplicate variable names
        }

        for (List<FuncDeclNode> funcOverloads : m_FuncDeclMap.values()) {
            // Check types of overloaded function
            for (FuncDeclNode funcDecl : funcOverloads) {
                funcDecl.CheckType();
            }

            // Check for duplicate signatures
            for (int i = 0; i < funcOverloads.size(); i++) {
                for (int j = i + 1; j < funcOverloads.size(); j++) {
                    if (funcOverloads.get(i).CompareSignature(funcOverloads.get(j))) {
                        throw new TypeCheckException(this, "Functions with the same signature found " + (funcOverloads.get(i).GetName()));
                    }
                }
            }
        }


        // statement last because it could use everything above
        if (m_CompoundStatement != null) {
            m_CompoundStatement.CheckType();
        }

        return null;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        for (TypeDeclNode type : m_TypeDeclMap.values()) {
            type.CreateSnippet(slave);
        }

        for (VarDeclNode var : m_VarDeclMap.values()) {
            var.CreateSnippet(slave);
        }

        if (m_CompoundStatement != null) {
            m_CompoundStatement.CreateSnippet(slave, ctx);
        }

        return null;
    }
}
