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
}
