package ast.core.operators;

import ast.BlockNode;
import ast.TypeCheckException;
import ast.core.StdBuilder;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode;
import ast.types.RecordTypeNode;
import ast.types.VoidTypeNode;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Struct;

public class RecordOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AGNStruct());
    }

    public static class AGNStruct extends PascalType_Assignment {
        public AGNStruct() {
            super(Operator.AGN, new VoidTypeNode(), RecordTypeNode.WildcardRecordNode(), (slave, lParam, rParam) -> {
                // TODO: keep same start reference of copy data?
                //  -> realloc memory of target pointers and copy memory of data
                if (rParam.HasExtendedType()) {
                    ParamContainer extended = slave.TryExtendType(lParam);
                    slave.StoreInVariable(extended, rParam);
                } else {
                    slave.CopyMemory(rParam, lParam);
                }
                return ParamContainer.VOIDCONTAINER();
            });
        }
    }
}