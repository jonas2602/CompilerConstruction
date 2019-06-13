package test.syntaxtree;

public class FunctionDeclarationNode extends SubNode {

    public FunctionDeclarationNode() {
        super();
    }

    @Override
    public String toString() {
        return "FUNC "+name;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
        block.print(level);
    }
}
