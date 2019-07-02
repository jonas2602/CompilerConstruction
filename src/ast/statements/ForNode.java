package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.operators.Operator;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.ValueWrapper;

public class ForNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Variable;
    // AbstractSyntaxTree m_InitialValue;
    // AbstractSyntaxTree m_FinalValue;
    // boolean m_Increment;

    private AssignmentNode m_InitAssign;
    private FuncCallNode m_CompCall;
    private FuncCallNode m_StepCall;
    private AbstractSyntaxTree m_Body;

    public ForNode(AbstractSyntaxTree variable, AbstractSyntaxTree initialValue, AbstractSyntaxTree finalValue, boolean bIncrement, AbstractSyntaxTree body) {
        this.m_Variable = variable;
        this.m_Body = body;

        m_InitAssign = new AssignmentNode(variable, initialValue);

        Operator compOperator = bIncrement ? Operator.LE : Operator.GE;
        m_CompCall = new FuncCallNode(compOperator);
        m_CompCall.AddParameter(variable);
        m_CompCall.AddParameter(finalValue);

        Operator stepOperator = bIncrement ? Operator.INC : Operator.DEC;
        m_StepCall = new FuncCallNode(stepOperator);
        m_StepCall.AddParameter(variable);


        m_InitAssign.SetParent(this);
        m_CompCall.SetParent(this);
        m_StepCall.SetParent(this);
        m_Body.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        m_InitAssign.CheckType();
        m_CompCall.CheckType();

        // Supported Types are automatically validated by checking
        // if the variable type has the used step operator overloaded
        m_StepCall.CheckType();

        m_Body.CheckType();

        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // initialize variable with start value
        m_InitAssign.CreateSnippet(slave);
        ParamContainer compBranch = ParamContainer.LABELCONTAINER();
        slave.CreateJump(compBranch);

        // Create Comparator Block
        ValueWrapper compLabel = slave.CreateLabel();
        compBranch.SetValueAccessor(compLabel);
        ParamContainer compCond = m_CompCall.CreateSnippet(slave);
        ParamContainer bodyBranch = ParamContainer.LABELCONTAINER();
        ParamContainer exitBranch = ParamContainer.LABELCONTAINER();
        slave.CreateBranch(compCond, bodyBranch, exitBranch);

        // Create Body Block
        ValueWrapper bodyLabel = slave.CreateLabel();
        bodyBranch.SetValueAccessor(bodyLabel);
        m_Body.CreateSnippet(slave);
        ParamContainer stepBranch = ParamContainer.LABELCONTAINER();
        slave.CreateJump(stepBranch);

        // Create Increment/Decrement Block
        ValueWrapper stepLabel = slave.CreateLabel();
        stepBranch.SetValueAccessor(stepLabel);
        ParamContainer variable = m_Variable.CreateSnippet(slave);
        ParamContainer newValue = m_StepCall.CreateSnippet(slave);
        slave.StoreInVariable(variable, newValue);
        slave.CreateJump(compBranch);

        // Create Exit Block
        ValueWrapper exitLabel = slave.CreateLabel();
        exitBranch.SetValueAccessor(exitLabel);

        return null;
    }
}
