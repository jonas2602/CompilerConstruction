package test.syntaxtree.statements.calls;

import gen.PascalParser;
import gen.PascalParser.ParameterListContext;
import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.subs.SubNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class SubCall extends Node {
    protected String name;
    protected SubNode target;
    protected List<Parameter> parameters;

    public SubCall(ScopeNode parent) {
        super(parent);
        parameters = new LinkedList<Parameter>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Parameter> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public void visitParameterList(ParameterListContext ctx) {
        for(PascalParser.ActualParameterContext param: ctx.actualParameter()) {
            Parameter p = new Parameter(parentBlock);
            p.buildAST(param);
            parameters.add(p);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name + "(");
        for(Parameter p: parameters) {
            builder.append(p);
        }
        builder.append(")");
        return builder.toString();
    }
}
