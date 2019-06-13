package test.syntaxtree;

public class ProcedureDeclarationNode extends SubNode {

    public ProcedureDeclarationNode() {
        super();
    }

    @Override
    public String toString() {
        return "PROC "+name;
    }

    @Override
    public void print(int level) {
        super.println(level, this);
        block.print(level);
    }
}
