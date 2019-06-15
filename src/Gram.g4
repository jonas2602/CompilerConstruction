grammar Gram;

startRule: program EOF;

identifier: IDENT;
identifierList: identifier (COMMA identifier)*;

directive: IDENT;

// TODO: Complete Number rules if required (Page 13-15)
unsignedNumber
    : NUM_REAL
    | NUM_INT
    ;
sign: PLUS | MINUS;


// ASK: Order is relevant?
block: labelDeclarationPart constantDefinitionPart typeDefinitionPart variableDeclarationPart procedureAndFunctionDeclarationPart compoundStatement;


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
typeIdentifier: identifier;

ordinalType: newOrdinalType | typeIdentifier;
newOrdinalType: enumeratedType | subrangeType;
// (type, type, type, ...)
enumeratedType: LPARENTHESE identifierList RPARENTHESE;
// const .. const
subrangeType: constant DOTDOT constant;

// packed or unpacked complex types
// packed -> compiler uses as little memoty as possible
newStructuredType: PACKED? (arrayType | recordType | setType | fileType);
// [ordType, ordType, ...] of anyType
// ASK: How to implement strings? "string[100]" allowed or "array[100] of char" (ISO page 24-25)
// ASK: "array of integer" possible? missing length?
arrayType: ARRAY LBRACKET ordinalType (COMMA ordinalType)* RBRACKET OF  typeDenoter;
// equivalent to structs in C?
recordType: RECORD fieldList END;
fieldList
    : (fixedPart (SEMICOLON variantPart)? | variantPart) SEMICOLON?
    | /* EPSILON */
    ;
fixedPart: recordSection (SEMICOLON recordSection)*;
// id, id, ... : type
// e.g.: name, firstname : string;
recordSection: identifierList COLON typeDenoter;

variantPart: CASE variantSelector OF variant (SEMICOLON variant)*;
variantSelector: (identifier COLON)? typeIdentifier;
variant: caseConstantList COLON LPARENTHESE fieldList RPARENTHESE;
caseConstantList: constant (COMMA constant)*;

setType: SET OF ordinalType;
fileType: FILE OF typeDenoter;

newPointerType: POINTER typeIdentifier;



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
resultType: typeIdentifier;
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
unpackedConformantArraySchema: ARRAY LBRACKET indexTypeSpecification (SEMICOLON indexTypeSpecification)* RBRACKET OF (typeIdentifier | conformantArraySchema);
indexTypeSpecification: identifier DOTDOT identifier COLON typeIdentifier;

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
statementSequence: statement (SEMICOLON statement)*;

simpleStatement
    : assignmentStatement
    | procedureStatement
    | gotoStatement
    | /* EPSILON */
    ;
// ASK: WTF? Assignment to function? like lambda expressions?
// e.g. test[12] := 1+2
assignmentStatement: (variableAccess | functionIdentifier) ASSIGN expression;
// e.g. println("Hello", "World")
procedureStatement: procedureIdentifier (actualParameterList? | readParameterList | readlnParameterList | writeParameterList | writelnParameterList);
// goto id
// ASK: Labels are defined as Numbers. Are names also valid? (used in the lecture)
gotoStatement: GOTO label;

structuredStatement
    : compoundStatement
    | conditionalStatement
    | repetitiveStatement
    | withStatement
    ;
compoundStatement: BEGIN statementSequence END;
conditionalStatement: ifStatement | caseStatement;

ifStatement: IF booleanExpression THEN statement (ELSE statement)?;

caseStatement: CASE expression OF caseListElement (SEMICOLON caseListElement)* SEMICOLON? END;
caseListElement: caseConstantList COLON statement;

repetitiveStatement
    : repeatStatement
    | whileStatement
    | forStatement
    ;
// equivalent of do-while?
repeatStatement: REPEAT statementSequence UNTIL booleanExpression;
whileStatement: WHILE booleanExpression DO statement;
forStatement: FOR variableIdentifier ASSIGN expression (TO | DOWNTO) expression DO statement;

// The with statements serves to access the elements of a record or object or class, without having to specify the element’s name each time
// pretty certain there is an equivalent in C++ called "using" or sth like this
withStatement: WITH recordVariableList DO statement;
recordVariableList: variableAccess (COMMA variableAccess)*;

// optional fileVariable as first element
// could get combined?
readParameterList: LPARENTHESE variableAccess (COMMA variableAccess)* RPARENTHESE;
readlnParameterList
    : LPARENTHESE variableAccess (COMMA variableAccess)* RPARENTHESE
    | /* EPSILON */
    ;

// optional variable is fileVariable
writeParameterList: LPARENTHESE (variableAccess COMMA)? writeParameter (COMMA writeParameter)* RPARENTHESE;
writeParameter: expression (COLON expression (COLON expression)?)?;
writelnParameterList
    : LPARENTHESE (variableAccess | writeParameter) (COMMA writeParameter)* RPARENTHESE
    | /* EPSILON */
    ;


// ASK: Multiple "program" definitions in the same file?
program: programHeading SEMICOLON programBlock DOT;
programHeading: PROGRAM identifier (LPARENTHESE identifierList RPARENTHESE )?;
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