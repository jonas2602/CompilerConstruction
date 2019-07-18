package ast.core.functions.array.dynamic;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.functions.FuncDeclNode_Generic;
import ast.core.functions.array.ArrayFunction;
import ast.core.functions.memory.Memory;
import ast.core.operators.Operator;
import ast.core.operators.PascalType_SingleOperator;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessNode_Field;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.CompStmtNode;
import ast.types.ArrayTypeNode_Dynamic;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class ListFunctions implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new GetLengthArrayDynamic());
        std.AddFunctionDeclaration(new SetLengthArrayDynamic());
        std.AddFunctionDeclaration(new HighArrayDynamic());
        std.AddFunctionDeclaration(new LowArrayDynamic());
    }

    public class HighArrayDynamic extends FuncDeclNode_Core {
        public HighArrayDynamic() {
            super(ArrayFunction.HIGH, PrimitiveTypeNode.IntNode);
            AddParameter("array", ArrayTypeNode_Dynamic.WildcardArrayNode());

            m_bInline = true;

            AccessNode_Variable arrAccess = new AccessNode_Variable("array");
            AccessNode_Field fieldAccess = new AccessNode_Field(arrAccess, "length");
            FuncCallNode subCall = new FuncCallNode(Operator.SUB, fieldAccess, ConstantNode.IntNode(1));

            m_Block.SetCompoundStatement(subCall);
        }
    }

    public class LowArrayDynamic extends PascalType_SingleOperator {
        public LowArrayDynamic() {
            super(ArrayFunction.LOW, PrimitiveTypeNode.IntNode, ArrayTypeNode_Dynamic.WildcardArrayNode(), (slave, lParam) -> ParamContainer.INTCONTAINER(0));
        }
    }

    public class GetLengthArrayDynamic extends FuncDeclNode_Core {
        public GetLengthArrayDynamic() {
            super(List.GETLEN, PrimitiveTypeNode.IntNode);
            m_bCustomCallLogic = true;
            m_bInline = true;

            AddParameter(new ParamDeclNode("arr", ArrayTypeNode_Dynamic.WildcardArrayNode()));
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer dynArrParam = callNode.GetParameter(0).CreateSnippet(slave);
            ParamContainer lengthField = slave.CreateArrayElementPtr(dynArrParam, ArrayTypeNode_Dynamic.DynamicLengthIndex);
            ParamContainer lengthValue = slave.LoadFromVariable(lengthField);

            return lengthValue;
        }
    }

    public class SetLengthArrayDynamic extends FuncDeclNode_Generic {
        public SetLengthArrayDynamic() {
            super(List.SETLEN, VoidTypeNode.VoidNode);

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
    }
}
