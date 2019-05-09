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


sum1: product1 sum2;
sum2: '+' product1 sum2
    | /** EPSILON */
    ;

product1: constant1 product2;
product2: '*' constant1 product2
        | /** EPSILON */
        ;

constant1: '(' sum1 ')'
         | NUMBER
         ;

//sum1: product1 sum2;
//sum2: '+' sum1
//    | /** EPSILON */
//    ;
//
//product1: constant1 product2;
//product2: '*' product1
//        | /** EPSILON */
//        ;
//
//constant1: '(' sum1 ')'
//         | NUMBER
//         ;

NUMBER: FLOAT | INTEGER;
FLOAT: [0-9]+ ('.' [0-9]+)?;
INTEGER: '0'..'8'+;

WS: [ \n]+ -> skip;