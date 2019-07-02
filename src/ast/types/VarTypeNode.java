package ast.types;

import writer.TypeWrapper;
import writer.TypeWrapper_Pointer;

public class VarTypeNode extends PointerTypeNode {
    public static final VarTypeNode IntVarNode = new VarTypeNode(PrimitiveTypeNode.IntNode);
    public static final VarTypeNode FloatVarNode = new VarTypeNode(PrimitiveTypeNode.FloatNode);
    public static final VarTypeNode CharVarNode = new VarTypeNode(PrimitiveTypeNode.CharNode);
    public static final VarTypeNode BoolVarNode = new VarTypeNode(PrimitiveTypeNode.BoolNode);

    public VarTypeNode(TypeNode baseType) {
        super(baseType);
    }

    @Override
    public TypeNode GetType() {
        return m_BaseType;
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!otherTypeNode.CompareType(m_BaseType)) {
            return false;
        }

        return true;
    }
}
