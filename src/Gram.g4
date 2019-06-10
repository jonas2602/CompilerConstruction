grammar Gram;

identifier: IDENT;
directive: IDENT;

// TODO: Complete Number rules if required (Page 13-15)
unsignedNumber
    : NUM_REAL
    | NUM_INT
    ;
sign: PLUS | MINUS;


// ASK: Order is relevant?
block: labelDeclarationPart constantDefinitionPart typeDefinitionPart variableDeclarationPart procedureAndFunctionDeclarationPart statementPart;


labelDeclarationPart
    : LABEL label (COMMA label)* SEMICOLON
    | /* EPSILON */
    ;
// TODO: ISO says: shall be in closed range 0..9999
label: NUM_INT;


constantDefinitionPart
    : CONST (constantDefinition SEMICOLON)+
    | /* EPSILON */
    ;
constantDefinition: identifier EQUAL constant;
// ASK: Function calls like chr() allowed?
constant
    : sign? (unsignedNumber | constantIdentifier)
    | STRING_LITERAL
    ;
constantIdentifier: identifier;



typeDefinitionPart
    : TYPE ( typeDefinition SEMICOLON)+
    | /* EPSILON */
    ;
typeDefinition: identifier EQUAL typeDenoter;
// ASK: Function/Procedure Types allowed? (param, param, param):returnType
typeDenoter: typeIdentifier | newType;
newType: newOrdinalType | newStructuredType | newPointerType;
// replace following rules?
simpleTypeIdentifier: typeIdentifier;
structuredTypeIdentifier: typeIdentifier;
pointerTypeIdentifier: typeIdentifier;
typeIdentifier: identifier;

simpleType: ordinalType | realTypeIdentifier;
ordinalType: newOrdinalType | ordinalTypeIdentifier;
newOrdinalType: enumeratedType | subrangeType;
// replace?
ordinalTypeIdentifier: typeIdentifier;
realTypeIdentifier: typeIdentifier;
enumeratedType: LPARENTHESE identifierList RPARENTHESE;
identifierList: identifier (COMMA identifier)*;
subrangeType: constant DOTDOT constant;

structuredType: newStructuredType | structuredTypeIdentifier;
newStructuredType: PACKED? unpackedStructuredType;
unpackedStructuredType: arrayType | recordType | setType | fileType;
arrayType: ARRAY LBRACKET indexType (COMMA indexType)* RBRACKET OF  componentType;
indexType: ordinalType;
componentType: typeDenoter;
recordType: RECORD fieldList END;
fieldList
    : (fixedPart (SEMICOLON variantPart)? | variantPart) SEMICOLON?
    | /* EPSILON */
    ;
fixedPart: recordSection (SEMICOLON recordSection)*;
recordSection: identifierList COLON typeDenoter;
fieldIdentifier: identifier;
variantPart: CASE variantSelector OF variant (SEMICOLON variant)*;
variantSelector: (tagField COLON)? tagType;
tagField: identifier;
variant: caseConstantList COLON LPARENTHESE fieldList RPARENTHESE;
tagType: ordinalTypeIdentifier;
caseConstantList: caseConstant (COMMA caseConstant)*;
caseConstant: constant;

setType: SET OF baseType;
baseType: ordinalType;

fileType: FILE OF componentType;

pointerType: newPointerType | pointerTypeIdentifier;
newPointerType: POINTER domainType;
domainType: typeIdentifier;



variableDeclarationPart
    : VAR (variableDeclaration SEMICOLON)+
    | /* EPSILON */
    ;
variableDeclaration: identifierList COLON typeDenoter;

// allows random combinations of the variable access types
variableAccess: variableIdentifier (indexedVariable | fieldDesignator | pointerVariable)*;
// var
variableIdentifier: identifier;
// var[exp,exp,...]
indexedVariable: LBRACKET expression (COMMA expression)* RBRACKET;
// var.id
fieldDesignator: DOT identifier;
// var^
pointerVariable: POINTER;

//variableAccess: entireVariable | componentVariable | identifiedVariable | bufferVariable;
//entireVariable: variableIdentifier;
//variableIdentifier: identifier;
//componentVariable: indexedVariable | fieldDesignator;
//indexedVariable: arrayVariable LBRACKET indexExpression (COMMA indexExpression)* RBRACKET;
//arrayVariable: variableAccess;
//indexExpression: expression;
//fieldDesignator
//    : recordVariable DOT fieldSpecifier
//    | fieldDesignatorIdentifier;
//recordVariable: variableAccess;
//fieldSpecifier: fieldIdentifier;
//bufferVariable: fileVariable POINTER;
//fileVariable: variableAccess;
//
//identifiedVariable: pointerVariable POINTER;
//pointerVariable: variableAccess;



