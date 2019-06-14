package ast.declaration;

import ast.BlockNode;
import ast.types.TypeNode;

public class FuncDeclNode extends ProcDeclNode {
    private TypeNode m_ReturnType;

    public FuncDeclNode(String InName, TypeNode InReturnType, BlockNode InBlock){
        super(InName, InBlock);

        m_ReturnType = InReturnType;
        m_ReturnType.SetParent(this);
    }

    // No need the Check Child types, because block is handled by parent class,
    // checking the block includes the return type because its stored in the block
    // as variable

    @Override
    public TypeNode GetType() {
        return m_ReturnType;
    }
}
