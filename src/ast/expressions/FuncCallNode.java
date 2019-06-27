package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.types.TypeNode;
import llvm.*;
import writer.GeneratorSlave;
import writer.TypeContainer;
import writer.TypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class FuncCallNode extends AbstractSyntaxTree {
    private String m_FuncName;
    private List<AbstractSyntaxTree> m_Params = new ArrayList<>();

    private FuncDeclNode m_FuncDecl;

    public FuncCallNode(String InFuncName) {
        this.m_FuncName = InFuncName;
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
            if (funcDecl.ValidateCall(this)) {
                m_FuncDecl = funcDecl;
                break;
            }
        }
        if (m_FuncDecl == null) {
            throw new TypeCheckException(this, "Function with Name " + m_FuncName + " is not overloaded for given types");
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

//    @Override
//    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
//        // build function if not already created or inline
//        if (!m_FuncDecl.IsInline()) {
//            m_FuncDecl.CreateSnippet(slave, null);
//        }
//
//        // Execute specialized function call
//        if (m_FuncDecl instanceof FuncDeclNode_Core) {
//            FuncDeclNode_Core coreFunc = (FuncDeclNode_Core) m_FuncDecl;
//            if (coreFunc.HasCustomCallLogic()) {
//                return null; // coreFunc.CreateFunctionCall(slave, ctx, this);
//            }
//        } else {
//            // TODO: default creation
//            CodeSnippet_Base returnType = m_FuncDecl.GetType().CreateSnippet(slave, ctx);
//            CodeSnippet_FuncCall call = new CodeSnippet_FuncCall(m_FuncName, returnType);
//
//            for (AbstractSyntaxTree param : m_Params) {
//                call.AddParameter(CreateParameterSnippet(slave, call, param));
//            }
//
//            if (m_FuncDecl.IsVoid()) {
//                slave.GetScopeSnippet().AddStatement(call);
//                return null;
//            } else {
//                int LocalIndex = slave.GetScopeSnippet().AddStatementWithStorage(call.Write());
//                return new CodeSnippet_Plain("%" + LocalIndex);
//            }
//
//
//        }
//
//        return null;
//    }

    @Override
    public TypeContainer CreateSnippet(GeneratorSlave slave) {
        // build function if not already created or inline
        if (!m_FuncDecl.IsInline()) {
            m_FuncDecl.CreateSnippet(slave, null);
        }

        // Execute specialized function call
        if (m_FuncDecl instanceof FuncDeclNode_Core) {
            FuncDeclNode_Core coreFunc = (FuncDeclNode_Core) m_FuncDecl;
            if (coreFunc.HasCustomCallLogic()) {
                return coreFunc.CreateFunctionCall(slave, this);
            }
        } else {
            // TODO: default creation
            TypeWrapper returnType = m_FuncDecl.GetType().GetWrappedType();
            CodeSnippet_FuncCall call = new CodeSnippet_FuncCall(m_FuncName, new CodeSnippet_Plain(returnType.GetTypeName()));

            for (AbstractSyntaxTree param : m_Params) {
                TypeContainer paramContainer = param.CreateSnippet(slave);
                // load value if requested from a variable
                if (!(param instanceof ConstantNode)) {
                    paramContainer = slave.LoadFromVariable(paramContainer);
                }
                call.AddParameter(paramContainer.CreateParameterString());
            }

            if (m_FuncDecl.IsVoid()) {
                slave.GetScopeSnippet().AddStatement(call);
                return null;
            } else {
                int LocalIndex = slave.GetScopeSnippet().AddStatementWithStorage(call.Write());
                return new TypeContainer(slave, returnType, "%" + LocalIndex);
            }


        }

        return null;
    }
}
