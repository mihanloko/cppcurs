grammar CPPGrammar;

@header {
import loko.LL;
}

@members {
public LL ll = new LL();
}

program: (description program) | EOF;

description: varDeclaration | classOp | main;

classOp: CLASS className {ll.setClass($className.text);} LEFT_BRACE innerClass RIGHT_BRACE SEMICOLON {ll.back();};

className: ident;

innerClass: varDeclaration SEMICOLON innerClass |;

main: INT MAIN LEFT_ROUND RIGHT_ROUND {ll.startMain();} {ll.genProc();} LEFT_BRACE opsAndVars RIGHT_BRACE {ll.back();} {ll.genEndp();};

opsAndVars: (varDeclaration opsAndVars) | (operator opsAndVars) | ;

type: INT {ll.saveType("int");} | CHAR {ll.saveType("char");}  | ident {ll.checkType($ident.text);} {ll.saveType($ident.text);} ;

operator: simpleOperator | compoundOperator | emptyOperator;

emptyOperator: SEMICOLON;

compoundOperator: {ll.dnew();} LEFT_BRACE opsAndVars RIGHT_BRACE {ll.back();};

simpleOperator: ifOp | expression;

ifOp: IF LEFT_ROUND expression RIGHT_ROUND {ll.genIf();} {ll.genSetTrueAddr();} {ll.checkCondition();} operator {ll.genGoto();} elseOp;

elseOp: ELSE {ll.genSetFalseAddr();} operator {ll.genSetAddrNop();} {ll.genFormIf();} | {ll.genSetAddrNop();} {ll.genFormIf();};

a0: object ASSIGN a1 {ll.matchAssign();} {ll.genAssigment();} | a1;

a1: a2 a11;
a2: a3 a22;
a3: a4 a33;
a4: a5 a44;
a5: a6 a55;
a6: MINUS a7 | PLUS a7 | a7;
a7: object | constant {ll.pushConst();} {ll.genPush($constant.text);} | (LEFT_ROUND a1 RIGHT_ROUND);

a11: EQUAL a1 {ll.match1();} {ll.genEqual();} | NOT_EQUAL a1 {ll.match1();} {ll.genNotEqual();} | ;
a22: LESS a2 {ll.match2();} {ll.genLess();} | GREATER a2 {ll.match2();} {ll.genGreater();} | LE a2 {ll.match2();} {ll.genLe();} | GE a2 {ll.match2();} {ll.genGe();} |;
a33: LEFT_SHIFT a3 {ll.match3();} {ll.genLeftShift();} | RIGHT_SHIFT a3 {ll.match3();} {ll.genRightShift();} | ;
a44: PLUS a4 {ll.match4();} {ll.genPlus();} | MINUS a4 {ll.match4();} {ll.genMinus();} | ;
a55: MOD a5 {ll.match5();} {ll.genMod();} | DIV a5 {ll.match5();} {ll.genDiv();} | MUL a5 {ll.match5();} {ll.genMul();} |;

expression: a0;

varDeclaration: type varList;

varList: var varDop;

varDop: (COMMA var varDop) |;

var: ident {ll.add($ident.text);} square p ;

p: ASSIGN a1 {ll.matchAssign();} | ;

square:(LEFT_SQUARE constant RIGHT_SQUARE {ll.makeArray();}) |;

object: ident {ll.genPush($ident.text);} {ll.find($ident.text);} {ll.push($ident.text);} arrayOrClass;

arrayOrClass: (array field arrayOrClass) | ;

array:  LEFT_SQUARE {ll.checkArray();} expression {ll.genIdx();} {ll.matchConst();} RIGHT_SQUARE;

field:  DOT ident {ll.genPush($ident.text);} {ll.findField();} {ll.push($ident.text);} {ll.genDot();} | WHITESPACE;

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