package test.syntaxtree.constants;

import test.syntaxtree.BlockNode;

public class RealConstant extends NumberConstant {

    private double value;

    public RealConstant(BlockNode parent) {
        super(parent);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(real) " + value;
    }
}
