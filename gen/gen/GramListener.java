// Generated from C:/Users/Project_X/gitKraken/CompilerConstruction/src\Gram.g4 by ANTLR 4.7.2
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GramParser}.
 */
public interface GramListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GramParser#startRule}.
	 * @param ctx the parse tree
	 */
	void enterStartRule(GramParser.StartRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#startRule}.
	 * @param ctx the parse tree
	 */
	void exitStartRule(GramParser.StartRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(GramParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(GramParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDirective(GramParser.DirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDirective(GramParser.DirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#unsignedNumber}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedNumber(GramParser.UnsignedNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#unsignedNumber}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedNumber(GramParser.UnsignedNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterSign(GramParser.SignContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitSign(GramParser.SignContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(GramParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(GramParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#labelDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void enterLabelDeclarationPart(GramParser.LabelDeclarationPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#labelDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void exitLabelDeclarationPart(GramParser.LabelDeclarationPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(GramParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(GramParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#constantDefinitionPart}.
	 * @param ctx the parse tree
	 */
	void enterConstantDefinitionPart(GramParser.ConstantDefinitionPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#constantDefinitionPart}.
	 * @param ctx the parse tree
	 */
	void exitConstantDefinitionPart(GramParser.ConstantDefinitionPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#constantDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstantDefinition(GramParser.ConstantDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#constantDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstantDefinition(GramParser.ConstantDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(GramParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(GramParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#constantIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterConstantIdentifier(GramParser.ConstantIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#constantIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitConstantIdentifier(GramParser.ConstantIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#typeDefinitionPart}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefinitionPart(GramParser.TypeDefinitionPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#typeDefinitionPart}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefinitionPart(GramParser.TypeDefinitionPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#typeDefinition}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefinition(GramParser.TypeDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#typeDefinition}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefinition(GramParser.TypeDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#typeDenoter}.
	 * @param ctx the parse tree
	 */
	void enterTypeDenoter(GramParser.TypeDenoterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#typeDenoter}.
	 * @param ctx the parse tree
	 */
	void exitTypeDenoter(GramParser.TypeDenoterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#newType}.
	 * @param ctx the parse tree
	 */
	void enterNewType(GramParser.NewTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#newType}.
	 * @param ctx the parse tree
	 */
	void exitNewType(GramParser.NewTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#simpleTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterSimpleTypeIdentifier(GramParser.SimpleTypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#simpleTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitSimpleTypeIdentifier(GramParser.SimpleTypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#structuredTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterStructuredTypeIdentifier(GramParser.StructuredTypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#structuredTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitStructuredTypeIdentifier(GramParser.StructuredTypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#pointerTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterPointerTypeIdentifier(GramParser.PointerTypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#pointerTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitPointerTypeIdentifier(GramParser.PointerTypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#typeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeIdentifier(GramParser.TypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#typeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeIdentifier(GramParser.TypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleType(GramParser.SimpleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleType(GramParser.SimpleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#ordinalType}.
	 * @param ctx the parse tree
	 */
	void enterOrdinalType(GramParser.OrdinalTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#ordinalType}.
	 * @param ctx the parse tree
	 */
	void exitOrdinalType(GramParser.OrdinalTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#newOrdinalType}.
	 * @param ctx the parse tree
	 */
	void enterNewOrdinalType(GramParser.NewOrdinalTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#newOrdinalType}.
	 * @param ctx the parse tree
	 */
	void exitNewOrdinalType(GramParser.NewOrdinalTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#ordinalTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterOrdinalTypeIdentifier(GramParser.OrdinalTypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#ordinalTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitOrdinalTypeIdentifier(GramParser.OrdinalTypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#realTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterRealTypeIdentifier(GramParser.RealTypeIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#realTypeIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitRealTypeIdentifier(GramParser.RealTypeIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#enumeratedType}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratedType(GramParser.EnumeratedTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#enumeratedType}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratedType(GramParser.EnumeratedTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(GramParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(GramParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#subrangeType}.
	 * @param ctx the parse tree
	 */
	void enterSubrangeType(GramParser.SubrangeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#subrangeType}.
	 * @param ctx the parse tree
	 */
	void exitSubrangeType(GramParser.SubrangeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#structuredType}.
	 * @param ctx the parse tree
	 */
	void enterStructuredType(GramParser.StructuredTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#structuredType}.
	 * @param ctx the parse tree
	 */
	void exitStructuredType(GramParser.StructuredTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#newStructuredType}.
	 * @param ctx the parse tree
	 */
	void enterNewStructuredType(GramParser.NewStructuredTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#newStructuredType}.
	 * @param ctx the parse tree
	 */
	void exitNewStructuredType(GramParser.NewStructuredTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#unpackedStructuredType}.
	 * @param ctx the parse tree
	 */
	void enterUnpackedStructuredType(GramParser.UnpackedStructuredTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#unpackedStructuredType}.
	 * @param ctx the parse tree
	 */
	void exitUnpackedStructuredType(GramParser.UnpackedStructuredTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(GramParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(GramParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#indexType}.
	 * @param ctx the parse tree
	 */
	void enterIndexType(GramParser.IndexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#indexType}.
	 * @param ctx the parse tree
	 */
	void exitIndexType(GramParser.IndexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#componentType}.
	 * @param ctx the parse tree
	 */
	void enterComponentType(GramParser.ComponentTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#componentType}.
	 * @param ctx the parse tree
	 */
	void exitComponentType(GramParser.ComponentTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#recordType}.
	 * @param ctx the parse tree
	 */
	void enterRecordType(GramParser.RecordTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#recordType}.
	 * @param ctx the parse tree
	 */
	void exitRecordType(GramParser.RecordTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#fieldList}.
	 * @param ctx the parse tree
	 */
	void enterFieldList(GramParser.FieldListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#fieldList}.
	 * @param ctx the parse tree
	 */
	void exitFieldList(GramParser.FieldListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#fixedPart}.
	 * @param ctx the parse tree
	 */
	void enterFixedPart(GramParser.FixedPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#fixedPart}.
	 * @param ctx the parse tree
	 */
	void exitFixedPart(GramParser.FixedPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#recordSection}.
	 * @param ctx the parse tree
	 */
	void enterRecordSection(GramParser.RecordSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#recordSection}.
	 * @param ctx the parse tree
	 */
	void exitRecordSection(GramParser.RecordSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#fieldIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterFieldIdentifier(GramParser.FieldIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#fieldIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitFieldIdentifier(GramParser.FieldIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variantPart}.
	 * @param ctx the parse tree
	 */
	void enterVariantPart(GramParser.VariantPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variantPart}.
	 * @param ctx the parse tree
	 */
	void exitVariantPart(GramParser.VariantPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variantSelector}.
	 * @param ctx the parse tree
	 */
	void enterVariantSelector(GramParser.VariantSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variantSelector}.
	 * @param ctx the parse tree
	 */
	void exitVariantSelector(GramParser.VariantSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#tagField}.
	 * @param ctx the parse tree
	 */
	void enterTagField(GramParser.TagFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#tagField}.
	 * @param ctx the parse tree
	 */
	void exitTagField(GramParser.TagFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variant}.
	 * @param ctx the parse tree
	 */
	void enterVariant(GramParser.VariantContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variant}.
	 * @param ctx the parse tree
	 */
	void exitVariant(GramParser.VariantContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#tagType}.
	 * @param ctx the parse tree
	 */
	void enterTagType(GramParser.TagTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#tagType}.
	 * @param ctx the parse tree
	 */
	void exitTagType(GramParser.TagTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#caseConstantList}.
	 * @param ctx the parse tree
	 */
	void enterCaseConstantList(GramParser.CaseConstantListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#caseConstantList}.
	 * @param ctx the parse tree
	 */
	void exitCaseConstantList(GramParser.CaseConstantListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#caseConstant}.
	 * @param ctx the parse tree
	 */
	void enterCaseConstant(GramParser.CaseConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#caseConstant}.
	 * @param ctx the parse tree
	 */
	void exitCaseConstant(GramParser.CaseConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#setType}.
	 * @param ctx the parse tree
	 */
	void enterSetType(GramParser.SetTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#setType}.
	 * @param ctx the parse tree
	 */
	void exitSetType(GramParser.SetTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(GramParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(GramParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#fileType}.
	 * @param ctx the parse tree
	 */
	void enterFileType(GramParser.FileTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#fileType}.
	 * @param ctx the parse tree
	 */
	void exitFileType(GramParser.FileTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#pointerType}.
	 * @param ctx the parse tree
	 */
	void enterPointerType(GramParser.PointerTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#pointerType}.
	 * @param ctx the parse tree
	 */
	void exitPointerType(GramParser.PointerTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#newPointerType}.
	 * @param ctx the parse tree
	 */
	void enterNewPointerType(GramParser.NewPointerTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#newPointerType}.
	 * @param ctx the parse tree
	 */
	void exitNewPointerType(GramParser.NewPointerTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#domainType}.
	 * @param ctx the parse tree
	 */
	void enterDomainType(GramParser.DomainTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#domainType}.
	 * @param ctx the parse tree
	 */
	void exitDomainType(GramParser.DomainTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variableDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationPart(GramParser.VariableDeclarationPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variableDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationPart(GramParser.VariableDeclarationPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(GramParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(GramParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variableAccess}.
	 * @param ctx the parse tree
	 */
	void enterVariableAccess(GramParser.VariableAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variableAccess}.
	 * @param ctx the parse tree
	 */
	void exitVariableAccess(GramParser.VariableAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variableIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableIdentifier(GramParser.VariableIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variableIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableIdentifier(GramParser.VariableIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#indexedVariable}.
	 * @param ctx the parse tree
	 */
	void enterIndexedVariable(GramParser.IndexedVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#indexedVariable}.
	 * @param ctx the parse tree
	 */
	void exitIndexedVariable(GramParser.IndexedVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#fieldDesignator}.
	 * @param ctx the parse tree
	 */
	void enterFieldDesignator(GramParser.FieldDesignatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#fieldDesignator}.
	 * @param ctx the parse tree
	 */
	void exitFieldDesignator(GramParser.FieldDesignatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#pointerVariable}.
	 * @param ctx the parse tree
	 */
	void enterPointerVariable(GramParser.PointerVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#pointerVariable}.
	 * @param ctx the parse tree
	 */
	void exitPointerVariable(GramParser.PointerVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#procedureAndFunctionDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void enterProcedureAndFunctionDeclarationPart(GramParser.ProcedureAndFunctionDeclarationPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#procedureAndFunctionDeclarationPart}.
	 * @param ctx the parse tree
	 */
	void exitProcedureAndFunctionDeclarationPart(GramParser.ProcedureAndFunctionDeclarationPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDeclaration(GramParser.ProcedureDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDeclaration(GramParser.ProcedureDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#procedureHeading}.
	 * @param ctx the parse tree
	 */
	void enterProcedureHeading(GramParser.ProcedureHeadingContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#procedureHeading}.
	 * @param ctx the parse tree
	 */
	void exitProcedureHeading(GramParser.ProcedureHeadingContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#procedureIdentification}.
	 * @param ctx the parse tree
	 */
	void enterProcedureIdentification(GramParser.ProcedureIdentificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#procedureIdentification}.
	 * @param ctx the parse tree
	 */
	void exitProcedureIdentification(GramParser.ProcedureIdentificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#procedureIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterProcedureIdentifier(GramParser.ProcedureIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#procedureIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitProcedureIdentifier(GramParser.ProcedureIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#procedureBlock}.
	 * @param ctx the parse tree
	 */
	void enterProcedureBlock(GramParser.ProcedureBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#procedureBlock}.
	 * @param ctx the parse tree
	 */
	void exitProcedureBlock(GramParser.ProcedureBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(GramParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(GramParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#functionHeading}.
	 * @param ctx the parse tree
	 */
	void enterFunctionHeading(GramParser.FunctionHeadingContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#functionHeading}.
	 * @param ctx the parse tree
	 */
	void exitFunctionHeading(GramParser.FunctionHeadingContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#functionIdentification}.
	 * @param ctx the parse tree
	 */
	void enterFunctionIdentification(GramParser.FunctionIdentificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#functionIdentification}.
	 * @param ctx the parse tree
	 */
	void exitFunctionIdentification(GramParser.FunctionIdentificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#functionIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterFunctionIdentifier(GramParser.FunctionIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#functionIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitFunctionIdentifier(GramParser.FunctionIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#resultType}.
	 * @param ctx the parse tree
	 */
	void enterResultType(GramParser.ResultTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#resultType}.
	 * @param ctx the parse tree
	 */
	void exitResultType(GramParser.ResultTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#functionBlock}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBlock(GramParser.FunctionBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#functionBlock}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBlock(GramParser.FunctionBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(GramParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(GramParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#formalParameterSection}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterSection(GramParser.FormalParameterSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#formalParameterSection}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterSection(GramParser.FormalParameterSectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#valueParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterValueParameterSpecification(GramParser.ValueParameterSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#valueParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitValueParameterSpecification(GramParser.ValueParameterSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variableParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterVariableParameterSpecification(GramParser.VariableParameterSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variableParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitVariableParameterSpecification(GramParser.VariableParameterSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#proceduralParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterProceduralParameterSpecification(GramParser.ProceduralParameterSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#proceduralParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitProceduralParameterSpecification(GramParser.ProceduralParameterSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#functionalParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterFunctionalParameterSpecification(GramParser.FunctionalParameterSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#functionalParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitFunctionalParameterSpecification(GramParser.FunctionalParameterSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#conformantArrayParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterConformantArrayParameterSpecification(GramParser.ConformantArrayParameterSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#conformantArrayParameterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitConformantArrayParameterSpecification(GramParser.ConformantArrayParameterSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#valueConformantArraySpecification}.
	 * @param ctx the parse tree
	 */
	void enterValueConformantArraySpecification(GramParser.ValueConformantArraySpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#valueConformantArraySpecification}.
	 * @param ctx the parse tree
	 */
	void exitValueConformantArraySpecification(GramParser.ValueConformantArraySpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#variableConformantArraySpecification}.
	 * @param ctx the parse tree
	 */
	void enterVariableConformantArraySpecification(GramParser.VariableConformantArraySpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#variableConformantArraySpecification}.
	 * @param ctx the parse tree
	 */
	void exitVariableConformantArraySpecification(GramParser.VariableConformantArraySpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#conformantArraySchema}.
	 * @param ctx the parse tree
	 */
	void enterConformantArraySchema(GramParser.ConformantArraySchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#conformantArraySchema}.
	 * @param ctx the parse tree
	 */
	void exitConformantArraySchema(GramParser.ConformantArraySchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#packedConformantArraySchema}.
	 * @param ctx the parse tree
	 */
	void enterPackedConformantArraySchema(GramParser.PackedConformantArraySchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#packedConformantArraySchema}.
	 * @param ctx the parse tree
	 */
	void exitPackedConformantArraySchema(GramParser.PackedConformantArraySchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#unpackedConformantArraySchema}.
	 * @param ctx the parse tree
	 */
	void enterUnpackedConformantArraySchema(GramParser.UnpackedConformantArraySchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#unpackedConformantArraySchema}.
	 * @param ctx the parse tree
	 */
	void exitUnpackedConformantArraySchema(GramParser.UnpackedConformantArraySchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#indexTypeSpecification}.
	 * @param ctx the parse tree
	 */
	void enterIndexTypeSpecification(GramParser.IndexTypeSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#indexTypeSpecification}.
	 * @param ctx the parse tree
	 */
	void exitIndexTypeSpecification(GramParser.IndexTypeSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(GramParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(GramParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#boundIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterBoundIdentifier(GramParser.BoundIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#boundIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitBoundIdentifier(GramParser.BoundIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GramParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GramParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(GramParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(GramParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(GramParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(GramParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#unsignedConstant}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedConstant(GramParser.UnsignedConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#unsignedConstant}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedConstant(GramParser.UnsignedConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#setConstructor}.
	 * @param ctx the parse tree
	 */
	void enterSetConstructor(GramParser.SetConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#setConstructor}.
	 * @param ctx the parse tree
	 */
	void exitSetConstructor(GramParser.SetConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#memberDesignator}.
	 * @param ctx the parse tree
	 */
	void enterMemberDesignator(GramParser.MemberDesignatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#memberDesignator}.
	 * @param ctx the parse tree
	 */
	void exitMemberDesignator(GramParser.MemberDesignatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#multiplyingOperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyingOperator(GramParser.MultiplyingOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#multiplyingOperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyingOperator(GramParser.MultiplyingOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#addingOperator}.
	 * @param ctx the parse tree
	 */
	void enterAddingOperator(GramParser.AddingOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#addingOperator}.
	 * @param ctx the parse tree
	 */
	void exitAddingOperator(GramParser.AddingOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOperator(GramParser.RelationalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOperator(GramParser.RelationalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(GramParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(GramParser.BooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#functionDesignator}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDesignator(GramParser.FunctionDesignatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#functionDesignator}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDesignator(GramParser.FunctionDesignatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#actualParameterList}.
	 * @param ctx the parse tree
	 */
	void enterActualParameterList(GramParser.ActualParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#actualParameterList}.
	 * @param ctx the parse tree
	 */
	void exitActualParameterList(GramParser.ActualParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#actualParameter}.
	 * @param ctx the parse tree
	 */
	void enterActualParameter(GramParser.ActualParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#actualParameter}.
	 * @param ctx the parse tree
	 */
	void exitActualParameter(GramParser.ActualParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GramParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GramParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatement(GramParser.SimpleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatement(GramParser.SimpleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(GramParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(GramParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(GramParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(GramParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#procedureStatement}.
	 * @param ctx the parse tree
	 */
	void enterProcedureStatement(GramParser.ProcedureStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#procedureStatement}.
	 * @param ctx the parse tree
	 */
	void exitProcedureStatement(GramParser.ProcedureStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void enterGotoStatement(GramParser.GotoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void exitGotoStatement(GramParser.GotoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#structuredStatement}.
	 * @param ctx the parse tree
	 */
	void enterStructuredStatement(GramParser.StructuredStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#structuredStatement}.
	 * @param ctx the parse tree
	 */
	void exitStructuredStatement(GramParser.StructuredStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#statementSequence}.
	 * @param ctx the parse tree
	 */
	void enterStatementSequence(GramParser.StatementSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#statementSequence}.
	 * @param ctx the parse tree
	 */
	void exitStatementSequence(GramParser.StatementSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#statementPart}.
	 * @param ctx the parse tree
	 */
	void enterStatementPart(GramParser.StatementPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#statementPart}.
	 * @param ctx the parse tree
	 */
	void exitStatementPart(GramParser.StatementPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(GramParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(GramParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#conditionalStatement}.
	 * @param ctx the parse tree
	 */
	void enterConditionalStatement(GramParser.ConditionalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#conditionalStatement}.
	 * @param ctx the parse tree
	 */
	void exitConditionalStatement(GramParser.ConditionalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(GramParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(GramParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#elsePart}.
	 * @param ctx the parse tree
	 */
	void enterElsePart(GramParser.ElsePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#elsePart}.
	 * @param ctx the parse tree
	 */
	void exitElsePart(GramParser.ElsePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(GramParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(GramParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#caseListElement}.
	 * @param ctx the parse tree
	 */
	void enterCaseListElement(GramParser.CaseListElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#caseListElement}.
	 * @param ctx the parse tree
	 */
	void exitCaseListElement(GramParser.CaseListElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#caseIndex}.
	 * @param ctx the parse tree
	 */
	void enterCaseIndex(GramParser.CaseIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#caseIndex}.
	 * @param ctx the parse tree
	 */
	void exitCaseIndex(GramParser.CaseIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#repetitiveStatement}.
	 * @param ctx the parse tree
	 */
	void enterRepetitiveStatement(GramParser.RepetitiveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#repetitiveStatement}.
	 * @param ctx the parse tree
	 */
	void exitRepetitiveStatement(GramParser.RepetitiveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#repeatStatement}.
	 * @param ctx the parse tree
	 */
	void enterRepeatStatement(GramParser.RepeatStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#repeatStatement}.
	 * @param ctx the parse tree
	 */
	void exitRepeatStatement(GramParser.RepeatStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(GramParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(GramParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(GramParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(GramParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#controlVariable}.
	 * @param ctx the parse tree
	 */
	void enterControlVariable(GramParser.ControlVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#controlVariable}.
	 * @param ctx the parse tree
	 */
	void exitControlVariable(GramParser.ControlVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#initialValue}.
	 * @param ctx the parse tree
	 */
	void enterInitialValue(GramParser.InitialValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#initialValue}.
	 * @param ctx the parse tree
	 */
	void exitInitialValue(GramParser.InitialValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#finalValue}.
	 * @param ctx the parse tree
	 */
	void enterFinalValue(GramParser.FinalValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#finalValue}.
	 * @param ctx the parse tree
	 */
	void exitFinalValue(GramParser.FinalValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#withStatement}.
	 * @param ctx the parse tree
	 */
	void enterWithStatement(GramParser.WithStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#withStatement}.
	 * @param ctx the parse tree
	 */
	void exitWithStatement(GramParser.WithStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#recordVariableList}.
	 * @param ctx the parse tree
	 */
	void enterRecordVariableList(GramParser.RecordVariableListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#recordVariableList}.
	 * @param ctx the parse tree
	 */
	void exitRecordVariableList(GramParser.RecordVariableListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#fieldDesignatorIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterFieldDesignatorIdentifier(GramParser.FieldDesignatorIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#fieldDesignatorIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitFieldDesignatorIdentifier(GramParser.FieldDesignatorIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#readParameterList}.
	 * @param ctx the parse tree
	 */
	void enterReadParameterList(GramParser.ReadParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#readParameterList}.
	 * @param ctx the parse tree
	 */
	void exitReadParameterList(GramParser.ReadParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#readlnParameterList}.
	 * @param ctx the parse tree
	 */
	void enterReadlnParameterList(GramParser.ReadlnParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#readlnParameterList}.
	 * @param ctx the parse tree
	 */
	void exitReadlnParameterList(GramParser.ReadlnParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#writeParameterList}.
	 * @param ctx the parse tree
	 */
	void enterWriteParameterList(GramParser.WriteParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#writeParameterList}.
	 * @param ctx the parse tree
	 */
	void exitWriteParameterList(GramParser.WriteParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#writeParameter}.
	 * @param ctx the parse tree
	 */
	void enterWriteParameter(GramParser.WriteParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#writeParameter}.
	 * @param ctx the parse tree
	 */
	void exitWriteParameter(GramParser.WriteParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#writelnParameterList}.
	 * @param ctx the parse tree
	 */
	void enterWritelnParameterList(GramParser.WritelnParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#writelnParameterList}.
	 * @param ctx the parse tree
	 */
	void exitWritelnParameterList(GramParser.WritelnParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(GramParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(GramParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#programHeading}.
	 * @param ctx the parse tree
	 */
	void enterProgramHeading(GramParser.ProgramHeadingContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#programHeading}.
	 * @param ctx the parse tree
	 */
	void exitProgramHeading(GramParser.ProgramHeadingContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#programParameterList}.
	 * @param ctx the parse tree
	 */
	void enterProgramParameterList(GramParser.ProgramParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#programParameterList}.
	 * @param ctx the parse tree
	 */
	void exitProgramParameterList(GramParser.ProgramParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramParser#programBlock}.
	 * @param ctx the parse tree
	 */
	void enterProgramBlock(GramParser.ProgramBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramParser#programBlock}.
	 * @param ctx the parse tree
	 */
	void exitProgramBlock(GramParser.ProgramBlockContext ctx);
}