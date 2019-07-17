package ast.core.functions.list;

import ast.core.functions.FuncDeclNode_Generic;
import ast.core.functions.memory.Memory;
import ast.core.operators.Operator;
import ast.expressions.*;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.CompStmtNode;
import ast.types.*;

public class FuncDeclNode_setLength extends FuncDeclNode_Generic {
    public FuncDeclNode_setLength() {
        super(List.SETLEN, VoidTypeNode.VoidNode);
        // m_bCustomCallLogic = true;
        // m_bInline = true;

        AddParameter("arr", ArrayTypeNode_Dynamic.WildcardArrayNode(), true);
        AddParameter("newLength", PrimitiveTypeNode.IntNode);
    }

    @Override
    protected void GenerateBody(CompStmtNode compNode) {
        // arr.length > 0
        AccessNode_Variable arrAccess = new AccessNode_Variable("arr");
        AccessNode_Field lengthAccess = new AccessNode_Field(arrAccess, ArrayTypeNode_Dynamic.DynamicLengthName);
        FuncCallNode greaterComparison = new FuncCallNode(Operator.GT);
        greaterComparison.AddParameter(lengthAccess);
        greaterComparison.AddParameter(new ConstantNode("0", PrimitiveTypeNode.IntNode));


        // bytesize = newLength * sizeof(type)
        TypeNode genericType = GetParameter(0).GetType().GetTypeDetails();
        int typeSize = genericType.GetWrappedType().GetTypeByteSize(genericType.GetWrappedType().GetAlignment());
        FuncCallNode mulCall = new FuncCallNode(Operator.MUL);
        mulCall.AddParameter(new AccessNode_Variable("newLength"));
        mulCall.AddParameter(new ConstantNode(Integer.toString(typeSize), PrimitiveTypeNode.IntNode));

        // arr.start = malloc(bytesize)
        AccessNode_Field startAccess = new AccessNode_Field(arrAccess, ArrayTypeNode_Dynamic.DynamicStartName);
        FuncCallNode mallocCall = new FuncCallNode(Memory.GETMEM);
        mallocCall.AddParameter(startAccess);
        mallocCall.AddParameter(mulCall);
        // FuncCallNode printCall = new FuncCallNode("writeln");
        // printCall.AddParameter(new ConstantNode_String("test"));
        // CompStmtNode thenBranch = new CompStmtNode(mallocCall, printCall);

        // arr.start = realloc(arr.start, newLength)
        FuncCallNode reallocCall = new FuncCallNode(Memory.REALLOC);
        reallocCall.AddParameter(startAccess);
        reallocCall.AddParameter(mulCall);
        AssignmentNode assignNodeRealloc = new AssignmentNode(startAccess, reallocCall);

        BranchNode branch = new BranchNode(greaterComparison, assignNodeRealloc, mallocCall);
        compNode.AddStatement(branch);

        // arr.length = newLength
        AssignmentNode assignNodeLength = new AssignmentNode(lengthAccess, new AccessNode_Variable("newLength"));
        compNode.AddStatement(assignNodeLength);
    }

//    @Override
//    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
//        // get func call parameter
//        ParamContainer dynArrParam = callNode.GetParameter(0).CreateSnippet(slave);
//        ParamContainer lengthParam = callNode.GetParameter(1).CreateSnippet(slave);
//
//        // Get array start ptr as charptr
//        ParamContainer startProp = slave.CreateArrayElementPtr(dynArrParam, ArrayTypeNode_Dynamic.DynamicStartIndex);
//        ParamContainer startPropValue = slave.LoadFromVariable(startProp);
//        ParamContainer startCharPtr = slave.BitCast(startPropValue, TypeWrapper_Pointer.CHARPTR);
//
//        // Get value of new length parameter
//        int lengthValue = Integer.parseInt(lengthParam.CreateDataString());
//
//        // native call to reallocate memory
//        ParamContainer outCharPtr = slave.CreateNativeCall(new NativeFunction_realloc(startCharPtr, lengthValue));
//
//        // store new startptr in dynamic array record
//        ParamContainer newStartParam = slave.BitCast(outCharPtr, startPropValue.GetRootType());
//        slave.StoreInVariable(startProp, newStartParam);
//
//        // update length value
//        ParamContainer lengthProp = slave.CreateArrayElementPtr(dynArrParam, ArrayTypeNode_Dynamic.DynamicLengthIndex);
//        slave.StoreInVariable(lengthProp, lengthParam);
//
//        // TODO: initialize newly created elements
//
//        return ParamContainer.VOIDCONTAINER();
//    }
}
