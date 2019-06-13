package test.syntaxtree;

import org.antlr.v4.runtime.Token;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Node {
    private Token token;

    public Node() {
        token = null;
    }

    protected void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return this.token;
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
