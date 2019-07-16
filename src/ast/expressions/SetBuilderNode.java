package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.VarDeclNode;
import ast.types.RangeTypeNode;
import ast.types.SetTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.ArrayList;
import java.util.List;


public class SetBuilderNode extends ConstantNode implements AccessInterface {
    private VarDeclNode m_TempSetVariable;
    private List<FuncCallNode> m_AppendCalls;
    private List<AbstractSyntaxTree> m_Constants;

    public SetBuilderNode(List<AbstractSyntaxTree> elements) {
        super("", new SetTypeNode((TypeNode) elements.get(0).GetType().GetTypeDetails().Copy()));

        m_TempSetVariable = new VarDeclNode("", m_TypeNode);
        m_Constants = elements;
        m_AppendCalls = new ArrayList<>();

        for (AbstractSyntaxTree e : elements) {
            if (e instanceof RangeTypeNode) {
                AddRange((RangeTypeNode)e);
            }
            else {
                AddElement(e);
            }
        }
    }

    public void AddElement(AbstractSyntaxTree node) {
        AccessNode_Variable m_TempSetAccess = new AccessNode_Variable("");
        m_TempSetAccess.SetDeclaration(m_TempSetVariable);

        FuncCallNode appendCall = new FuncCallNode(".append");
        appendCall.AddParameter(m_TempSetAccess);
        appendCall.AddParameter(node);

        m_AppendCalls.add(appendCall);
        appendCall.SetParent(this);
    }

    public void AddRange(RangeTypeNode r) {
        AccessNode_Variable m_TempSetAccess = new AccessNode_Variable("");
        m_TempSetAccess.SetDeclaration(m_TempSetVariable);

        FuncCallNode appendCall = new FuncCallNode(".appendRange");
        appendCall.AddParameter(m_TempSetAccess);
        appendCall.AddParameter(r.GetMin());
        appendCall.AddParameter(r.GetMax());

        m_AppendCalls.add(appendCall);
        appendCall.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        // make shure all elements have a type that fits an append function
        for (AbstractSyntaxTree call : m_AppendCalls) {
            call.CheckType();
        }

        // Make shure all elements have the same type
        TypeNode setType = ((SetTypeNode) m_TypeNode).GetSetElementType();
        for (AbstractSyntaxTree child : m_Constants) {
            if (!setType.CompareType(child.GetType().GetCompareType())) {
                throw new TypeCheckException(this, "Not all set elements have the same type");
            }
        }

        return GetType();
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        for (FuncCallNode callNode : m_AppendCalls) {
            callNode.CreateSnippet(slave);
        }


        return m_TempSetVariable.CreateSnippet(slave);
    }

    @Override
    public VarDeclNode GetVarDeclNode() {
        return m_TempSetVariable;
    }

    @Override
    public ParamContainer LoadValue(GeneratorSlave slave, ParamContainer pointer) {
        return slave.LoadFromVariable(pointer);
    }
}
