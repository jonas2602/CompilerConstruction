package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.core.functions.math.MathFunctions;
import ast.core.operators.Operator;
import ast.declarations.FuncDeclNode;
import ast.declarations.ParamDeclNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class FuncCallNode extends AbstractSyntaxTree {
    private String m_FuncName;
    private List<AbstractSyntaxTree> m_Params;

    private FuncDeclNode m_FuncDecl;

    public FuncCallNode(String name) {
        m_FuncName = name;
        m_Params = new ArrayList<>();
    }

    public FuncCallNode(String name, AbstractSyntaxTree... params) {
        this(name);

        for (AbstractSyntaxTree param : params) {
            AddParameter(param);
        }
    }

    public FuncCallNode(Operator operator) {
        this(operator.GetOperatorFunctionName());
    }

    public FuncCallNode(Operator operator, AbstractSyntaxTree... params) {
        this(operator);

        for (AbstractSyntaxTree param : params) {
            AddParameter(param);
        }
    }

    public void AddParameter(AbstractSyntaxTree InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);
    }


    public int GetParameterCount() {
        return m_Params.size();
    }

    public List<AbstractSyntaxTree> GetParameterList() {
        return m_Params;
    }

    public AbstractSyntaxTree GetParameter(int Index) {
        return m_Params.get(Index);
    }

    public TypeNode GetParameterType(int Index) {
        return GetParameter(Index).GetType();
    }

    @Override
    public TypeNode CheckType() {
        // Function with Name exists?
        List<FuncDeclNode> funcOverloads = GetOwningBlock().GetFunctionDeclaration(m_FuncName);
        if (funcOverloads == null || funcOverloads.size() < 1) {
            throw new TypeCheckException(this, "Function with Name " + m_FuncName + " is not defined");
        }

        // Validate Types of used Parameters
        for (int i = 0; i < m_Params.size(); i++) {
            m_Params.get(i).CheckType();
        }

        // Given parameters fit any function signature?
        for (FuncDeclNode funcDecl : funcOverloads) {
            m_FuncDecl = funcDecl.ValidateCall(this);
            if (m_FuncDecl != null) {
                break;
            }
        }

        if (m_FuncDecl == null) {
            StringBuilder builder = new StringBuilder();
            for (AbstractSyntaxTree param : m_Params) {
                TypeWrapper type = param.GetType().GetWrappedType();
                if (type == null) {
                    builder.append(" " + param + " (Couldn't load WrappedType)");
                } else {
                    builder.append(" " + type.CreateTypeName());
                }
            }

            throw new TypeCheckException(this, "Function with Name " + m_FuncName + " is not overloaded for given types " + builder.toString());
        }

        return m_FuncDecl.GetType();
    }

    @Override
    public TypeNode GetType() {
        // if (m_FuncDecl == null) {
        //     m_FuncDecl = GetOwningBlock().GetFunctionDeclaration(m_FuncName);
        // }

        return m_FuncDecl.GetType();
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // build function if not already created or inline
        if (!m_FuncDecl.IsInline()) {
            m_FuncDecl.CreateSnippet(slave);
        }

        // Execute specialized function call
        if (m_FuncDecl instanceof FuncDeclNode_Core && ((FuncDeclNode_Core) m_FuncDecl).HasCustomCallLogic()) {
            return ((FuncDeclNode_Core) m_FuncDecl).CreateFunctionCall(slave, this);
        }

        // Create standard function call
        List<ParamContainer> funcParams = new ArrayList<>();
        // CodeSnippet_FuncCall call = new CodeSnippet_FuncCall(m_FuncName, new CodeSnippet_Plain(returnType.CreateTypeName()));

        // create parameters for function call
        for (int i = 0; i < m_Params.size(); i++) {
            //
            AbstractSyntaxTree param = m_Params.get(i);
            ParamContainer paramContainer = param.CreateSnippet(slave);

            // use reference for "VAR" types, else value
            // TODO: allow VAR as keyword? expect the user to declare the parameter as pointer instead?
            ParamDeclNode decl = m_FuncDecl.GetParameter(i);
            if (decl.IsByValue() && !m_FuncDecl.IsInline()) {
                // load value if requested from a variable
                paramContainer = AccessInterface.TryLoadValue(slave, param, paramContainer);
            }

            funcParams.add(paramContainer);
        }

        ParamContainer OutParam;
        if (m_FuncDecl.IsInline()) {
            OutParam = m_FuncDecl.CreateInlineSnippet(slave, this, funcParams);
        } else {
            // Create Function Call Snippet + Parameter
            TypeWrapper returnType = m_FuncDecl.GetType().GetWrappedType();
            OutParam = slave.CreateFunctionCall(m_FuncDecl.GetHierarchicalName(), returnType, true);
            for (ParamContainer param : funcParams) {
                slave.CreateFunctionCallParameter(param);
            }
            slave.ExitScope();
        }

        return OutParam;
    }
}
