// Generated from C:/Users/Project_X/gitKraken/CompilerConstruction/src\Gram.g4 by ANTLR 4.7.2
package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GramParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GramVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GramParser#startRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartRule(GramParser.StartRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(GramParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirective(GramParser.DirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#unsignedNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedNumber(GramParser.UnsignedNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(GramParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(GramParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#labelDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelDeclarationPart(GramParser.LabelDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(GramParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#constantDefinitionPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinitionPart(GramParser.ConstantDefinitionPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#constantDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinition(GramParser.ConstantDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(GramParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#constantIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantIdentifier(GramParser.ConstantIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#typeDefinitionPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinitionPart(GramParser.TypeDefinitionPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#typeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinition(GramParser.TypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#typeDenoter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDenoter(GramParser.TypeDenoterContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#newType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewType(GramParser.NewTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#simpleTypeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleTypeIdentifier(GramParser.SimpleTypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#structuredTypeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredTypeIdentifier(GramParser.StructuredTypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#pointerTypeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointerTypeIdentifier(GramParser.PointerTypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#typeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdentifier(GramParser.TypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(GramParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#ordinalType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdinalType(GramParser.OrdinalTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#newOrdinalType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewOrdinalType(GramParser.NewOrdinalTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#ordinalTypeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrdinalTypeIdentifier(GramParser.OrdinalTypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#realTypeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealTypeIdentifier(GramParser.RealTypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#enumeratedType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumeratedType(GramParser.EnumeratedTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(GramParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#subrangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubrangeType(GramParser.SubrangeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#structuredType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredType(GramParser.StructuredTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#newStructuredType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewStructuredType(GramParser.NewStructuredTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#unpackedStructuredType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnpackedStructuredType(GramParser.UnpackedStructuredTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(GramParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#indexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexType(GramParser.IndexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#componentType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComponentType(GramParser.ComponentTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#recordType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordType(GramParser.RecordTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#fieldList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldList(GramParser.FieldListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#fixedPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFixedPart(GramParser.FixedPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#recordSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordSection(GramParser.RecordSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#fieldIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldIdentifier(GramParser.FieldIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variantPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariantPart(GramParser.VariantPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variantSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariantSelector(GramParser.VariantSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#tagField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagField(GramParser.TagFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariant(GramParser.VariantContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#tagType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagType(GramParser.TagTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#caseConstantList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseConstantList(GramParser.CaseConstantListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#caseConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseConstant(GramParser.CaseConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#setType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetType(GramParser.SetTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(GramParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#fileType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileType(GramParser.FileTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#pointerType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointerType(GramParser.PointerTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#newPointerType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewPointerType(GramParser.NewPointerTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#domainType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainType(GramParser.DomainTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variableDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationPart(GramParser.VariableDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(GramParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variableAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAccess(GramParser.VariableAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variableIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableIdentifier(GramParser.VariableIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#indexedVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexedVariable(GramParser.IndexedVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#fieldDesignator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDesignator(GramParser.FieldDesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#pointerVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointerVariable(GramParser.PointerVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#procedureAndFunctionDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureAndFunctionDeclarationPart(GramParser.ProcedureAndFunctionDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDeclaration(GramParser.ProcedureDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#procedureHeading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureHeading(GramParser.ProcedureHeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#procedureIdentification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureIdentification(GramParser.ProcedureIdentificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#procedureIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureIdentifier(GramParser.ProcedureIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#procedureBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureBlock(GramParser.ProcedureBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(GramParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#functionHeading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionHeading(GramParser.FunctionHeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#functionIdentification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionIdentification(GramParser.FunctionIdentificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#functionIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionIdentifier(GramParser.FunctionIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#resultType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResultType(GramParser.ResultTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#functionBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBlock(GramParser.FunctionBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(GramParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#formalParameterSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterSection(GramParser.FormalParameterSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#valueParameterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueParameterSpecification(GramParser.ValueParameterSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variableParameterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableParameterSpecification(GramParser.VariableParameterSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#proceduralParameterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProceduralParameterSpecification(GramParser.ProceduralParameterSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#functionalParameterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionalParameterSpecification(GramParser.FunctionalParameterSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#conformantArrayParameterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConformantArrayParameterSpecification(GramParser.ConformantArrayParameterSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#valueConformantArraySpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueConformantArraySpecification(GramParser.ValueConformantArraySpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#variableConformantArraySpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableConformantArraySpecification(GramParser.VariableConformantArraySpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#conformantArraySchema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConformantArraySchema(GramParser.ConformantArraySchemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#packedConformantArraySchema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackedConformantArraySchema(GramParser.PackedConformantArraySchemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#unpackedConformantArraySchema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnpackedConformantArraySchema(GramParser.UnpackedConformantArraySchemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#indexTypeSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexTypeSpecification(GramParser.IndexTypeSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(GramParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#boundIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoundIdentifier(GramParser.BoundIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(GramParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(GramParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(GramParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#unsignedConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedConstant(GramParser.UnsignedConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#setConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetConstructor(GramParser.SetConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#memberDesignator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDesignator(GramParser.MemberDesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#multiplyingOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyingOperator(GramParser.MultiplyingOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#addingOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddingOperator(GramParser.AddingOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#relationalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOperator(GramParser.RelationalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpression(GramParser.BooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#functionDesignator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDesignator(GramParser.FunctionDesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#actualParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParameterList(GramParser.ActualParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#actualParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParameter(GramParser.ActualParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(GramParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#simpleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(GramParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(GramParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(GramParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#procedureStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureStatement(GramParser.ProcedureStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#gotoStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGotoStatement(GramParser.GotoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#structuredStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredStatement(GramParser.StructuredStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#statementSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementSequence(GramParser.StatementSequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#statementPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementPart(GramParser.StatementPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(GramParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#conditionalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalStatement(GramParser.ConditionalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(GramParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#elsePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElsePart(GramParser.ElsePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(GramParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#caseListElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseListElement(GramParser.CaseListElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#caseIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseIndex(GramParser.CaseIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#repetitiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepetitiveStatement(GramParser.RepetitiveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#repeatStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStatement(GramParser.RepeatStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(GramParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(GramParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#controlVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControlVariable(GramParser.ControlVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#initialValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialValue(GramParser.InitialValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#finalValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinalValue(GramParser.FinalValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#withStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithStatement(GramParser.WithStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#recordVariableList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordVariableList(GramParser.RecordVariableListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#fieldDesignatorIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDesignatorIdentifier(GramParser.FieldDesignatorIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#readParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadParameterList(GramParser.ReadParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#readlnParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadlnParameterList(GramParser.ReadlnParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#writeParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteParameterList(GramParser.WriteParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#writeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteParameter(GramParser.WriteParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#writelnParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWritelnParameterList(GramParser.WritelnParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(GramParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#programHeading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramHeading(GramParser.ProgramHeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#programParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramParameterList(GramParser.ProgramParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramParser#programBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramBlock(GramParser.ProgramBlockContext ctx);
}