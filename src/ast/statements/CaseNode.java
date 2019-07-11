package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.operators.Operator;
import ast.declarations.ConstDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import ast.types.RangeTypeNode;
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
        if(m_ElseBlock != null) {
            m_ElseBlock.SetParent(this);
        }
    }

    public void AddSelector(SelectorNode selector) {
        m_Selectors.add(selector);

        selector.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode type = m_Expression.CheckType();
        if((!PrimitiveTypeNode.IntNode.CompareType(type)) && (!PrimitiveTypeNode.CharNode.CompareType(type)) && (!PrimitiveTypeNode.BoolNode.CompareType(type))) {
            throw new TypeCheckException(this, "Typecheck failed at Case Node because Condition is not of type integer, char or bool");
        }

        for(SelectorNode s: m_Selectors) {
            TypeNode stype = s.CheckType();
            if(!type.CompareType(stype)) {
                throw new TypeCheckException(this, "Typecheck failed at Case Node because Selector Node type does not match expression type");
            }
        }

        if (m_ElseBlock != null) {
            m_ElseBlock.CheckType();
        }

        //check for collisions
        for (SelectorNode s: m_Selectors) {
            //at this point we know, that we only have constants or ranges here
            List<AbstractSyntaxTree> selectors = s.GetSelectors();
            for (AbstractSyntaxTree a: selectors) {
                for (SelectorNode x: m_Selectors) {
                    //ignore the current selector
                    if(s == x) {
                        continue;
                    }

                    List<AbstractSyntaxTree> check = x.GetSelectors();
                    for (AbstractSyntaxTree b: check) {
                        if (a instanceof AccessNode_Variable || a instanceof ConstantNode) {
                            int valA = loadAccessVariableOrConstant(a);
                            if (b instanceof AccessNode_Variable || b instanceof ConstantNode) {
                                int valB = loadAccessVariableOrConstant(b);
                                if (valA == valB) {
                                    throw new TypeCheckException(this, "Typecheck failed at Case Node because multiple selectors exist for the value "+valA);
                                }
                            }
                            else {
                                // range check
                                if (((RangeTypeNode)b).CheckInRange(valA)) {
                                    throw new TypeCheckException(this, "Typecheck failed at Case Node because a selector lies within another subrange selector");
                                }
                            }
                        }
                        else {
                            // seems like a is a range
                            if (b instanceof AccessNode_Variable || b instanceof ConstantNode) {
                                int valB = loadAccessVariableOrConstant(b);
                                // range check
                                if (((RangeTypeNode)a).CheckInRange(valB)) {
                                    throw new TypeCheckException(this, "Typecheck failed at Case Node because a selector lies within another subrange selector");
                                }
                            }
                            else {
                                //both are ranges
                                RangeTypeNode rangeA = (RangeTypeNode)a;
                                RangeTypeNode rangeB = (RangeTypeNode)b;
                                if(rangeA.CheckInRange(rangeB) || rangeB.CheckInRange(rangeA)) {
                                    throw new TypeCheckException(this, "Typecheck failed at Case Node because a selector range lies within another subrange selector");
                                }
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    private int loadAccessVariableOrConstant(AbstractSyntaxTree a) {
        if (a instanceof AccessNode_Variable) {
            return loadAccessVariable(a);
        }
        if (a instanceof ConstantNode) {
            return loadConstant(a);
        }

        throw new TypeCheckException(this, "Selector is neither Variable nor Constant");
    }

    private int loadAccessVariable(AbstractSyntaxTree a) {
        String dataA = ((ConstantNode)(((ConstDeclNode)(((AccessNode_Variable)a).GetDeclaration())).GetConstant())).GetData();
        return Integer.parseInt(dataA);
    }

    private int loadConstant(AbstractSyntaxTree a) {
        String dataA = ((ConstantNode)a).GetData();
        return Integer.parseInt(dataA);
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // Generate result of expression
        ParamContainer expContainer = m_Expression.CreateSnippet(slave);
        expContainer = AccessInterface.TryLoadValue(slave, m_Expression, expContainer);
        ParamContainer elseContainer = ParamContainer.LABELCONTAINER();  // jumps to default / else
        ParamContainer exitContainer = ParamContainer.LABELCONTAINER();  // jumps to else/exit

        CodeSnippet_Switch stmt = slave.CreateSwitch(expContainer, elseContainer);

        ArrayList<RangeTypeNode> list = new ArrayList<>();
        ArrayList<ParamContainer> labels = new ArrayList<>();

        for(SelectorNode s: m_Selectors) {
            ParamContainer blockContainer = ParamContainer.LABELCONTAINER();

            for(AbstractSyntaxTree i: s.GetSelectors()) {
                if (i instanceof RangeTypeNode) {
                    list.add((RangeTypeNode) i);
                    labels.add(blockContainer);
                    continue;
                }

                ParamContainer num = i.CreateSnippet(slave);
                slave.AddLabelSwitch(stmt, num, blockContainer);
            }

            ValueWrapper blockValue = slave.CreateLabel();
            blockContainer.SetValueAccessor(blockValue);
            s.GetCompStmt().CreateSnippet(slave);
            slave.CreateJump(exitContainer);
        }

        ValueWrapper elseBlock = slave.CreateLabel();
        elseContainer.SetValueAccessor(elseBlock);
        for (int i = 0; i < list.size(); i++) {
            RangeTypeNode r = list.get(i);
            ParamContainer l = labels.get(i);
            FuncCallNode lower = new FuncCallNode(Operator.GE);
            lower.AddParameter(m_Expression);
            lower.AddParameter(r.GetMin());
            FuncCallNode upper = new FuncCallNode(Operator.LE);
            upper.AddParameter(m_Expression);
            upper.AddParameter(r.GetMax());
            FuncCallNode and = new FuncCallNode(Operator.AND);
            and.AddParameter(lower);
            and.AddParameter(upper);

            and.SetParent(this);
            and.CheckType();

            ParamContainer compCond = and.CreateSnippet(slave);
            ParamContainer exitBranch = ParamContainer.LABELCONTAINER();
            slave.CreateBranch(compCond, l, exitBranch);

            ValueWrapper exitValue = slave.CreateLabel();
            exitBranch.SetValueAccessor(exitValue);
        }

        if (m_ElseBlock != null) {
            m_ElseBlock.CreateSnippet(slave);
        }
        slave.CreateJump(exitContainer);

        ValueWrapper exitValue = slave.CreateLabel();
        exitContainer.SetValueAccessor(exitValue);
        return null;
    }
}
