package test.syntaxtree.scopes;

import test.syntaxtree.declarations.LabelDeclarationNode;
import test.syntaxtree.subs.SubNode;

public class ProxyNode extends ScopeNode {

    public ProxyNode(ScopeNode parent) {
        super(parent);
    }

    //TODO: custom resolve logic here!

    public LabelDeclarationNode searchLabelDeclaration(String name) {
        throw new RuntimeException("Illegal label target for label "+name+"! Jumps inside control blocks are not allowed");
    }

    @Override
    public LabelDeclarationNode searchLabelDeclarationGoto(String name) {
        return parentBlock.searchLabelDeclaration(name);
    }

    public SubNode searchFunctionOrProcedure(String name) {
        return parentBlock.searchFunctionOrProcedure(name);
    }
}
