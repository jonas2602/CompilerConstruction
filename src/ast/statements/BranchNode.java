package ast.statements;

import ast.AbstractSyntaxTree;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.*;
import writer.wrappers.ParamContainer;
import writer.wrappers.ValueWrapper;

public class BranchNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Condition;
    private AbstractSyntaxTree m_Then;
    private AbstractSyntaxTree m_Else;

    public BranchNode(AbstractSyntaxTree condition, AbstractSyntaxTree thenBlock, AbstractSyntaxTree elseBlock) {
        this.m_Condition = condition;
        this.m_Then = thenBlock;
        this.m_Else = elseBlock;

        m_Condition.SetParent(this);
        m_Then.SetParent(this);
        if (m_Else != null) {
            m_Else.SetParent(this);
        }
    }

    @Override
    public TypeNode CheckType() {
        if (!PrimitiveTypeNode.BoolNode.CompareType(m_Condition.CheckType())) {
            throw new RuntimeException("Typecheck failed at Branch Node because Condition is not of type bool");
        }

        m_Then.CheckType();
        if (m_Else != null) {
            m_Else.CheckType();
        }

        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // Generate result of condition
        ParamContainer condContainer = m_Condition.CreateSnippet(slave);

        // Create branch instruction with empty params
        ParamContainer posContainer = ParamContainer.LABELCONTAINER();  // jumps to then
        ParamContainer negContainer = ParamContainer.LABELCONTAINER();  // jumps to else/exit
        slave.CreateBranch(condContainer, posContainer, negContainer);

        // Create then branch
        ParamContainer exitContainer = ParamContainer.LABELCONTAINER(); // jumps to exit
        ValueWrapper posValue = slave.CreateLabel();
        posContainer.SetValueAccessor(posValue);
        m_Then.CreateSnippet(slave);
        slave.CreateJump(exitContainer);

        // Create else branch
        if (m_Else != null) {
            ValueWrapper negValue = slave.CreateLabel();
            negContainer.SetValueAccessor(negValue);
            m_Else.CreateSnippet(slave);
            slave.CreateJump(exitContainer);
        }

        // Create exit branch
        ValueWrapper exitValue = slave.CreateLabel();

        // fill containers, that jump out of the branches
        if (m_Else == null) {
            negContainer.SetValueAccessor(exitValue);
        }
        exitContainer.SetValueAccessor(exitValue);

        return null;
    }
}
