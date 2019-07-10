package ast.core.functions.list;

import ast.core.FuncDeclNode_Core;
import ast.core.operators.Operator;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessNode_Field;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.CompStmtNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_realloc;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

public class FuncDeclNode_setLength extends FuncDeclNode_Generic {
    public FuncDeclNode_setLength() {
        super("setlength", VoidTypeNode.VoidNode);
        // m_bCustomCallLogic = true;
        // m_bInline = true;

        AddParameter("arr", ArrayTypeNode_Dynamic.WildcardArrayNode());
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

//        TypeNode genericType = PrimitiveTypeNode.IntNode;
//
//        // bytesize = newLength * sizeof(type)
//        int typeSize = genericType.GetWrappedType().GetTypeSize();
//        FuncCallNode mulCall = new FuncCallNode(Operator.MUL);
//        mulCall.AddParameter(new AccessNode_Variable("newLength"));
//        mulCall.AddParameter(new ConstantNode(Integer.toString(typeSize), PrimitiveTypeNode.IntNode));
//
//        // arr.start = malloc(bytesize)
//        AccessNode_Field startAccess = new AccessNode_Field(arrAccess, ArrayTypeNode_Dynamic.DynamicStartName);
//        FuncCallNode mallocCall = new FuncCallNode("getmem");
//        mallocCall.AddParameter(startAccess);
//        mallocCall.AddParameter(mulCall);
//        // AssignmentNode assignNodeMalloc = new AssignmentNode(startAccess, mallocCall);
//
//        // arr.start = realloc(arr.start, newLength)
//        FuncCallNode reallocCall = new FuncCallNode("reallocmem");
//        reallocCall.AddParameter(startAccess);
//        reallocCall.AddParameter(new AccessNode_Variable("newLength"));
//        AssignmentNode assignNodeRealloc = new AssignmentNode(startAccess, reallocCall);
//
//        BranchNode branch = new BranchNode(greaterComparison, mallocCall, assignNodeRealloc);
//        compNode.AddStatement(branch);
//
//        // arr.length = newLength
//        AssignmentNode assignNodeLength = new AssignmentNode(lengthAccess, new AccessNode_Variable("newLength"));
//        compNode.AddStatement(assignNodeLength);


        compNode.AddStatement(greaterComparison);
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
