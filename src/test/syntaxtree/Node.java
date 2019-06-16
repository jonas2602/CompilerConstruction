package test.syntaxtree;

import org.antlr.v4.runtime.Token;
import test.syntaxtree.scopes.ScopeNode;

public abstract class Node {
    protected Token token;
    protected ScopeNode parentBlock;

    public Node() {
        token = null;
        parentBlock = null;
    }

    public Node(ScopeNode block) {
        parentBlock = block;
    }

    protected void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return this.token;
    }

    public void setParentBlock(ScopeNode parentBlock) {
        this.parentBlock = parentBlock;
    }

    public ScopeNode getParentBlock() {
        return this.parentBlock;
    }

    public void print() {
        print(0);
    }

    public void print(Object obj) {
        print(0, obj);
    }

    public abstract void print(int level);

    protected void print(int level, Object obj) {
        if(level == 0) {
            System.out.print(obj);
        }
        else {
            System.out.printf("%" + level + "s%s", "", obj);
        }
    }

    protected void println(int level, Object obj) {
        print(level, obj);
        System.out.println();
    }
}
