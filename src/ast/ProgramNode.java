package ast;

import ast.core.PascalType_Int;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_FuncDef;
import llvm.CodeSnippet_Type;
import writer.GeneratorSlave;

import java.util.ArrayList;
import java.util.List;

// TODO: inherit from FuncDeclNode
public class ProgramNode extends AbstractSyntaxTree {
    private String m_Name;
    private List<AbstractSyntaxTree> m_Params = new ArrayList<>();
    private BlockNode m_Block;

    public ProgramNode(String InName) {
        this.m_Name = InName;
    }

    public void AddParameter(AbstractSyntaxTree InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);
    }

    public void SetBlock(BlockNode InBlock) {
        InBlock.SetParent(this);
        this.m_Block = InBlock;
    }

    @Override
    public TypeNode CheckType() {
        // the block will take care of checking the parameter types

        m_Block.CheckType();
        return null;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        CodeSnippet_FuncDef funcDef = slave.CreateFunctionDefinition("main", CodeSnippet_Type.SNIPPETTYPE_INT, true);
        m_Block.CreateSnippet(slave, funcDef);
        PascalType_Int returnType = new PascalType_Int();
        funcDef.AddStatement(slave.CreateReturnStmt(returnType.GetShortName(), returnType.GetDefaultValue()));
        return funcDef;
    }
}
