grammar Gram;

expression: additive EOF;

additive: multiplicative | sum | subtract;
sum: multiplicative '+' additive;
subtract : multiplicative '-' additive;

multiplicative: constant | product | divide;
product: constant '*' multiplicative;
divide : constant '/' multiplicative;

constant: NUMBER
        | '(' additive ')';

//sum: product sum2;
//sum2: '+' product sum2
//    | /** EPSILON */
//    ;
//
//product: constant product2;
//product2: '*' constant product2
//        | /** EPSILON */
//        ;
//
//constant: '(' sum ')'
//        | NUMBER
//        ;

NUMBER: FLOAT | INTEGER;
FLOAT: [0-9]+ ('.' [0-9]+)?;
INTEGER: '0'..'8'+;

WS: [ \n]+ -> skip;