package ast.statements;

import ast.AbstractSyntaxTree;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.snippets.CodeSnippet_Switch;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Primitive;
import writer.wrappers.ValueWrapper;

import java.util.ArrayList;
import java.util.List;

public class CaseNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Expression;
    private List<SelectorNode> m_Selectors;
    private AbstractSyntaxTree m_ElseBlock;

    public CaseNode(AbstractSyntaxTree expression, AbstractSyntaxTree elseBlock) {
        m_Expression = expression;
        m_ElseBlock = elseBlock;
        m_Selectors = new ArrayList<>();

        m_Expression.SetParent(this);
        m_ElseBlock.SetParent(this);
    }

    public void AddSelector(SelectorNode selector) {
        m_Selectors.add(selector);

        selector.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        if (!PrimitiveTypeNode.IntNode.CompareType(m_Expression.CheckType())) {
            throw new RuntimeException("Typecheck failed at Case Node because Condition is not of type integer");
        }

        for(SelectorNode s: m_Selectors) {
            s.CheckType();
        }

        if (m_ElseBlock != null) {
            m_ElseBlock.CheckType();
        }

        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // Generate result of expression
        ParamContainer expContainer = m_Expression.CreateSnippet(slave);
        ParamContainer elseContainer = ParamContainer.LABELCONTAINER();  // jumps to default / else
        ParamContainer exitContainer = ParamContainer.LABELCONTAINER();  // jumps to else/exit

        if(m_ElseBlock == null) {
            elseContainer = exitContainer;
        }

        CodeSnippet_Switch stmt = slave.CreateSwitch(expContainer, elseContainer);

        for(SelectorNode s: m_Selectors) {
            ParamContainer blockContainer = ParamContainer.LABELCONTAINER();

            for(Integer i: s.GetSelectors()) {
                ParamContainer num = new ParamContainer(TypeWrapper_Primitive.INT, i.toString());
                slave.AddLabelSwitch(stmt, num, blockContainer);
            }

            ValueWrapper blockValue = slave.CreateLabel();
            blockContainer.SetValueAccessor(blockValue);
            s.GetCompStmt().CreateSnippet(slave);
            slave.CreateJump(exitContainer);
        }

        //
        if (m_ElseBlock != null) {
            ValueWrapper elseBlock = slave.CreateLabel();
            elseContainer.SetValueAccessor(elseBlock);
            m_ElseBlock.CreateSnippet(slave);
            slave.CreateJump(exitContainer);
        }

        ValueWrapper exitValue = slave.CreateLabel();
        exitContainer.SetValueAccessor(exitValue);
        return null;
    }
}
