package ast;

import ast.core.PascalType_Int;
import ast.types.TypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.snippets.CodeSnippet_FuncDef;
import writer.GeneratorSlave;
import writer.wrapper.TypeWrapper_Primitive;

import java.util.ArrayList;
import java.util.List;

// TODO: inherit from FuncDeclNode
public class ProgramNode extends AbstractSyntaxTree {
    private String m_Name;
    private List<AbstractSyntaxTree> m_Params;
    private BlockNode m_Block;

    public ProgramNode(String name) {
        m_Name = name;
        m_Params = new ArrayList<>();
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
        CodeSnippet_FuncDef funcDef = slave.CreateFunctionDefinition("main", TypeWrapper_Primitive.INT, 0, true);
        m_Block.CreateSnippet(slave, funcDef);
        PascalType_Int returnType = new PascalType_Int();
        funcDef.AddStatement(slave.CreateReturnStmt(returnType.GetTypeName(), returnType.GetDefaultValue()));
        return funcDef;
    }
}
