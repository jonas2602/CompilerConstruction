package ast.core.functions.list;

import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.statements.CompStmtNode;
import ast.types.TypeNode;
import ast.types.WildcardTypeNode;
import writer.GeneratorSlave;
import writer.snippets.CodeSnippet_Base;
import writer.wrappers.ParamContainer;

import java.util.*;

public class FuncDeclNode_Generic extends FuncDeclNode_Core implements Cloneable {
    private Set<FuncDeclNode_Generic> m_Instances = new HashSet<>();
    private Map<WildcardTypeNode, TypeNode> m_Config;

    public FuncDeclNode_Generic(String InName, TypeNode InReturnType) {
        super(InName, InReturnType);

        m_Config = new HashMap<>();


        CompStmtNode compNode = new CompStmtNode();
        m_Block.SetCompoundStatement(compNode);
        GenerateBody(compNode);
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
                if (inst.CompareSignature(this)) {
                    return inst;
                }
            }

            // Create new version of generic function
            FuncDeclNode_Generic copy = (FuncDeclNode_Generic) clone();
            m_Instances.add(copy);
            for (WildcardTypeNode wildcard : m_Wildcards) {
                copy.m_Config.put(wildcard, wildcard.GetTypeDetails());
            }
            return copy;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return this;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {

//        for (Map<WildcardTypeNode, TypeNode> config : m_WildcardTypes) {
//            for (Map.Entry<WildcardTypeNode, TypeNode> element : config.entrySet()) {
//                element.getKey().SetFilledType(element.getValue());
//            }
//
//            super.CreateSnippet(slave);
//        }

        return null;
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
