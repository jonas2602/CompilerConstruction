package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.NamedTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.ValueWrapper;

public class WhileNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Condition;
    private AbstractSyntaxTree m_Body;

    public WhileNode(AbstractSyntaxTree condition, AbstractSyntaxTree body)
    {
        this.m_Condition = condition;
        this.m_Body = body;

        m_Condition.SetParent(this);
        m_Body.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode condType = m_Condition.CheckType();
        PrimitiveTypeNode boolType = PrimitiveTypeNode.BoolNode;
        if(!boolType.CompareType(condType)){
            throw new TypeCheckException(this, "Condition must return a bool type");
        }

        m_Body.CheckType();
        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // jump to initial compare
        ParamContainer compBranch = ParamContainer.LABELCONTAINER();
        slave.CreateJump(compBranch);

        // Create Comparator Block
        ValueWrapper compLabel = slave.CreateLabel();
        compBranch.SetValueAccessor(compLabel);
        ParamContainer compCond = m_Condition.CreateSnippet(slave);
        ParamContainer bodyBranch = ParamContainer.LABELCONTAINER();
        ParamContainer exitBranch = ParamContainer.LABELCONTAINER();
        slave.CreateBranch(compCond, bodyBranch, exitBranch);

        // Create Body Block
        ValueWrapper bodyLabel = slave.CreateLabel();
        bodyBranch.SetValueAccessor(bodyLabel);
        m_Body.CreateSnippet(slave);
        slave.CreateJump(compBranch);

        // Create Exit Block
        ValueWrapper exitLabel = slave.CreateLabel();
        exitBranch.SetValueAccessor(exitLabel);

        return null;
    }
}
