package ast.core.functions;

import ast.BlockNode;
import ast.ProgramNode;
import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.declarations.ParamDeclNode;
import ast.declarations.VarDeclNode;
import ast.expressions.FuncCallNode;
import ast.statements.CompStmtNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import ast.types.WildcardTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.*;

public class FuncDeclNode_Generic extends FuncDeclNode_Core implements Cloneable {
    private Set<FuncDeclNode_Generic> m_Instances = new HashSet<>();
    private Map<WildcardTypeNode, TypeNode> m_Config;

    public FuncDeclNode_Generic(String InName, TypeNode InReturnType) {
        super(InName, InReturnType);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        FuncDeclNode_Generic copy = (FuncDeclNode_Generic) super.clone();
        // TODO: use AddFunctionDeclaration of program blocknode
        copy.SetParent(ProgramNode.INSTANCE.GetBlock());

        // Build unique name
        String genericName = m_Name;
        for (WildcardTypeNode wildcard : m_Wildcards) {
            genericName += wildcard.GetTypeDetails().GetShortName();
        }
        copy.SetName(genericName);

        // Create Function Block
        copy.m_Block = new BlockNode();
        copy.m_Block.SetParent(copy);
        CompStmtNode compNode = new CompStmtNode();
        copy.GenerateBody(compNode);
        copy.m_Block.SetCompoundStatement(compNode);

        // Copy parameter list
        copy.m_Params = new ArrayList<>();
        for (ParamDeclNode param : m_Params) {
            copy.AddParameter((ParamDeclNode) param.Copy());
        }

        // copy return type
        if (!(m_ReturnType instanceof VoidTypeNode)) {
            copy.m_Block.AddParameterDeclaration(new VarDeclNode(genericName, (TypeNode) m_ReturnType.Copy()));
        }

        // TODO: support variables that are no parameters?
        return copy;
    }

    protected void GenerateBody(CompStmtNode compNode) {

    }

    @Override
    public FuncDeclNode ValidateCall(FuncCallNode callNode) {
        // Function fits to func call params?
        if (super.ValidateCall(callNode) == null) {
            return null;
        }

        try {
            // has fitting instance?
            for (FuncDeclNode_Generic inst : m_Instances) {
                if (inst.CompareConfig()) {
                    return inst;
                }
            }

            // Create new version of generic function
            FuncDeclNode_Generic copy = (FuncDeclNode_Generic) clone();
            copy.CheckType();
            // set ocnfig of wildcard parameters
            copy.m_Config = new HashMap<>();
            m_Instances.add(copy);
            for (WildcardTypeNode wildcard : m_Wildcards) {
                copy.m_Config.put(wildcard, wildcard.GetTypeDetails());
            }
            // TODO: build generic name

            return copy;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return this;
    }

    protected boolean CompareConfig() {
        for (WildcardTypeNode wildcard : m_Wildcards) {
            if (!wildcard.CompareType(m_Config.get(wildcard))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // rebuild config
        for (WildcardTypeNode element : m_Wildcards) {
            TypeNode configType = m_Config.get(element);
            element.SetFilledType(configType);
        }

        // create function as usual
        return super.CreateSnippet(slave);
    }

//    @Override
//    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
//        CompStmtNode compNode = new CompStmtNode();
//        m_Block.SetCompoundStatement(compNode);
//
//        GenerateBody(compNode);
//
//        return CreateSnippet(slave);
//    }
}
