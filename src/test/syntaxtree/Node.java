package test.syntaxtree;

import org.antlr.v4.runtime.Token;

public abstract class Node {
    protected Token token;
    protected BlockNode parentBlock;

    public Node() {
        token = null;
        parentBlock = null;
    }

    public Node(BlockNode block) {
        parentBlock = block;
    }

    protected void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return this.token;
    }

    public void setParentBlock(BlockNode parentBlock) {
        this.parentBlock = parentBlock;
    }

    public BlockNode getParentBlock() {
        return this.parentBlock;
    }

    public void print() {
        print(0);
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
