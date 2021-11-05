grammar CPPGrammar;

@members {
int count = 0;
}

program: (description program) | EOF;

description: varDeclaration | classOp | main;

classOp: CLASS className LEFT_BRACE innerClass RIGHT_BRACE SEMICOLON;

className: ident;

innerClass: varDeclaration SEMICOLON innerClass |;

main: INT MAIN LEFT_ROUND RIGHT_ROUND LEFT_BRACE opsAndVars RIGHT_BRACE;

opsAndVars: (varDeclaration opsAndVars) | (operator opsAndVars) | ;

type: INT | CHAR | ident;

operator: simpleOperator | compoundOperator | emptyOperator;

emptyOperator: SEMICOLON;

compoundOperator: LEFT_BRACE opsAndVars RIGHT_BRACE;

simpleOperator: ifOp | expression;

ifOp: IF LEFT_ROUND expression RIGHT_ROUND operator elseOp;

elseOp: ELSE operator | ;

a0: object ASSIGN a1 | a1;

a1: a2 a11;
a2: a3 a22;
a3: a4 a33;
a4: a5 a44;
a5: a6 a55;
a6: MINUS a7 | PLUS a7 | a7;
a7: object | constant | (LEFT_ROUND a1 RIGHT_ROUND);

a11: EQUAL a1 | NOT_EQUAL a1 | ;
a22: LESS a2 | GREATER a2 | LE a2 | GE a2 |;
a33: LEFT_SHIFT a3 | RIGHT_SHIFT a3 | ;
a44: PLUS a4 | MINUS a4 | ;
a55: MOD a5 | DIV a5 | MUL a5 |;

expression: a0;

varDeclaration: type varList;

varList: var varDop;

varDop: (COMMA var varDop) |;

var: ident square p ;

p: ASSIGN a1 | ;

square:(LEFT_SQUARE constant RIGHT_SQUARE) |;

object: ident arrayOrClass;

arrayOrClass: (array field arrayOrClass) | ;

array: (LEFT_SQUARE expression RIGHT_SQUARE);

field: (DOT ident) | WHITESPACE;

ident: ID ;

constant: CONSTANT;

CONSTANT: [0-9]+;

WHITESPACE: (' ' | '\t' | '\n') -> skip;

CLASS: 'class';

LEFT_BRACE: '{';
RIGHT_BRACE: '}';
LEFT_ROUND: '(';
RIGHT_ROUND: ')';
LEFT_SQUARE: '[';
RIGHT_SQUARE: ']';

SEMICOLON: ';';
COMMA: ',';
DOT: '.';

INT: 'int';
CHAR: 'char';


MAIN: 'main';

IF: 'ifOp';
ELSE: 'elseOp';

ASSIGN: '=';
MINUS: '-';
PLUS: '+';
EQUAL: '==';
NOT_EQUAL: '!=';
LESS: '<';
GREATER: '>';
LE: '<=';
GE: '>=';
LEFT_SHIFT: '<<';
RIGHT_SHIFT: '>>';
MUL: '*';
DIV: '/';
MOD: '%';

ID: MYLETTERS;

fragment MYLETTERS: [a-zA-Z]+ ;