procedureAndFunctionDeclarationPart: ((procedureDeclaration | functionDeclaration) SEMICOLON)*;
procedureDeclaration
    : procedureHeading SEMICOLON directive
    | procedureIdentification SEMICOLON procedureBlock
    | procedureHeading SEMICOLON procedureBlock
    ;
procedureHeading: PROCEDURE identifier formalParameterList?;
procedureIdentification: PROCEDURE procedureIdentifier;
procedureIdentifier: identifier;
procedureBlock: block;

functionDeclaration
    : functionHeading SEMICOLON directive
    | functionIdentification SEMICOLON functionBlock
    | functionHeading SEMICOLON functionBlock
    ;
functionHeading: FUNCTION identifier formalParameterList? COLON resultType;
functionIdentification: FUNCTION functionIdentifier;
functionIdentifier: identifier;
resultType: simpleTypeIdentifier | pointerTypeIdentifier;
functionBlock: block;

formalParameterList: LPARENTHESE formalParameterSection (SEMICOLON formalParameterSection)* RPARENTHESE;
formalParameterSection
    : valueParameterSpecification
    | variableParameterSpecification
    | proceduralParameterSpecification
    | functionalParameterSpecification
    | conformantArrayParameterSpecification
    ;
valueParameterSpecification: identifierList COLON typeIdentifier;
variableParameterSpecification: VAR identifierList COLON typeIdentifier;
proceduralParameterSpecification: procedureHeading;
functionalParameterSpecification: functionHeading;

conformantArrayParameterSpecification
    : valueConformantArraySpecification
    | variableConformantArraySpecification
    ;
valueConformantArraySpecification: identifierList COLON conformantArraySchema;
variableConformantArraySpecification: VAR identifierList COLON conformantArraySchema;
conformantArraySchema
    : packedConformantArraySchema
    | unpackedConformantArraySchema
    ;
packedConformantArraySchema: PACKED ARRAY indexTypeSpecification? OF typeIdentifier;
unpackedConformantArraySchema: ARRAY (indexTypeSpecification (SEMICOLON indexTypeSpecification)*)? OF (typeIdentifier | conformantArraySchema);
indexTypeSpecification: identifier DOTDOT identifier COLON ordinalTypeIdentifier;

factor
    : boundIdentifier
    | variableAccess
    | unsignedConstant
    | functionDesignator
    | setConstructor
    | LPARENTHESE expression RPARENTHESE
    | NOT factor
    ;

boundIdentifier: identifier;

expression: simpleExpression (relationalOperator simpleExpression)?;
simpleExpression: sign? term (addingOperator term)*;
term: factor (multiplyingOperator factor)*;
unsignedConstant
    : unsignedNumber
    | STRING_LITERAL
    | constantIdentifier
    | NIL
    ;
setConstructor: LBRACKET (memberDesignator (SEMICOLON memberDesignator)*)? RBRACKET;
memberDesignator: expression (DOTDOT expression)?;

multiplyingOperator: STAR | SLASH | DIV | MOD | AND;
addingOperator: PLUS | MINUS | OR;
relationalOperator: EQUAL | NOTEQUAL | LESS | GREATER | LESSEQUAL | GREATEREQUAL | IN;

booleanExpression: expression;

functionDesignator: functionIdentifier actualParameterList?;
actualParameterList: LPARENTHESE actualParameter (COMMA actualParameter)* RPARENTHESE;
actualParameter: expression | variableAccess | procedureIdentifier | functionIdentifier;

statement: (label COLON)? (simpleStatement | structuredStatement);
simpleStatement
    : emptyStatement
    | assignmentStatement
    | procedureStatement
    | gotoStatement
    ;
emptyStatement: /* EPSILON */;
assignmentStatement: (variableAccess | functionIdentifier) ASSIGN expression;
procedureStatement: procedureIdentifier (actualParameterList* | readParameterList | readlnParameterList | writeParameterList | writelnParameterList);
gotoStatement: GOTO label;
structuredStatement
    : compoundStatement
    | conditionalStatement
    | repetitiveStatement
    | withStatement
    ;
statementSequence: statement (SEMICOLON statement)*;

