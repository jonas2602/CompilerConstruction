// Generated from D:/JetBrains/Projects/Intellij/CompilerConstruction/src\Pascal.g4 by ANTLR 4.7.2
package gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PascalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, ARRAY=2, BEGIN=3, BOOLEAN=4, CASE=5, CHAR=6, CONST=7, DIV=8, DO=9, 
		DOWNTO=10, ELSE=11, END=12, FILE=13, FOR=14, FUNCTION=15, GOTO=16, IF=17, 
		IN=18, INTEGER=19, LABEL=20, MOD=21, NIL=22, NOT=23, OF=24, OR=25, PROCEDURE=26, 
		PROGRAM=27, REAL=28, RECORD=29, REPEAT=30, SET=31, THEN=32, TO=33, TYPE=34, 
		UNTIL=35, VAR=36, WHILE=37, WITH=38, PLUS=39, MINUS=40, STAR=41, SLASH=42, 
		ASSIGN=43, COMMA=44, SEMI=45, COLON=46, EQUAL=47, NOT_EQUAL=48, LT=49, 
		LE=50, GE=51, GT=52, LPAREN=53, RPAREN=54, LBRACK=55, LBRACK2=56, RBRACK=57, 
		RBRACK2=58, POINTER=59, AT=60, DOT=61, DOTDOT=62, LCURLY=63, RCURLY=64, 
		STRING=65, TRUE=66, FALSE=67, WS=68, COMMENT_1=69, COMMENT_2=70, IDENT=71, 
		STRING_LITERAL=72, NUM_INT=73, NUM_REAL=74;
	public static final int
		RULE_program = 0, RULE_programHeading = 1, RULE_identifier = 2, RULE_block = 3, 
		RULE_labelDeclarationPart = 4, RULE_label = 5, RULE_constantDefinitionPart = 6, 
		RULE_constantDefinition = 7, RULE_constant = 8, RULE_unsignedNumber = 9, 
		RULE_unsignedInteger = 10, RULE_unsignedReal = 11, RULE_sign = 12, RULE_bool = 13, 
		RULE_string = 14, RULE_typeDefinitionPart = 15, RULE_typeDefinition = 16, 
		RULE_functionType = 17, RULE_procedureType = 18, RULE_type = 19, RULE_simpleType = 20, 
		RULE_scalarType = 21, RULE_subrangeType = 22, RULE_typeIdentifier = 23, 
		RULE_structuredType = 24, RULE_stringtype = 25, RULE_arrayType = 26, RULE_typeList = 27, 
		RULE_recordType = 28, RULE_fieldList = 29, RULE_fixedPart = 30, RULE_recordSection = 31, 
		RULE_variantPart = 32, RULE_tag = 33, RULE_variant = 34, RULE_setType = 35, 
		RULE_fileType = 36, RULE_pointerType = 37, RULE_variableDeclarationPart = 38, 
		RULE_variableDeclaration = 39, RULE_procedureAndFunctionDeclarationPart = 40, 
		RULE_procedureOrFunctionDeclaration = 41, RULE_procedureDeclaration = 42, 
		RULE_formalParameterList = 43, RULE_formalParameterSection = 44, RULE_parameterGroup = 45, 
		RULE_identifierList = 46, RULE_constList = 47, RULE_functionDeclaration = 48, 
		RULE_resultType = 49, RULE_statement = 50, RULE_unlabelledStatement = 51, 
		RULE_simpleStatement = 52, RULE_assignmentStatement = 53, RULE_variable = 54, 
		RULE_variableAccess = 55, RULE_indexedVariable = 56, RULE_fieldDesignator = 57, 
		RULE_pointerVariable = 58, RULE_expression = 59, RULE_relationaloperator = 60, 
		RULE_simpleExpression = 61, RULE_additiveoperator = 62, RULE_term = 63, 
		RULE_multiplicativeoperator = 64, RULE_signedFactor = 65, RULE_factor = 66, 
		RULE_unsignedConstant = 67, RULE_functionDesignator = 68, RULE_parameterList = 69, 
		RULE_set = 70, RULE_elementList = 71, RULE_element = 72, RULE_procedureStatement = 73, 
		RULE_actualParameter = 74, RULE_parameterwidth = 75, RULE_gotoStatement = 76, 
		RULE_emptyStatement = 77, RULE_structuredStatement = 78, RULE_compoundStatement = 79, 
		RULE_statements = 80, RULE_conditionalStatement = 81, RULE_ifStatement = 82, 
		RULE_caseStatement = 83, RULE_caseListElement = 84, RULE_repetetiveStatement = 85, 
		RULE_whileStatement = 86, RULE_repeatStatement = 87, RULE_forStatement = 88, 
		RULE_initialValue = 89, RULE_finalValue = 90, RULE_withStatement = 91, 
		RULE_recordVariableList = 92;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "programHeading", "identifier", "block", "labelDeclarationPart", 
			"label", "constantDefinitionPart", "constantDefinition", "constant", 
			"unsignedNumber", "unsignedInteger", "unsignedReal", "sign", "bool", 
			"string", "typeDefinitionPart", "typeDefinition", "functionType", "procedureType", 
			"type", "simpleType", "scalarType", "subrangeType", "typeIdentifier", 
			"structuredType", "stringtype", "arrayType", "typeList", "recordType", 
			"fieldList", "fixedPart", "recordSection", "variantPart", "tag", "variant", 
			"setType", "fileType", "pointerType", "variableDeclarationPart", "variableDeclaration", 
			"procedureAndFunctionDeclarationPart", "procedureOrFunctionDeclaration", 
			"procedureDeclaration", "formalParameterList", "formalParameterSection", 
			"parameterGroup", "identifierList", "constList", "functionDeclaration", 
			"resultType", "statement", "unlabelledStatement", "simpleStatement", 
			"assignmentStatement", "variable", "variableAccess", "indexedVariable", 
			"fieldDesignator", "pointerVariable", "expression", "relationaloperator", 
			"simpleExpression", "additiveoperator", "term", "multiplicativeoperator", 
			"signedFactor", "factor", "unsignedConstant", "functionDesignator", "parameterList", 
			"set", "elementList", "element", "procedureStatement", "actualParameter", 
			"parameterwidth", "gotoStatement", "emptyStatement", "structuredStatement", 
			"compoundStatement", "statements", "conditionalStatement", "ifStatement", 
			"caseStatement", "caseListElement", "repetetiveStatement", "whileStatement", 
			"repeatStatement", "forStatement", "initialValue", "finalValue", "withStatement", 
			"recordVariableList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "'+'", "'-'", "'*'", "'/'", "':='", "','", "';'", "':'", 
			"'='", "'<>'", "'<'", "'<='", "'>='", "'>'", "'('", "')'", "'['", "'(.'", 
			"']'", "'.)'", "'^'", "'@'", "'.'", "'..'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "ARRAY", "BEGIN", "BOOLEAN", "CASE", "CHAR", "CONST", "DIV", 
			"DO", "DOWNTO", "ELSE", "END", "FILE", "FOR", "FUNCTION", "GOTO", "IF", 
			"IN", "INTEGER", "LABEL", "MOD", "NIL", "NOT", "OF", "OR", "PROCEDURE", 
			"PROGRAM", "REAL", "RECORD", "REPEAT", "SET", "THEN", "TO", "TYPE", "UNTIL", 
			"VAR", "WHILE", "WITH", "PLUS", "MINUS", "STAR", "SLASH", "ASSIGN", "COMMA", 
			"SEMI", "COLON", "EQUAL", "NOT_EQUAL", "LT", "LE", "GE", "GT", "LPAREN", 
			"RPAREN", "LBRACK", "LBRACK2", "RBRACK", "RBRACK2", "POINTER", "AT", 
			"DOT", "DOTDOT", "LCURLY", "RCURLY", "STRING", "TRUE", "FALSE", "WS", 
			"COMMENT_1", "COMMENT_2", "IDENT", "STRING_LITERAL", "NUM_INT", "NUM_REAL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Pascal.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PascalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public ProgramHeadingContext programHeading() {
			return getRuleContext(ProgramHeadingContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode DOT() { return getToken(PascalParser.DOT, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			programHeading();
			setState(187);
			block();
			setState(188);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramHeadingContext extends ParserRuleContext {
		public TerminalNode PROGRAM() { return getToken(PascalParser.PROGRAM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(PascalParser.SEMI, 0); }
		public TerminalNode LPAREN() { return getToken(PascalParser.LPAREN, 0); }
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PascalParser.RPAREN, 0); }
		public ProgramHeadingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programHeading; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitProgramHeading(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramHeadingContext programHeading() throws RecognitionException {
		ProgramHeadingContext _localctx = new ProgramHeadingContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_programHeading);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(PROGRAM);
			setState(191);
			identifier();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(192);
				match(LPAREN);
				setState(193);
				identifierList();
				setState(194);
				match(RPAREN);
				}
			}

			setState(198);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(PascalParser.IDENT, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public List<LabelDeclarationPartContext> labelDeclarationPart() {
			return getRuleContexts(LabelDeclarationPartContext.class);
		}
		public LabelDeclarationPartContext labelDeclarationPart(int i) {
			return getRuleContext(LabelDeclarationPartContext.class,i);
		}
		public List<ConstantDefinitionPartContext> constantDefinitionPart() {
			return getRuleContexts(ConstantDefinitionPartContext.class);
		}
		public ConstantDefinitionPartContext constantDefinitionPart(int i) {
			return getRuleContext(ConstantDefinitionPartContext.class,i);
		}
		public List<TypeDefinitionPartContext> typeDefinitionPart() {
			return getRuleContexts(TypeDefinitionPartContext.class);
		}
		public TypeDefinitionPartContext typeDefinitionPart(int i) {
			return getRuleContext(TypeDefinitionPartContext.class,i);
		}
		public List<VariableDeclarationPartContext> variableDeclarationPart() {
			return getRuleContexts(VariableDeclarationPartContext.class);
		}
		public VariableDeclarationPartContext variableDeclarationPart(int i) {
			return getRuleContext(VariableDeclarationPartContext.class,i);
		}
		public List<ProcedureAndFunctionDeclarationPartContext> procedureAndFunctionDeclarationPart() {
			return getRuleContexts(ProcedureAndFunctionDeclarationPartContext.class);
		}
		public ProcedureAndFunctionDeclarationPartContext procedureAndFunctionDeclarationPart(int i) {
			return getRuleContext(ProcedureAndFunctionDeclarationPartContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONST) | (1L << FUNCTION) | (1L << LABEL) | (1L << PROCEDURE) | (1L << TYPE) | (1L << VAR))) != 0)) {
				{
				setState(207);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LABEL:
					{
					setState(202);
					labelDeclarationPart();
					}
					break;
				case CONST:
					{
					setState(203);
					constantDefinitionPart();
					}
					break;
				case TYPE:
					{
					setState(204);
					typeDefinitionPart();
					}
					break;
				case VAR:
					{
					setState(205);
					variableDeclarationPart();
					}
					break;
				case FUNCTION:
				case PROCEDURE:
					{
					setState(206);
					procedureAndFunctionDeclarationPart();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(212);
			compoundStatement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelDeclarationPartContext extends ParserRuleContext {
		public TerminalNode LABEL() { return getToken(PascalParser.LABEL, 0); }
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(PascalParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PascalParser.COMMA, i);
		}
		public LabelDeclarationPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelDeclarationPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitLabelDeclarationPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelDeclarationPartContext labelDeclarationPart() throws RecognitionException {
		LabelDeclarationPartContext _localctx = new LabelDeclarationPartContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_labelDeclarationPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(LABEL);
			setState(215);
			label();
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(216);
				match(COMMA);
				setState(217);
				label();
				}
				}
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(223);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public UnsignedIntegerContext unsignedInteger() {
			return getRuleContext(UnsignedIntegerContext.class,0);
		}
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			unsignedInteger();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantDefinitionPartContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(PascalParser.CONST, 0); }
		public List<ConstantDefinitionContext> constantDefinition() {
			return getRuleContexts(ConstantDefinitionContext.class);
		}
		public ConstantDefinitionContext constantDefinition(int i) {
			return getRuleContext(ConstantDefinitionContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public ConstantDefinitionPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDefinitionPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitConstantDefinitionPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantDefinitionPartContext constantDefinitionPart() throws RecognitionException {
		ConstantDefinitionPartContext _localctx = new ConstantDefinitionPartContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constantDefinitionPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(CONST);
			setState(231); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(228);
				constantDefinition();
				setState(229);
				match(SEMI);
				}
				}
				setState(233); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantDefinitionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(PascalParser.EQUAL, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitConstantDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantDefinitionContext constantDefinition() throws RecognitionException {
		ConstantDefinitionContext _localctx = new ConstantDefinitionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constantDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			identifier();
			setState(236);
			match(EQUAL);
			setState(237);
			constant();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public UnsignedNumberContext unsignedNumber() {
			return getRuleContext(UnsignedNumberContext.class,0);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_constant);
		try {
			setState(248);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				unsignedNumber();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				sign();
				setState(241);
				unsignedNumber();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(243);
				identifier();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(244);
				sign();
				setState(245);
				identifier();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(247);
				string();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnsignedNumberContext extends ParserRuleContext {
		public UnsignedIntegerContext unsignedInteger() {
			return getRuleContext(UnsignedIntegerContext.class,0);
		}
		public UnsignedRealContext unsignedReal() {
			return getRuleContext(UnsignedRealContext.class,0);
		}
		public UnsignedNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedNumber; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitUnsignedNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedNumberContext unsignedNumber() throws RecognitionException {
		UnsignedNumberContext _localctx = new UnsignedNumberContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_unsignedNumber);
		try {
			setState(252);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				unsignedInteger();
				}
				break;
			case NUM_REAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				unsignedReal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnsignedIntegerContext extends ParserRuleContext {
		public TerminalNode NUM_INT() { return getToken(PascalParser.NUM_INT, 0); }
		public UnsignedIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedInteger; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitUnsignedInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedIntegerContext unsignedInteger() throws RecognitionException {
		UnsignedIntegerContext _localctx = new UnsignedIntegerContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_unsignedInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(NUM_INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnsignedRealContext extends ParserRuleContext {
		public TerminalNode NUM_REAL() { return getToken(PascalParser.NUM_REAL, 0); }
		public UnsignedRealContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedReal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitUnsignedReal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedRealContext unsignedReal() throws RecognitionException {
		UnsignedRealContext _localctx = new UnsignedRealContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_unsignedReal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(NUM_REAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(PascalParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PascalParser.MINUS, 0); }
		public SignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignContext sign() throws RecognitionException {
		SignContext _localctx = new SignContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(PascalParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(PascalParser.FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(PascalParser.STRING_LITERAL, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDefinitionPartContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(PascalParser.TYPE, 0); }
		public List<TypeDefinitionContext> typeDefinition() {
			return getRuleContexts(TypeDefinitionContext.class);
		}
		public TypeDefinitionContext typeDefinition(int i) {
			return getRuleContext(TypeDefinitionContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public TypeDefinitionPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefinitionPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitTypeDefinitionPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefinitionPartContext typeDefinitionPart() throws RecognitionException {
		TypeDefinitionPartContext _localctx = new TypeDefinitionPartContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_typeDefinitionPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(TYPE);
			setState(268); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(265);
				typeDefinition();
				setState(266);
				match(SEMI);
				}
				}
				setState(270); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDefinitionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(PascalParser.EQUAL, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public ProcedureTypeContext procedureType() {
			return getRuleContext(ProcedureTypeContext.class,0);
		}
		public TypeDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitTypeDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefinitionContext typeDefinition() throws RecognitionException {
		TypeDefinitionContext _localctx = new TypeDefinitionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_typeDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			identifier();
			setState(273);
			match(EQUAL);
			setState(277);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARRAY:
			case BOOLEAN:
			case CHAR:
			case FILE:
			case INTEGER:
			case REAL:
			case RECORD:
			case SET:
			case PLUS:
			case MINUS:
			case LPAREN:
			case POINTER:
			case STRING:
			case IDENT:
			case STRING_LITERAL:
			case NUM_INT:
			case NUM_REAL:
				{
				setState(274);
				type();
				}
				break;
			case FUNCTION:
				{
				setState(275);
				functionType();
				}
				break;
			case PROCEDURE:
				{
				setState(276);
				procedureType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionTypeContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(PascalParser.FUNCTION, 0); }
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public ResultTypeContext resultType() {
			return getRuleContext(ResultTypeContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFunctionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functionType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(FUNCTION);
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(280);
				formalParameterList();
				}
			}

			setState(283);
			match(COLON);
			setState(284);
			resultType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureTypeContext extends ParserRuleContext {
		public TerminalNode PROCEDURE() { return getToken(PascalParser.PROCEDURE, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public ProcedureTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitProcedureType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureTypeContext procedureType() throws RecognitionException {
		ProcedureTypeContext _localctx = new ProcedureTypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_procedureType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(PROCEDURE);
			setState(288);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(287);
				formalParameterList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public StructuredTypeContext structuredType() {
			return getRuleContext(StructuredTypeContext.class,0);
		}
		public PointerTypeContext pointerType() {
			return getRuleContext(PointerTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_type);
		try {
			setState(293);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case CHAR:
			case INTEGER:
			case REAL:
			case PLUS:
			case MINUS:
			case LPAREN:
			case STRING:
			case IDENT:
			case STRING_LITERAL:
			case NUM_INT:
			case NUM_REAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				simpleType();
				}
				break;
			case ARRAY:
			case FILE:
			case RECORD:
			case SET:
				enterOuterAlt(_localctx, 2);
				{
				setState(291);
				structuredType();
				}
				break;
			case POINTER:
				enterOuterAlt(_localctx, 3);
				{
				setState(292);
				pointerType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleTypeContext extends ParserRuleContext {
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public SubrangeTypeContext subrangeType() {
			return getRuleContext(SubrangeTypeContext.class,0);
		}
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public StringtypeContext stringtype() {
			return getRuleContext(StringtypeContext.class,0);
		}
		public SimpleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_simpleType);
		try {
			setState(299);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				subrangeType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(297);
				typeIdentifier();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(298);
				stringtype();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScalarTypeContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PascalParser.LPAREN, 0); }
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PascalParser.RPAREN, 0); }
		public ScalarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitScalarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarTypeContext scalarType() throws RecognitionException {
		ScalarTypeContext _localctx = new ScalarTypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_scalarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(LPAREN);
			setState(302);
			identifierList();
			setState(303);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubrangeTypeContext extends ParserRuleContext {
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TerminalNode DOTDOT() { return getToken(PascalParser.DOTDOT, 0); }
		public SubrangeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subrangeType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitSubrangeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubrangeTypeContext subrangeType() throws RecognitionException {
		SubrangeTypeContext _localctx = new SubrangeTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_subrangeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			constant();
			setState(306);
			match(DOTDOT);
			setState(307);
			constant();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeIdentifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode CHAR() { return getToken(PascalParser.CHAR, 0); }
		public TerminalNode BOOLEAN() { return getToken(PascalParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(PascalParser.INTEGER, 0); }
		public TerminalNode REAL() { return getToken(PascalParser.REAL, 0); }
		public TerminalNode STRING() { return getToken(PascalParser.STRING, 0); }
		public TypeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeIdentifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitTypeIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeIdentifierContext typeIdentifier() throws RecognitionException {
		TypeIdentifierContext _localctx = new TypeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_typeIdentifier);
		int _la;
		try {
			setState(311);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				identifier();
				}
				break;
			case BOOLEAN:
			case CHAR:
			case INTEGER:
			case REAL:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(310);
				_la = _input.LA(1);
				if ( !(((((_la - 4)) & ~0x3f) == 0 && ((1L << (_la - 4)) & ((1L << (BOOLEAN - 4)) | (1L << (CHAR - 4)) | (1L << (INTEGER - 4)) | (1L << (REAL - 4)) | (1L << (STRING - 4)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructuredTypeContext extends ParserRuleContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public RecordTypeContext recordType() {
			return getRuleContext(RecordTypeContext.class,0);
		}
		public SetTypeContext setType() {
			return getRuleContext(SetTypeContext.class,0);
		}
		public FileTypeContext fileType() {
			return getRuleContext(FileTypeContext.class,0);
		}
		public StructuredTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structuredType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitStructuredType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructuredTypeContext structuredType() throws RecognitionException {
		StructuredTypeContext _localctx = new StructuredTypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_structuredType);
		try {
			setState(317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARRAY:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				arrayType();
				}
				break;
			case RECORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(314);
				recordType();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 3);
				{
				setState(315);
				setType();
				}
				break;
			case FILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(316);
				fileType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringtypeContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PascalParser.STRING, 0); }
		public TerminalNode LBRACK() { return getToken(PascalParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(PascalParser.RBRACK, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode NUM_INT() { return getToken(PascalParser.NUM_INT, 0); }
		public StringtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringtype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitStringtype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringtypeContext stringtype() throws RecognitionException {
		StringtypeContext _localctx = new StringtypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_stringtype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(STRING);
			setState(320);
			match(LBRACK);
			setState(323);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				{
				setState(321);
				identifier();
				}
				break;
			case NUM_INT:
				{
				setState(322);
				match(NUM_INT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(325);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public TerminalNode ARRAY() { return getToken(PascalParser.ARRAY, 0); }
		public TerminalNode LBRACK() { return getToken(PascalParser.LBRACK, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(PascalParser.RBRACK, 0); }
		public TerminalNode OF() { return getToken(PascalParser.OF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LBRACK2() { return getToken(PascalParser.LBRACK2, 0); }
		public TerminalNode RBRACK2() { return getToken(PascalParser.RBRACK2, 0); }
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_arrayType);
		try {
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(327);
				match(ARRAY);
				setState(328);
				match(LBRACK);
				setState(329);
				typeList();
				setState(330);
				match(RBRACK);
				setState(331);
				match(OF);
				setState(332);
				type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				match(ARRAY);
				setState(335);
				match(LBRACK2);
				setState(336);
				typeList();
				setState(337);
				match(RBRACK2);
				setState(338);
				match(OF);
				setState(339);
				type();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeListContext extends ParserRuleContext {
		public List<SimpleTypeContext> simpleType() {
			return getRuleContexts(SimpleTypeContext.class);
		}
		public SimpleTypeContext simpleType(int i) {
			return getRuleContext(SimpleTypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PascalParser.COMMA, i);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitTypeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			simpleType();
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(344);
				match(COMMA);
				setState(345);
				simpleType();
				}
				}
				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecordTypeContext extends ParserRuleContext {
		public TerminalNode RECORD() { return getToken(PascalParser.RECORD, 0); }
		public TerminalNode END() { return getToken(PascalParser.END, 0); }
		public List<RecordSectionContext> recordSection() {
			return getRuleContexts(RecordSectionContext.class);
		}
		public RecordSectionContext recordSection(int i) {
			return getRuleContext(RecordSectionContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public RecordTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitRecordType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordTypeContext recordType() throws RecognitionException {
		RecordTypeContext _localctx = new RecordTypeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_recordType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			match(RECORD);
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENT) {
				{
				setState(352);
				recordSection();
				setState(357);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(353);
						match(SEMI);
						setState(354);
						recordSection();
						}
						} 
					}
					setState(359);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				}
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMI) {
					{
					setState(360);
					match(SEMI);
					}
				}

				}
			}

			setState(365);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldListContext extends ParserRuleContext {
		public FixedPartContext fixedPart() {
			return getRuleContext(FixedPartContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(PascalParser.SEMI, 0); }
		public VariantPartContext variantPart() {
			return getRuleContext(VariantPartContext.class,0);
		}
		public FieldListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFieldList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldListContext fieldList() throws RecognitionException {
		FieldListContext _localctx = new FieldListContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_fieldList);
		int _la;
		try {
			setState(373);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(367);
				fixedPart();
				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMI) {
					{
					setState(368);
					match(SEMI);
					setState(369);
					variantPart();
					}
				}

				}
				break;
			case CASE:
				enterOuterAlt(_localctx, 2);
				{
				setState(372);
				variantPart();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FixedPartContext extends ParserRuleContext {
		public List<RecordSectionContext> recordSection() {
			return getRuleContexts(RecordSectionContext.class);
		}
		public RecordSectionContext recordSection(int i) {
			return getRuleContext(RecordSectionContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public FixedPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixedPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFixedPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FixedPartContext fixedPart() throws RecognitionException {
		FixedPartContext _localctx = new FixedPartContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_fixedPart);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			recordSection();
			setState(380);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(376);
					match(SEMI);
					setState(377);
					recordSection();
					}
					} 
				}
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecordSectionContext extends ParserRuleContext {
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public RecordSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordSection; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitRecordSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordSectionContext recordSection() throws RecognitionException {
		RecordSectionContext _localctx = new RecordSectionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_recordSection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			identifierList();
			setState(384);
			match(COLON);
			setState(385);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariantPartContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(PascalParser.CASE, 0); }
		public TagContext tag() {
			return getRuleContext(TagContext.class,0);
		}
		public TerminalNode OF() { return getToken(PascalParser.OF, 0); }
		public List<VariantContext> variant() {
			return getRuleContexts(VariantContext.class);
		}
		public VariantContext variant(int i) {
			return getRuleContext(VariantContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public VariantPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variantPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitVariantPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariantPartContext variantPart() throws RecognitionException {
		VariantPartContext _localctx = new VariantPartContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_variantPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(CASE);
			setState(388);
			tag();
			setState(389);
			match(OF);
			setState(390);
			variant();
			setState(395);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(391);
				match(SEMI);
				setState(392);
				variant();
				}
				}
				setState(397);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public TagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tag; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagContext tag() throws RecognitionException {
		TagContext _localctx = new TagContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_tag);
		try {
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(398);
				identifier();
				setState(399);
				match(COLON);
				setState(400);
				typeIdentifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(402);
				typeIdentifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariantContext extends ParserRuleContext {
		public ConstListContext constList() {
			return getRuleContext(ConstListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public TerminalNode LPAREN() { return getToken(PascalParser.LPAREN, 0); }
		public FieldListContext fieldList() {
			return getRuleContext(FieldListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PascalParser.RPAREN, 0); }
		public VariantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitVariant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariantContext variant() throws RecognitionException {
		VariantContext _localctx = new VariantContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_variant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			constList();
			setState(406);
			match(COLON);
			setState(407);
			match(LPAREN);
			setState(408);
			fieldList();
			setState(409);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetTypeContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(PascalParser.SET, 0); }
		public TerminalNode OF() { return getToken(PascalParser.OF, 0); }
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public SetTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitSetType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetTypeContext setType() throws RecognitionException {
		SetTypeContext _localctx = new SetTypeContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_setType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			match(SET);
			setState(412);
			match(OF);
			setState(413);
			simpleType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FileTypeContext extends ParserRuleContext {
		public TerminalNode FILE() { return getToken(PascalParser.FILE, 0); }
		public TerminalNode OF() { return getToken(PascalParser.OF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FileTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fileType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFileType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileTypeContext fileType() throws RecognitionException {
		FileTypeContext _localctx = new FileTypeContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_fileType);
		try {
			setState(419);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(415);
				match(FILE);
				setState(416);
				match(OF);
				setState(417);
				type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(418);
				match(FILE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerTypeContext extends ParserRuleContext {
		public TerminalNode POINTER() { return getToken(PascalParser.POINTER, 0); }
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public PointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitPointerType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointerTypeContext pointerType() throws RecognitionException {
		PointerTypeContext _localctx = new PointerTypeContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_pointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(POINTER);
			setState(422);
			typeIdentifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationPartContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(PascalParser.VAR, 0); }
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public VariableDeclarationPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitVariableDeclarationPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationPartContext variableDeclarationPart() throws RecognitionException {
		VariableDeclarationPartContext _localctx = new VariableDeclarationPartContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_variableDeclarationPart);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
			match(VAR);
			setState(425);
			variableDeclaration();
			setState(430);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(426);
					match(SEMI);
					setState(427);
					variableDeclaration();
					}
					} 
				}
				setState(432);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(433);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			identifierList();
			setState(436);
			match(COLON);
			setState(437);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureAndFunctionDeclarationPartContext extends ParserRuleContext {
		public ProcedureOrFunctionDeclarationContext procedureOrFunctionDeclaration() {
			return getRuleContext(ProcedureOrFunctionDeclarationContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(PascalParser.SEMI, 0); }
		public ProcedureAndFunctionDeclarationPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureAndFunctionDeclarationPart; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitProcedureAndFunctionDeclarationPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureAndFunctionDeclarationPartContext procedureAndFunctionDeclarationPart() throws RecognitionException {
		ProcedureAndFunctionDeclarationPartContext _localctx = new ProcedureAndFunctionDeclarationPartContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_procedureAndFunctionDeclarationPart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			procedureOrFunctionDeclaration();
			setState(440);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureOrFunctionDeclarationContext extends ParserRuleContext {
		public ProcedureDeclarationContext procedureDeclaration() {
			return getRuleContext(ProcedureDeclarationContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ProcedureOrFunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureOrFunctionDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitProcedureOrFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureOrFunctionDeclarationContext procedureOrFunctionDeclaration() throws RecognitionException {
		ProcedureOrFunctionDeclarationContext _localctx = new ProcedureOrFunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_procedureOrFunctionDeclaration);
		try {
			setState(444);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PROCEDURE:
				enterOuterAlt(_localctx, 1);
				{
				setState(442);
				procedureDeclaration();
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(443);
				functionDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureDeclarationContext extends ParserRuleContext {
		public TerminalNode PROCEDURE() { return getToken(PascalParser.PROCEDURE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(PascalParser.SEMI, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public ProcedureDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitProcedureDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureDeclarationContext procedureDeclaration() throws RecognitionException {
		ProcedureDeclarationContext _localctx = new ProcedureDeclarationContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_procedureDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			match(PROCEDURE);
			setState(447);
			identifier();
			setState(449);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(448);
				formalParameterList();
				}
			}

			setState(451);
			match(SEMI);
			setState(452);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterListContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PascalParser.LPAREN, 0); }
		public List<FormalParameterSectionContext> formalParameterSection() {
			return getRuleContexts(FormalParameterSectionContext.class);
		}
		public FormalParameterSectionContext formalParameterSection(int i) {
			return getRuleContext(FormalParameterSectionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PascalParser.RPAREN, 0); }
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFormalParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_formalParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			match(LPAREN);
			setState(455);
			formalParameterSection();
			setState(460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(456);
				match(SEMI);
				setState(457);
				formalParameterSection();
				}
				}
				setState(462);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(463);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterSectionContext extends ParserRuleContext {
		public ParameterGroupContext parameterGroup() {
			return getRuleContext(ParameterGroupContext.class,0);
		}
		public TerminalNode VAR() { return getToken(PascalParser.VAR, 0); }
		public TerminalNode FUNCTION() { return getToken(PascalParser.FUNCTION, 0); }
		public TerminalNode PROCEDURE() { return getToken(PascalParser.PROCEDURE, 0); }
		public FormalParameterSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterSection; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFormalParameterSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterSectionContext formalParameterSection() throws RecognitionException {
		FormalParameterSectionContext _localctx = new FormalParameterSectionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_formalParameterSection);
		try {
			setState(472);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(465);
				parameterGroup();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(466);
				match(VAR);
				setState(467);
				parameterGroup();
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(468);
				match(FUNCTION);
				setState(469);
				parameterGroup();
				}
				break;
			case PROCEDURE:
				enterOuterAlt(_localctx, 4);
				{
				setState(470);
				match(PROCEDURE);
				setState(471);
				parameterGroup();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterGroupContext extends ParserRuleContext {
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParameterGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterGroup; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitParameterGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterGroupContext parameterGroup() throws RecognitionException {
		ParameterGroupContext _localctx = new ParameterGroupContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_parameterGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			identifierList();
			setState(475);
			match(COLON);
			setState(476);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierListContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PascalParser.COMMA, i);
		}
		public IdentifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitIdentifierList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_identifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
			identifier();
			setState(483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(479);
				match(COMMA);
				setState(480);
				identifier();
				}
				}
				setState(485);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstListContext extends ParserRuleContext {
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PascalParser.COMMA, i);
		}
		public ConstListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitConstList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstListContext constList() throws RecognitionException {
		ConstListContext _localctx = new ConstListContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_constList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			constant();
			setState(491);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(487);
				match(COMMA);
				setState(488);
				constant();
				}
				}
				setState(493);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(PascalParser.FUNCTION, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public ResultTypeContext resultType() {
			return getRuleContext(ResultTypeContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(PascalParser.SEMI, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			match(FUNCTION);
			setState(495);
			identifier();
			setState(497);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(496);
				formalParameterList();
				}
			}

			setState(499);
			match(COLON);
			setState(500);
			resultType();
			setState(501);
			match(SEMI);
			setState(502);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResultTypeContext extends ParserRuleContext {
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public ResultTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resultType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitResultType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResultTypeContext resultType() throws RecognitionException {
		ResultTypeContext _localctx = new ResultTypeContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_resultType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
			typeIdentifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public UnlabelledStatementContext unlabelledStatement() {
			return getRuleContext(UnlabelledStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_statement);
		try {
			setState(511);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(506);
				label();
				setState(507);
				match(COLON);
				setState(508);
				unlabelledStatement();
				}
				break;
			case BEGIN:
			case CASE:
			case ELSE:
			case END:
			case FOR:
			case GOTO:
			case IF:
			case REPEAT:
			case UNTIL:
			case WHILE:
			case WITH:
			case SEMI:
			case AT:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(510);
				unlabelledStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnlabelledStatementContext extends ParserRuleContext {
		public SimpleStatementContext simpleStatement() {
			return getRuleContext(SimpleStatementContext.class,0);
		}
		public StructuredStatementContext structuredStatement() {
			return getRuleContext(StructuredStatementContext.class,0);
		}
		public UnlabelledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unlabelledStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitUnlabelledStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnlabelledStatementContext unlabelledStatement() throws RecognitionException {
		UnlabelledStatementContext _localctx = new UnlabelledStatementContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_unlabelledStatement);
		try {
			setState(515);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ELSE:
			case END:
			case GOTO:
			case UNTIL:
			case SEMI:
			case AT:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(513);
				simpleStatement();
				}
				break;
			case BEGIN:
			case CASE:
			case FOR:
			case IF:
			case REPEAT:
			case WHILE:
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(514);
				structuredStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleStatementContext extends ParserRuleContext {
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public ProcedureStatementContext procedureStatement() {
			return getRuleContext(ProcedureStatementContext.class,0);
		}
		public GotoStatementContext gotoStatement() {
			return getRuleContext(GotoStatementContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public SimpleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitSimpleStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleStatementContext simpleStatement() throws RecognitionException {
		SimpleStatementContext _localctx = new SimpleStatementContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_simpleStatement);
		try {
			setState(521);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(517);
				assignmentStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(518);
				procedureStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(519);
				gotoStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(520);
				emptyStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentStatementContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(PascalParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			variable();
			setState(524);
			match(ASSIGN);
			setState(525);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(PascalParser.AT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<VariableAccessContext> variableAccess() {
			return getRuleContexts(VariableAccessContext.class);
		}
		public VariableAccessContext variableAccess(int i) {
			return getRuleContext(VariableAccessContext.class,i);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT:
				{
				setState(527);
				match(AT);
				setState(528);
				identifier();
				}
				break;
			case IDENT:
				{
				setState(529);
				identifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(535);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LBRACK) | (1L << POINTER) | (1L << DOT))) != 0)) {
				{
				{
				setState(532);
				variableAccess();
				}
				}
				setState(537);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableAccessContext extends ParserRuleContext {
		public IndexedVariableContext indexedVariable() {
			return getRuleContext(IndexedVariableContext.class,0);
		}
		public FieldDesignatorContext fieldDesignator() {
			return getRuleContext(FieldDesignatorContext.class,0);
		}
		public PointerVariableContext pointerVariable() {
			return getRuleContext(PointerVariableContext.class,0);
		}
		public VariableAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableAccess; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitVariableAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableAccessContext variableAccess() throws RecognitionException {
		VariableAccessContext _localctx = new VariableAccessContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_variableAccess);
		try {
			setState(541);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(538);
				indexedVariable();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(539);
				fieldDesignator();
				}
				break;
			case POINTER:
				enterOuterAlt(_localctx, 3);
				{
				setState(540);
				pointerVariable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexedVariableContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(PascalParser.LBRACK, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RBRACK() { return getToken(PascalParser.RBRACK, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PascalParser.COMMA, i);
		}
		public IndexedVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedVariable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitIndexedVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexedVariableContext indexedVariable() throws RecognitionException {
		IndexedVariableContext _localctx = new IndexedVariableContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_indexedVariable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			match(LBRACK);
			setState(544);
			expression();
			setState(549);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(545);
				match(COMMA);
				setState(546);
				expression();
				}
				}
				setState(551);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(552);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDesignatorContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(PascalParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FieldDesignatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDesignator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFieldDesignator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDesignatorContext fieldDesignator() throws RecognitionException {
		FieldDesignatorContext _localctx = new FieldDesignatorContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_fieldDesignator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(554);
			match(DOT);
			setState(555);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointerVariableContext extends ParserRuleContext {
		public TerminalNode POINTER() { return getToken(PascalParser.POINTER, 0); }
		public PointerVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerVariable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitPointerVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointerVariableContext pointerVariable() throws RecognitionException {
		PointerVariableContext _localctx = new PointerVariableContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_pointerVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557);
			match(POINTER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public SimpleExpressionContext simpleExpression() {
			return getRuleContext(SimpleExpressionContext.class,0);
		}
		public RelationaloperatorContext relationaloperator() {
			return getRuleContext(RelationaloperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559);
			simpleExpression();
			setState(563);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IN) | (1L << EQUAL) | (1L << NOT_EQUAL) | (1L << LT) | (1L << LE) | (1L << GE) | (1L << GT))) != 0)) {
				{
				setState(560);
				relationaloperator();
				setState(561);
				expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationaloperatorContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(PascalParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(PascalParser.NOT_EQUAL, 0); }
		public TerminalNode LT() { return getToken(PascalParser.LT, 0); }
		public TerminalNode LE() { return getToken(PascalParser.LE, 0); }
		public TerminalNode GE() { return getToken(PascalParser.GE, 0); }
		public TerminalNode GT() { return getToken(PascalParser.GT, 0); }
		public TerminalNode IN() { return getToken(PascalParser.IN, 0); }
		public RelationaloperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationaloperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitRelationaloperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationaloperatorContext relationaloperator() throws RecognitionException {
		RelationaloperatorContext _localctx = new RelationaloperatorContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_relationaloperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IN) | (1L << EQUAL) | (1L << NOT_EQUAL) | (1L << LT) | (1L << LE) | (1L << GE) | (1L << GT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleExpressionContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public AdditiveoperatorContext additiveoperator() {
			return getRuleContext(AdditiveoperatorContext.class,0);
		}
		public SimpleExpressionContext simpleExpression() {
			return getRuleContext(SimpleExpressionContext.class,0);
		}
		public SimpleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitSimpleExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleExpressionContext simpleExpression() throws RecognitionException {
		SimpleExpressionContext _localctx = new SimpleExpressionContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_simpleExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(567);
			term();
			setState(571);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << PLUS) | (1L << MINUS))) != 0)) {
				{
				setState(568);
				additiveoperator();
				setState(569);
				simpleExpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveoperatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(PascalParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PascalParser.MINUS, 0); }
		public TerminalNode OR() { return getToken(PascalParser.OR, 0); }
		public AdditiveoperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveoperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitAdditiveoperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveoperatorContext additiveoperator() throws RecognitionException {
		AdditiveoperatorContext _localctx = new AdditiveoperatorContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_additiveoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(573);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << PLUS) | (1L << MINUS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public SignedFactorContext signedFactor() {
			return getRuleContext(SignedFactorContext.class,0);
		}
		public MultiplicativeoperatorContext multiplicativeoperator() {
			return getRuleContext(MultiplicativeoperatorContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(575);
			signedFactor();
			setState(579);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << DIV) | (1L << MOD) | (1L << STAR) | (1L << SLASH))) != 0)) {
				{
				setState(576);
				multiplicativeoperator();
				setState(577);
				term();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeoperatorContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(PascalParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(PascalParser.SLASH, 0); }
		public TerminalNode DIV() { return getToken(PascalParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(PascalParser.MOD, 0); }
		public TerminalNode AND() { return getToken(PascalParser.AND, 0); }
		public MultiplicativeoperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeoperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitMultiplicativeoperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeoperatorContext multiplicativeoperator() throws RecognitionException {
		MultiplicativeoperatorContext _localctx = new MultiplicativeoperatorContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_multiplicativeoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << DIV) | (1L << MOD) | (1L << STAR) | (1L << SLASH))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignedFactorContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(PascalParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PascalParser.MINUS, 0); }
		public SignedFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signedFactor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitSignedFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignedFactorContext signedFactor() throws RecognitionException {
		SignedFactorContext _localctx = new SignedFactorContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_signedFactor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(584);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(583);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(586);
			factor();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PascalParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PascalParser.RPAREN, 0); }
		public FunctionDesignatorContext functionDesignator() {
			return getRuleContext(FunctionDesignatorContext.class,0);
		}
		public UnsignedConstantContext unsignedConstant() {
			return getRuleContext(UnsignedConstantContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public TerminalNode NOT() { return getToken(PascalParser.NOT, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_factor);
		try {
			setState(599);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(588);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(589);
				match(LPAREN);
				setState(590);
				expression();
				setState(591);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(593);
				functionDesignator();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(594);
				unsignedConstant();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(595);
				set();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(596);
				match(NOT);
				setState(597);
				factor();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(598);
				bool();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnsignedConstantContext extends ParserRuleContext {
		public UnsignedNumberContext unsignedNumber() {
			return getRuleContext(UnsignedNumberContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode NIL() { return getToken(PascalParser.NIL, 0); }
		public UnsignedConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedConstant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitUnsignedConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedConstantContext unsignedConstant() throws RecognitionException {
		UnsignedConstantContext _localctx = new UnsignedConstantContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_unsignedConstant);
		try {
			setState(604);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM_INT:
			case NUM_REAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(601);
				unsignedNumber();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(602);
				string();
				}
				break;
			case NIL:
				enterOuterAlt(_localctx, 3);
				{
				setState(603);
				match(NIL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDesignatorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PascalParser.LPAREN, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PascalParser.RPAREN, 0); }
		public FunctionDesignatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDesignator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFunctionDesignator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDesignatorContext functionDesignator() throws RecognitionException {
		FunctionDesignatorContext _localctx = new FunctionDesignatorContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_functionDesignator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(606);
			identifier();
			setState(607);
			match(LPAREN);
			setState(608);
			parameterList();
			setState(609);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<ActualParameterContext> actualParameter() {
			return getRuleContexts(ActualParameterContext.class);
		}
		public ActualParameterContext actualParameter(int i) {
			return getRuleContext(ActualParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PascalParser.COMMA, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(611);
			actualParameter();
			setState(616);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(612);
				match(COMMA);
				setState(613);
				actualParameter();
				}
				}
				setState(618);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(PascalParser.LBRACK, 0); }
		public ElementListContext elementList() {
			return getRuleContext(ElementListContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(PascalParser.RBRACK, 0); }
		public TerminalNode LBRACK2() { return getToken(PascalParser.LBRACK2, 0); }
		public TerminalNode RBRACK2() { return getToken(PascalParser.RBRACK2, 0); }
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_set);
		try {
			setState(627);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(619);
				match(LBRACK);
				setState(620);
				elementList();
				setState(621);
				match(RBRACK);
				}
				break;
			case LBRACK2:
				enterOuterAlt(_localctx, 2);
				{
				setState(623);
				match(LBRACK2);
				setState(624);
				elementList();
				setState(625);
				match(RBRACK2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementListContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PascalParser.COMMA, i);
		}
		public ElementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitElementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementListContext elementList() throws RecognitionException {
		ElementListContext _localctx = new ElementListContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_elementList);
		int _la;
		try {
			setState(638);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NIL:
			case NOT:
			case PLUS:
			case MINUS:
			case LPAREN:
			case LBRACK:
			case LBRACK2:
			case AT:
			case TRUE:
			case FALSE:
			case IDENT:
			case STRING_LITERAL:
			case NUM_INT:
			case NUM_REAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(629);
				element();
				setState(634);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(630);
					match(COMMA);
					setState(631);
					element();
					}
					}
					setState(636);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case RBRACK:
			case RBRACK2:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DOTDOT() { return getToken(PascalParser.DOTDOT, 0); }
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(640);
			expression();
			setState(643);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOTDOT) {
				{
				setState(641);
				match(DOTDOT);
				setState(642);
				expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureStatementContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PascalParser.LPAREN, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PascalParser.RPAREN, 0); }
		public ProcedureStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitProcedureStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureStatementContext procedureStatement() throws RecognitionException {
		ProcedureStatementContext _localctx = new ProcedureStatementContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_procedureStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(645);
			identifier();
			setState(650);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(646);
				match(LPAREN);
				setState(647);
				parameterList();
				setState(648);
				match(RPAREN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActualParameterContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<ParameterwidthContext> parameterwidth() {
			return getRuleContexts(ParameterwidthContext.class);
		}
		public ParameterwidthContext parameterwidth(int i) {
			return getRuleContext(ParameterwidthContext.class,i);
		}
		public ActualParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualParameter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitActualParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActualParameterContext actualParameter() throws RecognitionException {
		ActualParameterContext _localctx = new ActualParameterContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_actualParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652);
			expression();
			setState(656);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COLON) {
				{
				{
				setState(653);
				parameterwidth();
				}
				}
				setState(658);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterwidthContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParameterwidthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterwidth; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitParameterwidth(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterwidthContext parameterwidth() throws RecognitionException {
		ParameterwidthContext _localctx = new ParameterwidthContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_parameterwidth);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(659);
			match(COLON);
			setState(660);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GotoStatementContext extends ParserRuleContext {
		public TerminalNode GOTO() { return getToken(PascalParser.GOTO, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public GotoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gotoStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitGotoStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GotoStatementContext gotoStatement() throws RecognitionException {
		GotoStatementContext _localctx = new GotoStatementContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_gotoStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(662);
			match(GOTO);
			setState(663);
			label();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EmptyStatementContext extends ParserRuleContext {
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructuredStatementContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public ConditionalStatementContext conditionalStatement() {
			return getRuleContext(ConditionalStatementContext.class,0);
		}
		public RepetetiveStatementContext repetetiveStatement() {
			return getRuleContext(RepetetiveStatementContext.class,0);
		}
		public WithStatementContext withStatement() {
			return getRuleContext(WithStatementContext.class,0);
		}
		public StructuredStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structuredStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitStructuredStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructuredStatementContext structuredStatement() throws RecognitionException {
		StructuredStatementContext _localctx = new StructuredStatementContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_structuredStatement);
		try {
			setState(671);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(667);
				compoundStatement();
				}
				break;
			case CASE:
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(668);
				conditionalStatement();
				}
				break;
			case FOR:
			case REPEAT:
			case WHILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(669);
				repetetiveStatement();
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 4);
				{
				setState(670);
				withStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(PascalParser.BEGIN, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode END() { return getToken(PascalParser.END, 0); }
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitCompoundStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_compoundStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(673);
			match(BEGIN);
			setState(674);
			statements();
			setState(675);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(677);
			statement();
			setState(682);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMI) {
				{
				{
				setState(678);
				match(SEMI);
				setState(679);
				statement();
				}
				}
				setState(684);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalStatementContext extends ParserRuleContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public CaseStatementContext caseStatement() {
			return getRuleContext(CaseStatementContext.class,0);
		}
		public ConditionalStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitConditionalStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalStatementContext conditionalStatement() throws RecognitionException {
		ConditionalStatementContext _localctx = new ConditionalStatementContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_conditionalStatement);
		try {
			setState(687);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(685);
				ifStatement();
				}
				break;
			case CASE:
				enterOuterAlt(_localctx, 2);
				{
				setState(686);
				caseStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(PascalParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(PascalParser.THEN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(PascalParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(689);
			match(IF);
			setState(690);
			expression();
			setState(691);
			match(THEN);
			setState(692);
			statement();
			setState(695);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(693);
				match(ELSE);
				setState(694);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseStatementContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(PascalParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode OF() { return getToken(PascalParser.OF, 0); }
		public List<CaseListElementContext> caseListElement() {
			return getRuleContexts(CaseListElementContext.class);
		}
		public CaseListElementContext caseListElement(int i) {
			return getRuleContext(CaseListElementContext.class,i);
		}
		public TerminalNode END() { return getToken(PascalParser.END, 0); }
		public List<TerminalNode> SEMI() { return getTokens(PascalParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PascalParser.SEMI, i);
		}
		public TerminalNode ELSE() { return getToken(PascalParser.ELSE, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public CaseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitCaseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseStatementContext caseStatement() throws RecognitionException {
		CaseStatementContext _localctx = new CaseStatementContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_caseStatement);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(697);
			match(CASE);
			setState(698);
			expression();
			setState(699);
			match(OF);
			setState(700);
			caseListElement();
			setState(705);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(701);
					match(SEMI);
					setState(702);
					caseListElement();
					}
					} 
				}
				setState(707);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			}
			setState(711);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(708);
				match(SEMI);
				setState(709);
				match(ELSE);
				setState(710);
				statements();
				}
			}

			setState(713);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseListElementContext extends ParserRuleContext {
		public ConstListContext constList() {
			return getRuleContext(ConstListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PascalParser.COLON, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public CaseListElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseListElement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitCaseListElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseListElementContext caseListElement() throws RecognitionException {
		CaseListElementContext _localctx = new CaseListElementContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_caseListElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(715);
			constList();
			setState(716);
			match(COLON);
			setState(717);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepetetiveStatementContext extends ParserRuleContext {
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public RepeatStatementContext repeatStatement() {
			return getRuleContext(RepeatStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public RepetetiveStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repetetiveStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitRepetetiveStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepetetiveStatementContext repetetiveStatement() throws RecognitionException {
		RepetetiveStatementContext _localctx = new RepetetiveStatementContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_repetetiveStatement);
		try {
			setState(722);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(719);
				whileStatement();
				}
				break;
			case REPEAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(720);
				repeatStatement();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(721);
				forStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(PascalParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DO() { return getToken(PascalParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(724);
			match(WHILE);
			setState(725);
			expression();
			setState(726);
			match(DO);
			setState(727);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RepeatStatementContext extends ParserRuleContext {
		public TerminalNode REPEAT() { return getToken(PascalParser.REPEAT, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode UNTIL() { return getToken(PascalParser.UNTIL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RepeatStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitRepeatStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatStatementContext repeatStatement() throws RecognitionException {
		RepeatStatementContext _localctx = new RepeatStatementContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_repeatStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(729);
			match(REPEAT);
			setState(730);
			statements();
			setState(731);
			match(UNTIL);
			setState(732);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(PascalParser.FOR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(PascalParser.ASSIGN, 0); }
		public InitialValueContext initialValue() {
			return getRuleContext(InitialValueContext.class,0);
		}
		public FinalValueContext finalValue() {
			return getRuleContext(FinalValueContext.class,0);
		}
		public TerminalNode DO() { return getToken(PascalParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode TO() { return getToken(PascalParser.TO, 0); }
		public TerminalNode DOWNTO() { return getToken(PascalParser.DOWNTO, 0); }
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(734);
			match(FOR);
			setState(735);
			identifier();
			setState(736);
			match(ASSIGN);
			setState(737);
			initialValue();
			setState(738);
			_la = _input.LA(1);
			if ( !(_la==DOWNTO || _la==TO) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(739);
			finalValue();
			setState(740);
			match(DO);
			setState(741);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitialValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InitialValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initialValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitInitialValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitialValueContext initialValue() throws RecognitionException {
		InitialValueContext _localctx = new InitialValueContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_initialValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinalValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FinalValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finalValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitFinalValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinalValueContext finalValue() throws RecognitionException {
		FinalValueContext _localctx = new FinalValueContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_finalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(745);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WithStatementContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(PascalParser.WITH, 0); }
		public RecordVariableListContext recordVariableList() {
			return getRuleContext(RecordVariableListContext.class,0);
		}
		public TerminalNode DO() { return getToken(PascalParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WithStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitWithStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithStatementContext withStatement() throws RecognitionException {
		WithStatementContext _localctx = new WithStatementContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_withStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(747);
			match(WITH);
			setState(748);
			recordVariableList();
			setState(749);
			match(DO);
			setState(750);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RecordVariableListContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PascalParser.COMMA, i);
		}
		public RecordVariableListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recordVariableList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PascalVisitor ) return ((PascalVisitor<? extends T>)visitor).visitRecordVariableList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RecordVariableListContext recordVariableList() throws RecognitionException {
		RecordVariableListContext _localctx = new RecordVariableListContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_recordVariableList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(752);
			variable();
			setState(757);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(753);
				match(COMMA);
				setState(754);
				variable();
				}
				}
				setState(759);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3L\u02fb\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u00c7\n\3\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\7\5\u00d2\n\5\f\5\16\5\u00d5\13\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\7\6\u00dd\n\6\f\6\16\6\u00e0\13\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\6\b"+
		"\u00ea\n\b\r\b\16\b\u00eb\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\5\n\u00fb\n\n\3\13\3\13\5\13\u00ff\n\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\6\21\u010f\n\21\r\21\16"+
		"\21\u0110\3\22\3\22\3\22\3\22\3\22\5\22\u0118\n\22\3\23\3\23\5\23\u011c"+
		"\n\23\3\23\3\23\3\23\3\24\3\24\5\24\u0123\n\24\3\25\3\25\3\25\5\25\u0128"+
		"\n\25\3\26\3\26\3\26\3\26\5\26\u012e\n\26\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\5\31\u013a\n\31\3\32\3\32\3\32\3\32\5\32\u0140\n"+
		"\32\3\33\3\33\3\33\3\33\5\33\u0146\n\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0158\n\34\3\35"+
		"\3\35\3\35\7\35\u015d\n\35\f\35\16\35\u0160\13\35\3\36\3\36\3\36\3\36"+
		"\7\36\u0166\n\36\f\36\16\36\u0169\13\36\3\36\5\36\u016c\n\36\5\36\u016e"+
		"\n\36\3\36\3\36\3\37\3\37\3\37\5\37\u0175\n\37\3\37\5\37\u0178\n\37\3"+
		" \3 \3 \7 \u017d\n \f \16 \u0180\13 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\7\"\u018c\n\"\f\"\16\"\u018f\13\"\3#\3#\3#\3#\3#\5#\u0196\n#\3$\3$"+
		"\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\5&\u01a6\n&\3\'\3\'\3\'\3(\3(\3("+
		"\3(\7(\u01af\n(\f(\16(\u01b2\13(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3+\3+\5+\u01bf"+
		"\n+\3,\3,\3,\5,\u01c4\n,\3,\3,\3,\3-\3-\3-\3-\7-\u01cd\n-\f-\16-\u01d0"+
		"\13-\3-\3-\3.\3.\3.\3.\3.\3.\3.\5.\u01db\n.\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\7\60\u01e4\n\60\f\60\16\60\u01e7\13\60\3\61\3\61\3\61\7\61\u01ec\n\61"+
		"\f\61\16\61\u01ef\13\61\3\62\3\62\3\62\5\62\u01f4\n\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\64\3\64\5\64\u0202\n\64\3\65\3\65"+
		"\5\65\u0206\n\65\3\66\3\66\3\66\3\66\5\66\u020c\n\66\3\67\3\67\3\67\3"+
		"\67\38\38\38\58\u0215\n8\38\78\u0218\n8\f8\168\u021b\138\39\39\39\59\u0220"+
		"\n9\3:\3:\3:\3:\7:\u0226\n:\f:\16:\u0229\13:\3:\3:\3;\3;\3;\3<\3<\3=\3"+
		"=\3=\3=\5=\u0236\n=\3>\3>\3?\3?\3?\3?\5?\u023e\n?\3@\3@\3A\3A\3A\3A\5"+
		"A\u0246\nA\3B\3B\3C\5C\u024b\nC\3C\3C\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3"+
		"D\5D\u025a\nD\3E\3E\3E\5E\u025f\nE\3F\3F\3F\3F\3F\3G\3G\3G\7G\u0269\n"+
		"G\fG\16G\u026c\13G\3H\3H\3H\3H\3H\3H\3H\3H\5H\u0276\nH\3I\3I\3I\7I\u027b"+
		"\nI\fI\16I\u027e\13I\3I\5I\u0281\nI\3J\3J\3J\5J\u0286\nJ\3K\3K\3K\3K\3"+
		"K\5K\u028d\nK\3L\3L\7L\u0291\nL\fL\16L\u0294\13L\3M\3M\3M\3N\3N\3N\3O"+
		"\3O\3P\3P\3P\3P\5P\u02a2\nP\3Q\3Q\3Q\3Q\3R\3R\3R\7R\u02ab\nR\fR\16R\u02ae"+
		"\13R\3S\3S\5S\u02b2\nS\3T\3T\3T\3T\3T\3T\5T\u02ba\nT\3U\3U\3U\3U\3U\3"+
		"U\7U\u02c2\nU\fU\16U\u02c5\13U\3U\3U\3U\5U\u02ca\nU\3U\3U\3V\3V\3V\3V"+
		"\3W\3W\3W\5W\u02d5\nW\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3Z"+
		"\3Z\3Z\3Z\3[\3[\3\\\3\\\3]\3]\3]\3]\3]\3^\3^\3^\7^\u02f6\n^\f^\16^\u02f9"+
		"\13^\3^\2\2_\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0"+
		"\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8"+
		"\u00ba\2\t\3\2)*\3\2DE\7\2\6\6\b\b\25\25\36\36CC\4\2\24\24\61\66\4\2\33"+
		"\33)*\6\2\3\3\n\n\27\27+,\4\2\f\f##\2\u02f6\2\u00bc\3\2\2\2\4\u00c0\3"+
		"\2\2\2\6\u00ca\3\2\2\2\b\u00d3\3\2\2\2\n\u00d8\3\2\2\2\f\u00e3\3\2\2\2"+
		"\16\u00e5\3\2\2\2\20\u00ed\3\2\2\2\22\u00fa\3\2\2\2\24\u00fe\3\2\2\2\26"+
		"\u0100\3\2\2\2\30\u0102\3\2\2\2\32\u0104\3\2\2\2\34\u0106\3\2\2\2\36\u0108"+
		"\3\2\2\2 \u010a\3\2\2\2\"\u0112\3\2\2\2$\u0119\3\2\2\2&\u0120\3\2\2\2"+
		"(\u0127\3\2\2\2*\u012d\3\2\2\2,\u012f\3\2\2\2.\u0133\3\2\2\2\60\u0139"+
		"\3\2\2\2\62\u013f\3\2\2\2\64\u0141\3\2\2\2\66\u0157\3\2\2\28\u0159\3\2"+
		"\2\2:\u0161\3\2\2\2<\u0177\3\2\2\2>\u0179\3\2\2\2@\u0181\3\2\2\2B\u0185"+
		"\3\2\2\2D\u0195\3\2\2\2F\u0197\3\2\2\2H\u019d\3\2\2\2J\u01a5\3\2\2\2L"+
		"\u01a7\3\2\2\2N\u01aa\3\2\2\2P\u01b5\3\2\2\2R\u01b9\3\2\2\2T\u01be\3\2"+
		"\2\2V\u01c0\3\2\2\2X\u01c8\3\2\2\2Z\u01da\3\2\2\2\\\u01dc\3\2\2\2^\u01e0"+
		"\3\2\2\2`\u01e8\3\2\2\2b\u01f0\3\2\2\2d\u01fa\3\2\2\2f\u0201\3\2\2\2h"+
		"\u0205\3\2\2\2j\u020b\3\2\2\2l\u020d\3\2\2\2n\u0214\3\2\2\2p\u021f\3\2"+
		"\2\2r\u0221\3\2\2\2t\u022c\3\2\2\2v\u022f\3\2\2\2x\u0231\3\2\2\2z\u0237"+
		"\3\2\2\2|\u0239\3\2\2\2~\u023f\3\2\2\2\u0080\u0241\3\2\2\2\u0082\u0247"+
		"\3\2\2\2\u0084\u024a\3\2\2\2\u0086\u0259\3\2\2\2\u0088\u025e\3\2\2\2\u008a"+
		"\u0260\3\2\2\2\u008c\u0265\3\2\2\2\u008e\u0275\3\2\2\2\u0090\u0280\3\2"+
		"\2\2\u0092\u0282\3\2\2\2\u0094\u0287\3\2\2\2\u0096\u028e\3\2\2\2\u0098"+
		"\u0295\3\2\2\2\u009a\u0298\3\2\2\2\u009c\u029b\3\2\2\2\u009e\u02a1\3\2"+
		"\2\2\u00a0\u02a3\3\2\2\2\u00a2\u02a7\3\2\2\2\u00a4\u02b1\3\2\2\2\u00a6"+
		"\u02b3\3\2\2\2\u00a8\u02bb\3\2\2\2\u00aa\u02cd\3\2\2\2\u00ac\u02d4\3\2"+
		"\2\2\u00ae\u02d6\3\2\2\2\u00b0\u02db\3\2\2\2\u00b2\u02e0\3\2\2\2\u00b4"+
		"\u02e9\3\2\2\2\u00b6\u02eb\3\2\2\2\u00b8\u02ed\3\2\2\2\u00ba\u02f2\3\2"+
		"\2\2\u00bc\u00bd\5\4\3\2\u00bd\u00be\5\b\5\2\u00be\u00bf\7?\2\2\u00bf"+
		"\3\3\2\2\2\u00c0\u00c1\7\35\2\2\u00c1\u00c6\5\6\4\2\u00c2\u00c3\7\67\2"+
		"\2\u00c3\u00c4\5^\60\2\u00c4\u00c5\78\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c2"+
		"\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\7/\2\2\u00c9"+
		"\5\3\2\2\2\u00ca\u00cb\7I\2\2\u00cb\7\3\2\2\2\u00cc\u00d2\5\n\6\2\u00cd"+
		"\u00d2\5\16\b\2\u00ce\u00d2\5 \21\2\u00cf\u00d2\5N(\2\u00d0\u00d2\5R*"+
		"\2\u00d1\u00cc\3\2\2\2\u00d1\u00cd\3\2\2\2\u00d1\u00ce\3\2\2\2\u00d1\u00cf"+
		"\3\2\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d7\5\u00a0"+
		"Q\2\u00d7\t\3\2\2\2\u00d8\u00d9\7\26\2\2\u00d9\u00de\5\f\7\2\u00da\u00db"+
		"\7.\2\2\u00db\u00dd\5\f\7\2\u00dc\u00da\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e1\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e1\u00e2\7/\2\2\u00e2\13\3\2\2\2\u00e3\u00e4\5\26\f\2\u00e4\r"+
		"\3\2\2\2\u00e5\u00e9\7\t\2\2\u00e6\u00e7\5\20\t\2\u00e7\u00e8\7/\2\2\u00e8"+
		"\u00ea\3\2\2\2\u00e9\u00e6\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00e9\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\17\3\2\2\2\u00ed\u00ee\5\6\4\2\u00ee\u00ef"+
		"\7\61\2\2\u00ef\u00f0\5\22\n\2\u00f0\21\3\2\2\2\u00f1\u00fb\5\24\13\2"+
		"\u00f2\u00f3\5\32\16\2\u00f3\u00f4\5\24\13\2\u00f4\u00fb\3\2\2\2\u00f5"+
		"\u00fb\5\6\4\2\u00f6\u00f7\5\32\16\2\u00f7\u00f8\5\6\4\2\u00f8\u00fb\3"+
		"\2\2\2\u00f9\u00fb\5\36\20\2\u00fa\u00f1\3\2\2\2\u00fa\u00f2\3\2\2\2\u00fa"+
		"\u00f5\3\2\2\2\u00fa\u00f6\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb\23\3\2\2"+
		"\2\u00fc\u00ff\5\26\f\2\u00fd\u00ff\5\30\r\2\u00fe\u00fc\3\2\2\2\u00fe"+
		"\u00fd\3\2\2\2\u00ff\25\3\2\2\2\u0100\u0101\7K\2\2\u0101\27\3\2\2\2\u0102"+
		"\u0103\7L\2\2\u0103\31\3\2\2\2\u0104\u0105\t\2\2\2\u0105\33\3\2\2\2\u0106"+
		"\u0107\t\3\2\2\u0107\35\3\2\2\2\u0108\u0109\7J\2\2\u0109\37\3\2\2\2\u010a"+
		"\u010e\7$\2\2\u010b\u010c\5\"\22\2\u010c\u010d\7/\2\2\u010d\u010f\3\2"+
		"\2\2\u010e\u010b\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e\3\2\2\2\u0110"+
		"\u0111\3\2\2\2\u0111!\3\2\2\2\u0112\u0113\5\6\4\2\u0113\u0117\7\61\2\2"+
		"\u0114\u0118\5(\25\2\u0115\u0118\5$\23\2\u0116\u0118\5&\24\2\u0117\u0114"+
		"\3\2\2\2\u0117\u0115\3\2\2\2\u0117\u0116\3\2\2\2\u0118#\3\2\2\2\u0119"+
		"\u011b\7\21\2\2\u011a\u011c\5X-\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2"+
		"\2\2\u011c\u011d\3\2\2\2\u011d\u011e\7\60\2\2\u011e\u011f\5d\63\2\u011f"+
		"%\3\2\2\2\u0120\u0122\7\34\2\2\u0121\u0123\5X-\2\u0122\u0121\3\2\2\2\u0122"+
		"\u0123\3\2\2\2\u0123\'\3\2\2\2\u0124\u0128\5*\26\2\u0125\u0128\5\62\32"+
		"\2\u0126\u0128\5L\'\2\u0127\u0124\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0126"+
		"\3\2\2\2\u0128)\3\2\2\2\u0129\u012e\5,\27\2\u012a\u012e\5.\30\2\u012b"+
		"\u012e\5\60\31\2\u012c\u012e\5\64\33\2\u012d\u0129\3\2\2\2\u012d\u012a"+
		"\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012c\3\2\2\2\u012e+\3\2\2\2\u012f"+
		"\u0130\7\67\2\2\u0130\u0131\5^\60\2\u0131\u0132\78\2\2\u0132-\3\2\2\2"+
		"\u0133\u0134\5\22\n\2\u0134\u0135\7@\2\2\u0135\u0136\5\22\n\2\u0136/\3"+
		"\2\2\2\u0137\u013a\5\6\4\2\u0138\u013a\t\4\2\2\u0139\u0137\3\2\2\2\u0139"+
		"\u0138\3\2\2\2\u013a\61\3\2\2\2\u013b\u0140\5\66\34\2\u013c\u0140\5:\36"+
		"\2\u013d\u0140\5H%\2\u013e\u0140\5J&\2\u013f\u013b\3\2\2\2\u013f\u013c"+
		"\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u013e\3\2\2\2\u0140\63\3\2\2\2\u0141"+
		"\u0142\7C\2\2\u0142\u0145\79\2\2\u0143\u0146\5\6\4\2\u0144\u0146\7K\2"+
		"\2\u0145\u0143\3\2\2\2\u0145\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0148"+
		"\7;\2\2\u0148\65\3\2\2\2\u0149\u014a\7\4\2\2\u014a\u014b\79\2\2\u014b"+
		"\u014c\58\35\2\u014c\u014d\7;\2\2\u014d\u014e\7\32\2\2\u014e\u014f\5("+
		"\25\2\u014f\u0158\3\2\2\2\u0150\u0151\7\4\2\2\u0151\u0152\7:\2\2\u0152"+
		"\u0153\58\35\2\u0153\u0154\7<\2\2\u0154\u0155\7\32\2\2\u0155\u0156\5("+
		"\25\2\u0156\u0158\3\2\2\2\u0157\u0149\3\2\2\2\u0157\u0150\3\2\2\2\u0158"+
		"\67\3\2\2\2\u0159\u015e\5*\26\2\u015a\u015b\7.\2\2\u015b\u015d\5*\26\2"+
		"\u015c\u015a\3\2\2\2\u015d\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f"+
		"\3\2\2\2\u015f9\3\2\2\2\u0160\u015e\3\2\2\2\u0161\u016d\7\37\2\2\u0162"+
		"\u0167\5@!\2\u0163\u0164\7/\2\2\u0164\u0166\5@!\2\u0165\u0163\3\2\2\2"+
		"\u0166\u0169\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u016b"+
		"\3\2\2\2\u0169\u0167\3\2\2\2\u016a\u016c\7/\2\2\u016b\u016a\3\2\2\2\u016b"+
		"\u016c\3\2\2\2\u016c\u016e\3\2\2\2\u016d\u0162\3\2\2\2\u016d\u016e\3\2"+
		"\2\2\u016e\u016f\3\2\2\2\u016f\u0170\7\16\2\2\u0170;\3\2\2\2\u0171\u0174"+
		"\5> \2\u0172\u0173\7/\2\2\u0173\u0175\5B\"\2\u0174\u0172\3\2\2\2\u0174"+
		"\u0175\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0178\5B\"\2\u0177\u0171\3\2"+
		"\2\2\u0177\u0176\3\2\2\2\u0178=\3\2\2\2\u0179\u017e\5@!\2\u017a\u017b"+
		"\7/\2\2\u017b\u017d\5@!\2\u017c\u017a\3\2\2\2\u017d\u0180\3\2\2\2\u017e"+
		"\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f?\3\2\2\2\u0180\u017e\3\2\2\2"+
		"\u0181\u0182\5^\60\2\u0182\u0183\7\60\2\2\u0183\u0184\5(\25\2\u0184A\3"+
		"\2\2\2\u0185\u0186\7\7\2\2\u0186\u0187\5D#\2\u0187\u0188\7\32\2\2\u0188"+
		"\u018d\5F$\2\u0189\u018a\7/\2\2\u018a\u018c\5F$\2\u018b\u0189\3\2\2\2"+
		"\u018c\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018eC\3"+
		"\2\2\2\u018f\u018d\3\2\2\2\u0190\u0191\5\6\4\2\u0191\u0192\7\60\2\2\u0192"+
		"\u0193\5\60\31\2\u0193\u0196\3\2\2\2\u0194\u0196\5\60\31\2\u0195\u0190"+
		"\3\2\2\2\u0195\u0194\3\2\2\2\u0196E\3\2\2\2\u0197\u0198\5`\61\2\u0198"+
		"\u0199\7\60\2\2\u0199\u019a\7\67\2\2\u019a\u019b\5<\37\2\u019b\u019c\7"+
		"8\2\2\u019cG\3\2\2\2\u019d\u019e\7!\2\2\u019e\u019f\7\32\2\2\u019f\u01a0"+
		"\5*\26\2\u01a0I\3\2\2\2\u01a1\u01a2\7\17\2\2\u01a2\u01a3\7\32\2\2\u01a3"+
		"\u01a6\5(\25\2\u01a4\u01a6\7\17\2\2\u01a5\u01a1\3\2\2\2\u01a5\u01a4\3"+
		"\2\2\2\u01a6K\3\2\2\2\u01a7\u01a8\7=\2\2\u01a8\u01a9\5\60\31\2\u01a9M"+
		"\3\2\2\2\u01aa\u01ab\7&\2\2\u01ab\u01b0\5P)\2\u01ac\u01ad\7/\2\2\u01ad"+
		"\u01af\5P)\2\u01ae\u01ac\3\2\2\2\u01af\u01b2\3\2\2\2\u01b0\u01ae\3\2\2"+
		"\2\u01b0\u01b1\3\2\2\2\u01b1\u01b3\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b3\u01b4"+
		"\7/\2\2\u01b4O\3\2\2\2\u01b5\u01b6\5^\60\2\u01b6\u01b7\7\60\2\2\u01b7"+
		"\u01b8\5(\25\2\u01b8Q\3\2\2\2\u01b9\u01ba\5T+\2\u01ba\u01bb\7/\2\2\u01bb"+
		"S\3\2\2\2\u01bc\u01bf\5V,\2\u01bd\u01bf\5b\62\2\u01be\u01bc\3\2\2\2\u01be"+
		"\u01bd\3\2\2\2\u01bfU\3\2\2\2\u01c0\u01c1\7\34\2\2\u01c1\u01c3\5\6\4\2"+
		"\u01c2\u01c4\5X-\2\u01c3\u01c2\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5"+
		"\3\2\2\2\u01c5\u01c6\7/\2\2\u01c6\u01c7\5\b\5\2\u01c7W\3\2\2\2\u01c8\u01c9"+
		"\7\67\2\2\u01c9\u01ce\5Z.\2\u01ca\u01cb\7/\2\2\u01cb\u01cd\5Z.\2\u01cc"+
		"\u01ca\3\2\2\2\u01cd\u01d0\3\2\2\2\u01ce\u01cc\3\2\2\2\u01ce\u01cf\3\2"+
		"\2\2\u01cf\u01d1\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d1\u01d2\78\2\2\u01d2"+
		"Y\3\2\2\2\u01d3\u01db\5\\/\2\u01d4\u01d5\7&\2\2\u01d5\u01db\5\\/\2\u01d6"+
		"\u01d7\7\21\2\2\u01d7\u01db\5\\/\2\u01d8\u01d9\7\34\2\2\u01d9\u01db\5"+
		"\\/\2\u01da\u01d3\3\2\2\2\u01da\u01d4\3\2\2\2\u01da\u01d6\3\2\2\2\u01da"+
		"\u01d8\3\2\2\2\u01db[\3\2\2\2\u01dc\u01dd\5^\60\2\u01dd\u01de\7\60\2\2"+
		"\u01de\u01df\5(\25\2\u01df]\3\2\2\2\u01e0\u01e5\5\6\4\2\u01e1\u01e2\7"+
		".\2\2\u01e2\u01e4\5\6\4\2\u01e3\u01e1\3\2\2\2\u01e4\u01e7\3\2\2\2\u01e5"+
		"\u01e3\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6_\3\2\2\2\u01e7\u01e5\3\2\2\2"+
		"\u01e8\u01ed\5\22\n\2\u01e9\u01ea\7.\2\2\u01ea\u01ec\5\22\n\2\u01eb\u01e9"+
		"\3\2\2\2\u01ec\u01ef\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee"+
		"a\3\2\2\2\u01ef\u01ed\3\2\2\2\u01f0\u01f1\7\21\2\2\u01f1\u01f3\5\6\4\2"+
		"\u01f2\u01f4\5X-\2\u01f3\u01f2\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f5"+
		"\3\2\2\2\u01f5\u01f6\7\60\2\2\u01f6\u01f7\5d\63\2\u01f7\u01f8\7/\2\2\u01f8"+
		"\u01f9\5\b\5\2\u01f9c\3\2\2\2\u01fa\u01fb\5\60\31\2\u01fbe\3\2\2\2\u01fc"+
		"\u01fd\5\f\7\2\u01fd\u01fe\7\60\2\2\u01fe\u01ff\5h\65\2\u01ff\u0202\3"+
		"\2\2\2\u0200\u0202\5h\65\2\u0201\u01fc\3\2\2\2\u0201\u0200\3\2\2\2\u0202"+
		"g\3\2\2\2\u0203\u0206\5j\66\2\u0204\u0206\5\u009eP\2\u0205\u0203\3\2\2"+
		"\2\u0205\u0204\3\2\2\2\u0206i\3\2\2\2\u0207\u020c\5l\67\2\u0208\u020c"+
		"\5\u0094K\2\u0209\u020c\5\u009aN\2\u020a\u020c\5\u009cO\2\u020b\u0207"+
		"\3\2\2\2\u020b\u0208\3\2\2\2\u020b\u0209\3\2\2\2\u020b\u020a\3\2\2\2\u020c"+
		"k\3\2\2\2\u020d\u020e\5n8\2\u020e\u020f\7-\2\2\u020f\u0210\5x=\2\u0210"+
		"m\3\2\2\2\u0211\u0212\7>\2\2\u0212\u0215\5\6\4\2\u0213\u0215\5\6\4\2\u0214"+
		"\u0211\3\2\2\2\u0214\u0213\3\2\2\2\u0215\u0219\3\2\2\2\u0216\u0218\5p"+
		"9\2\u0217\u0216\3\2\2\2\u0218\u021b\3\2\2\2\u0219\u0217\3\2\2\2\u0219"+
		"\u021a\3\2\2\2\u021ao\3\2\2\2\u021b\u0219\3\2\2\2\u021c\u0220\5r:\2\u021d"+
		"\u0220\5t;\2\u021e\u0220\5v<\2\u021f\u021c\3\2\2\2\u021f\u021d\3\2\2\2"+
		"\u021f\u021e\3\2\2\2\u0220q\3\2\2\2\u0221\u0222\79\2\2\u0222\u0227\5x"+
		"=\2\u0223\u0224\7.\2\2\u0224\u0226\5x=\2\u0225\u0223\3\2\2\2\u0226\u0229"+
		"\3\2\2\2\u0227\u0225\3\2\2\2\u0227\u0228\3\2\2\2\u0228\u022a\3\2\2\2\u0229"+
		"\u0227\3\2\2\2\u022a\u022b\7;\2\2\u022bs\3\2\2\2\u022c\u022d\7?\2\2\u022d"+
		"\u022e\5\6\4\2\u022eu\3\2\2\2\u022f\u0230\7=\2\2\u0230w\3\2\2\2\u0231"+
		"\u0235\5|?\2\u0232\u0233\5z>\2\u0233\u0234\5x=\2\u0234\u0236\3\2\2\2\u0235"+
		"\u0232\3\2\2\2\u0235\u0236\3\2\2\2\u0236y\3\2\2\2\u0237\u0238\t\5\2\2"+
		"\u0238{\3\2\2\2\u0239\u023d\5\u0080A\2\u023a\u023b\5~@\2\u023b\u023c\5"+
		"|?\2\u023c\u023e\3\2\2\2\u023d\u023a\3\2\2\2\u023d\u023e\3\2\2\2\u023e"+
		"}\3\2\2\2\u023f\u0240\t\6\2\2\u0240\177\3\2\2\2\u0241\u0245\5\u0084C\2"+
		"\u0242\u0243\5\u0082B\2\u0243\u0244\5\u0080A\2\u0244\u0246\3\2\2\2\u0245"+
		"\u0242\3\2\2\2\u0245\u0246\3\2\2\2\u0246\u0081\3\2\2\2\u0247\u0248\t\7"+
		"\2\2\u0248\u0083\3\2\2\2\u0249\u024b\t\2\2\2\u024a\u0249\3\2\2\2\u024a"+
		"\u024b\3\2\2\2\u024b\u024c\3\2\2\2\u024c\u024d\5\u0086D\2\u024d\u0085"+
		"\3\2\2\2\u024e\u025a\5n8\2\u024f\u0250\7\67\2\2\u0250\u0251\5x=\2\u0251"+
		"\u0252\78\2\2\u0252\u025a\3\2\2\2\u0253\u025a\5\u008aF\2\u0254\u025a\5"+
		"\u0088E\2\u0255\u025a\5\u008eH\2\u0256\u0257\7\31\2\2\u0257\u025a\5\u0086"+
		"D\2\u0258\u025a\5\34\17\2\u0259\u024e\3\2\2\2\u0259\u024f\3\2\2\2\u0259"+
		"\u0253\3\2\2\2\u0259\u0254\3\2\2\2\u0259\u0255\3\2\2\2\u0259\u0256\3\2"+
		"\2\2\u0259\u0258\3\2\2\2\u025a\u0087\3\2\2\2\u025b\u025f\5\24\13\2\u025c"+
		"\u025f\5\36\20\2\u025d\u025f\7\30\2\2\u025e\u025b\3\2\2\2\u025e\u025c"+
		"\3\2\2\2\u025e\u025d\3\2\2\2\u025f\u0089\3\2\2\2\u0260\u0261\5\6\4\2\u0261"+
		"\u0262\7\67\2\2\u0262\u0263\5\u008cG\2\u0263\u0264\78\2\2\u0264\u008b"+
		"\3\2\2\2\u0265\u026a\5\u0096L\2\u0266\u0267\7.\2\2\u0267\u0269\5\u0096"+
		"L\2\u0268\u0266\3\2\2\2\u0269\u026c\3\2\2\2\u026a\u0268\3\2\2\2\u026a"+
		"\u026b\3\2\2\2\u026b\u008d\3\2\2\2\u026c\u026a\3\2\2\2\u026d\u026e\79"+
		"\2\2\u026e\u026f\5\u0090I\2\u026f\u0270\7;\2\2\u0270\u0276\3\2\2\2\u0271"+
		"\u0272\7:\2\2\u0272\u0273\5\u0090I\2\u0273\u0274\7<\2\2\u0274\u0276\3"+
		"\2\2\2\u0275\u026d\3\2\2\2\u0275\u0271\3\2\2\2\u0276\u008f\3\2\2\2\u0277"+
		"\u027c\5\u0092J\2\u0278\u0279\7.\2\2\u0279\u027b\5\u0092J\2\u027a\u0278"+
		"\3\2\2\2\u027b\u027e\3\2\2\2\u027c\u027a\3\2\2\2\u027c\u027d\3\2\2\2\u027d"+
		"\u0281\3\2\2\2\u027e\u027c\3\2\2\2\u027f\u0281\3\2\2\2\u0280\u0277\3\2"+
		"\2\2\u0280\u027f\3\2\2\2\u0281\u0091\3\2\2\2\u0282\u0285\5x=\2\u0283\u0284"+
		"\7@\2\2\u0284\u0286\5x=\2\u0285\u0283\3\2\2\2\u0285\u0286\3\2\2\2\u0286"+
		"\u0093\3\2\2\2\u0287\u028c\5\6\4\2\u0288\u0289\7\67\2\2\u0289\u028a\5"+
		"\u008cG\2\u028a\u028b\78\2\2\u028b\u028d\3\2\2\2\u028c\u0288\3\2\2\2\u028c"+
		"\u028d\3\2\2\2\u028d\u0095\3\2\2\2\u028e\u0292\5x=\2\u028f\u0291\5\u0098"+
		"M\2\u0290\u028f\3\2\2\2\u0291\u0294\3\2\2\2\u0292\u0290\3\2\2\2\u0292"+
		"\u0293\3\2\2\2\u0293\u0097\3\2\2\2\u0294\u0292\3\2\2\2\u0295\u0296\7\60"+
		"\2\2\u0296\u0297\5x=\2\u0297\u0099\3\2\2\2\u0298\u0299\7\22\2\2\u0299"+
		"\u029a\5\f\7\2\u029a\u009b\3\2\2\2\u029b\u029c\3\2\2\2\u029c\u009d\3\2"+
		"\2\2\u029d\u02a2\5\u00a0Q\2\u029e\u02a2\5\u00a4S\2\u029f\u02a2\5\u00ac"+
		"W\2\u02a0\u02a2\5\u00b8]\2\u02a1\u029d\3\2\2\2\u02a1\u029e\3\2\2\2\u02a1"+
		"\u029f\3\2\2\2\u02a1\u02a0\3\2\2\2\u02a2\u009f\3\2\2\2\u02a3\u02a4\7\5"+
		"\2\2\u02a4\u02a5\5\u00a2R\2\u02a5\u02a6\7\16\2\2\u02a6\u00a1\3\2\2\2\u02a7"+
		"\u02ac\5f\64\2\u02a8\u02a9\7/\2\2\u02a9\u02ab\5f\64\2\u02aa\u02a8\3\2"+
		"\2\2\u02ab\u02ae\3\2\2\2\u02ac\u02aa\3\2\2\2\u02ac\u02ad\3\2\2\2\u02ad"+
		"\u00a3\3\2\2\2\u02ae\u02ac\3\2\2\2\u02af\u02b2\5\u00a6T\2\u02b0\u02b2"+
		"\5\u00a8U\2\u02b1\u02af\3\2\2\2\u02b1\u02b0\3\2\2\2\u02b2\u00a5\3\2\2"+
		"\2\u02b3\u02b4\7\23\2\2\u02b4\u02b5\5x=\2\u02b5\u02b6\7\"\2\2\u02b6\u02b9"+
		"\5f\64\2\u02b7\u02b8\7\r\2\2\u02b8\u02ba\5f\64\2\u02b9\u02b7\3\2\2\2\u02b9"+
		"\u02ba\3\2\2\2\u02ba\u00a7\3\2\2\2\u02bb\u02bc\7\7\2\2\u02bc\u02bd\5x"+
		"=\2\u02bd\u02be\7\32\2\2\u02be\u02c3\5\u00aaV\2\u02bf\u02c0\7/\2\2\u02c0"+
		"\u02c2\5\u00aaV\2\u02c1\u02bf\3\2\2\2\u02c2\u02c5\3\2\2\2\u02c3\u02c1"+
		"\3\2\2\2\u02c3\u02c4\3\2\2\2\u02c4\u02c9\3\2\2\2\u02c5\u02c3\3\2\2\2\u02c6"+
		"\u02c7\7/\2\2\u02c7\u02c8\7\r\2\2\u02c8\u02ca\5\u00a2R\2\u02c9\u02c6\3"+
		"\2\2\2\u02c9\u02ca\3\2\2\2\u02ca\u02cb\3\2\2\2\u02cb\u02cc\7\16\2\2\u02cc"+
		"\u00a9\3\2\2\2\u02cd\u02ce\5`\61\2\u02ce\u02cf\7\60\2\2\u02cf\u02d0\5"+
		"f\64\2\u02d0\u00ab\3\2\2\2\u02d1\u02d5\5\u00aeX\2\u02d2\u02d5\5\u00b0"+
		"Y\2\u02d3\u02d5\5\u00b2Z\2\u02d4\u02d1\3\2\2\2\u02d4\u02d2\3\2\2\2\u02d4"+
		"\u02d3\3\2\2\2\u02d5\u00ad\3\2\2\2\u02d6\u02d7\7\'\2\2\u02d7\u02d8\5x"+
		"=\2\u02d8\u02d9\7\13\2\2\u02d9\u02da\5f\64\2\u02da\u00af\3\2\2\2\u02db"+
		"\u02dc\7 \2\2\u02dc\u02dd\5\u00a2R\2\u02dd\u02de\7%\2\2\u02de\u02df\5"+
		"x=\2\u02df\u00b1\3\2\2\2\u02e0\u02e1\7\20\2\2\u02e1\u02e2\5\6\4\2\u02e2"+
		"\u02e3\7-\2\2\u02e3\u02e4\5\u00b4[\2\u02e4\u02e5\t\b\2\2\u02e5\u02e6\5"+
		"\u00b6\\\2\u02e6\u02e7\7\13\2\2\u02e7\u02e8\5f\64\2\u02e8\u00b3\3\2\2"+
		"\2\u02e9\u02ea\5x=\2\u02ea\u00b5\3\2\2\2\u02eb\u02ec\5x=\2\u02ec\u00b7"+
		"\3\2\2\2\u02ed\u02ee\7(\2\2\u02ee\u02ef\5\u00ba^\2\u02ef\u02f0\7\13\2"+
		"\2\u02f0\u02f1\5f\64\2\u02f1\u00b9\3\2\2\2\u02f2\u02f7\5n8\2\u02f3\u02f4"+
		"\7.\2\2\u02f4\u02f6\5n8\2\u02f5\u02f3\3\2\2\2\u02f6\u02f9\3\2\2\2\u02f7"+
		"\u02f5\3\2\2\2\u02f7\u02f8\3\2\2\2\u02f8\u00bb\3\2\2\2\u02f9\u02f7\3\2"+
		"\2\2A\u00c6\u00d1\u00d3\u00de\u00eb\u00fa\u00fe\u0110\u0117\u011b\u0122"+
		"\u0127\u012d\u0139\u013f\u0145\u0157\u015e\u0167\u016b\u016d\u0174\u0177"+
		"\u017e\u018d\u0195\u01a5\u01b0\u01be\u01c3\u01ce\u01da\u01e5\u01ed\u01f3"+
		"\u0201\u0205\u020b\u0214\u0219\u021f\u0227\u0235\u023d\u0245\u024a\u0259"+
		"\u025e\u026a\u0275\u027c\u0280\u0285\u028c\u0292\u02a1\u02ac\u02b1\u02b9"+
		"\u02c3\u02c9\u02d4\u02f7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}