// could this level get removed?
statementPart: compoundStatement;

compoundStatement: BEGIN statementSequence END;
conditionalStatement: ifStatement | caseStatement;

ifStatement: IF booleanExpression THEN statement (elsePart)?;
elsePart: ELSE statement;

caseStatement: CASE caseIndex OF caseListElement (SEMICOLON caseListElement)* SEMICOLON? END;
caseListElement: caseConstantList COLON statement;
caseIndex: expression;

repetitiveStatement
    : repeatStatement
    | whileStatement
    | forStatement
    ;
repeatStatement: REPEAT statementSequence UNTIL booleanExpression;
whileStatement: WHILE booleanExpression DO statement;
forStatement: FOR controlVariable ASSIGN initialValue (TO | DOWNTO) finalValue DO statement;
controlVariable: variableIdentifier;
initialValue: expression;
finalValue: expression;

withStatement: WITH recordVariableList DO statement;
recordVariableList: variableAccess (COMMA variableAccess)*;
fieldDesignatorIdentifier: identifier;

readParameterList: LPARENTHESE (variableAccess COMMA)? variableAccess (COMMA variableAccess)* RPARENTHESE;
readlnParameterList
    : LPARENTHESE (variableAccess) (COMMA variableAccess)* RPARENTHESE
    | /* EPSILON */
    ;

writeParameterList: LPARENTHESE (variableAccess COMMA)? writeParameter (COMMA writeParameter)* RPARENTHESE;
writeParameter: expression (COLON expression (COLON expression)?)?;
writelnParameterList
    : LPARENTHESE (variableAccess | writeParameter) (COMMA writeParameter)* RPARENTHESE
    | /* EPSILON */
    ;

program: programHeading SEMICOLON programBlock;
programHeading: PROGRAM identifier (LPARENTHESE programParameterList RPARENTHESE )?;
programParameterList: identifierList;
programBlock: block;



AND: A N D;
ARRAY: A R R A Y;
BEGIN: B E G I N;
CASE: C A S E;
CONST: C O N S T;
DIV: D I V;
DO: D O;
DOWNTO: D O W N T O;
ELSE: E L S E;
END: E N D;
FILE: F I L E;
FOR: F O R;
FUNCTION: F U N C T I O N;
GOTO: G O T O;
IF: I F;
IN: I N;
LABEL: L A B E L;
MOD: M O D;
NIL: N I L;
NOT: N O T;
OF: O F;
OR: O R;
PACKED: P A C K E D;
PROCEDURE: P R O C E D U R E;
PROGRAM: P R O G R A M;
RECORD: R E C O R D;
REPEAT: R E P E A T;
SET: S E T;
THEN: T H E N;
TO: T O;
TYPE: T Y P E;
UNTIL: U N T I L;
VAR: V A R;
WHILE: W H I L E;
WITH: W I T H;

//T_CHAR: C H A R;
//T_INTEGER: I N T E G E R;
//T_BOOLEAN: B O O L E A N;
//T_REAL: R E A L;
//T_STRING: S T R I N G;

PLUS: '+';
MINUS: '-';
STAR: '*';
SLASH: '/';
EQUAL: '=';
LESS: '<';
GREATER: '>';
LBRACKET: '[' | '(.'; // Includes Alternative Token
RBRACKET: ']' | '.)'; // Includes Alternative Token
DOT:'.';
COMMA: ',';
COLON: ':';
SEMICOLON: ';';
POINTER: '^' | '@'; // Includes Alternative Token
LPARENTHESE: '(';
RPARENTHESE: ')';
NOTEQUAL: '<>';
LESSEQUAL: '<=';
GREATEREQUAL: '>=';
ASSIGN: ':=';
DOTDOT: '..';

LBRACE: '{';
RBRACE: '}';

NUM_INT: [0-9]+;
NUM_REAL: [0-9]+ (('.' [0-9]+ EXPONENT?)? | EXPONENT);
STRING_LITERAL: '\'' ('\'\'' | ~ ('\''))* '\'';

IDENT: [a-zA-Z][a-zA-Z0-9_]*;

fragment EXPONENT
   : (E) ('+' | '-')? [0-9]+
   ;

// match case insensitive characters
fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];

// TODO: Comments have additional notes (Page 16 top)
COMMENT1: '(*' .*? '*)' -> skip;
COMMENT2: '{' .*? '}' -> skip;

WS: [ \n\t\r]+ -> skip;