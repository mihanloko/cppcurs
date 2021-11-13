package loko;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.System.exit;
import static loko.OperandType.*;
import static loko.Tri.*;
import static loko.Type.*;
import static loko.TypeName.*;

public class LL {
    private Scanner scanner = new Scanner();
    private Tree magazineTypes[] = new Tree[5000];
    int typePos = 0;
    private List<Triad> triads = new ArrayList<>();
    private List<Operand> operands = new ArrayList<>();
    private List<IfData> ifData = new ArrayList<>();
    private Tree lastType;
    private String lastLex;

    public LL() {
        Node node = new Node();
        node.lex = "int";
        node.type = ObjStructDefinition;
        Tree currentNode = new Tree(node);
        node = new Node();
        node.lex = "char";
        node.type = ObjStructDefinition;
        currentNode.SetLeft(node);
        Tree.cur = currentNode.getLeft();
    }

    public void test(String t) {
        System.out.println(t);
    }

    public void setClass(String className) {
        if (Tree.cur.FindUp(className) != null) {
            scanner.printSemError("Идентификатор " + className + "уже объявлен", className.length());
            exit(0);
        }
        Tree.cur.openBlock(className, ObjStructDefinition);
    }

    public void back() {
        Tree.cur.goUp();
    }

    public void startMain() {
        Tree.cur.openBlock("main", ObjMain);
    }

    public void saveType(String lastLex) {
        magazineTypes[typePos++] = Tree.cur.findClassDefinition(lastLex);
    }

    public void checkType(String lastLex) {
        Tree.cur.findClassDefinition(lastLex);
    }

    public void dnew() {
        Tree.cur.openBlock("", ObjFictive);
    }

    public void genProc(String name) {
        triads.add(new Triad(TRI_PROC, new Operand(name), null));
    }

    public void genEndp(String name) {
        triads.add(new Triad(TRI_ENDP, new Operand(name), null));
    }

    public void genIf() {
        Triad ifTriad = new Triad(TRI_IF, null, null);
        triads.add(ifTriad);
        ifData.add(new IfData());
        ifData.get(ifData.size() - 1).ifOperand = ifTriad;
        ifData.get(ifData.size() - 1).falseAddress = -1;
        ifData.get(ifData.size() - 1).trueAddress = -1;
        ifData.get(ifData.size() - 1).nopOperand = -1;
    }

    public void genSetTrueAddr() {
        ifData.get(ifData.size() - 1).trueAddress = triads.size();
    }

    public void checkCondition() {
    }

    public void genGoto() {
        triads.add(new Triad(TRI_JMP, new Operand(ifData.get(ifData.size() - 1).nopOperand), null));
        ifData.get(ifData.size() - 1).jmpTriad = triads.get(triads.size() - 1);
    }

    public void genSetFalseAddr() {
        ifData.get(ifData.size() - 1).falseAddress = triads.size();
    }

    public void genSetAddrNop() {
        triads.add(new Triad(TRI_NOP));
        ifData.get(ifData.size() - 1).nopOperand = triads.size() - 1;
    }

    public void genFormIf() {
        ifData.get(ifData.size() - 1).ifOperand.setOperand1(new Operand(ifData.get(ifData.size() - 1).trueAddress));
        ifData.get(ifData.size() - 1).ifOperand.setOperand2(new Operand(ifData.get(ifData.size() - 1).falseAddress == -1 ? ifData.get(ifData.size() - 1).nopOperand : ifData.get(ifData.size() - 1).falseAddress));
        ifData.get(ifData.size() - 1).jmpTriad.getOperand1().value.address = ifData.get(ifData.size() - 1).nopOperand;
        ifData.remove(ifData.size() - 1);
    }

    public void matchAssign() {
        magazineTypes[typePos - 1] = Tree.cur.checkAssignCompatible(magazineTypes[typePos - 1],
                magazineTypes[typePos - 2]);
        checkAssignCast(magazineTypes[typePos - 1], magazineTypes[typePos - 2]);
        typePos--;
    }

    public void genAssigment() {
        generateArithmeticTriad(TRI_ASSIGNMENT);
    }

    public void pushConst() {
        magazineTypes[typePos++] = Tree.cur.makeIntVar();
    }

    public void genPush(String lex) {
        operands.add(new Operand(new Node(lex)));
    }

    public void match1() {
        magazineTypes[typePos - 1] = Tree.cur.check1Compatible(magazineTypes[typePos - 1],
                magazineTypes[typePos - 2]);
        checkCast(magazineTypes[typePos - 2], magazineTypes[typePos - 1]);
        typePos--;
    }

    public void genEqual() {
        generateArithmeticTriad(TRI_EQ);
    }

    public void genNotEqual() {
        generateArithmeticTriad(TRI_NEQ);
    }

    public void match2() {
        magazineTypes[typePos - 1] = Tree.cur.check2Compatible(magazineTypes[typePos - 1],
                magazineTypes[typePos - 2]);
        checkCast(magazineTypes[typePos - 2], magazineTypes[typePos - 1]);
        typePos--;
    }

    public void genLess() {
        generateArithmeticTriad(TRI_LT);
    }

    public void genGreater() {
        generateArithmeticTriad(TRI_GT);
    }

    public void genLe() {
        generateArithmeticTriad(TRI_LE);
    }

    public void genGe() {
        generateArithmeticTriad(TRI_GE);
    }

    public void match3() {
        magazineTypes[typePos - 1] = Tree.cur.check3Compatible(magazineTypes[typePos - 1],
                magazineTypes[typePos - 2]);
        checkCast(magazineTypes[typePos - 2], magazineTypes[typePos - 1]);
        typePos--;
    }

    public void match4() {
        magazineTypes[typePos - 1] = Tree.cur.check4Compatible(magazineTypes[typePos - 1],
                magazineTypes[typePos - 2]);
        checkCast(magazineTypes[typePos - 2], magazineTypes[typePos - 1]);
        typePos--;
    }

    public void match5() {
        magazineTypes[typePos - 1] = Tree.cur.check5Compatible(magazineTypes[typePos - 1],
                magazineTypes[typePos - 2]);
        checkCast(magazineTypes[typePos - 2], magazineTypes[typePos - 1]);
        typePos--;
    }

    public void genLeftShift() {
        generateArithmeticTriad(TRI_LEFT_SHIFT);
    }

    public void genRightShift() {
        generateArithmeticTriad(TRI_RIGHT_SHIFT);
    }

    public void genPlus() {
        generateArithmeticTriad(TRI_PLUS);
    }

    public void genMinus() {
        generateArithmeticTriad(TRI_MINUS);
    }

    public void genMod() {
        generateArithmeticTriad(TRI_MOD);
    }

    public void genDiv() {
        generateArithmeticTriad(TRI_DIV);
    }

    public void genMul() {
        generateArithmeticTriad(TRI_MUL);
    }

    public void add(String s) {
        lastType = Tree.cur.createVar(magazineTypes[typePos - 1], s);
    }

    public void makeArray() {
        if (Tree.flagInterpret)
            lastType.makeVarArray();
    }

    public void find(String lastLex) {
        lastType = Tree.cur.FindUp(lastLex);
        if (Tree.flagInterpret && lastType == null) {
            scanner.printSemError("Идентификатор " + lastLex + " не объявлен", 0);
        }
    }

    public void push(String lastLex) {
        magazineTypes[typePos++] = Tree.cur.FindUp(lastLex);
        this.lastLex = lastLex;
    }

    public void checkArray() {
        if (lastType.getNode().type != ObjArray)
        scanner.printSemError("Объект " + lastType.getNode().lex + " не является массивом", lastLex.length());
    }

    public void genIdx() {
        generateArithmeticTriad(TRI_IDX);
    }

    public void matchConst() {
        if (magazineTypes[typePos - 1].getNode().typeName != ObjChar &&
                magazineTypes[typePos - 1].getNode().typeName != ObjInt)
        scanner.printSemError("Значение выражения не явлется целым типом", lastLex.length());

    }

    public void findField() {
        if (Tree.flagInterpret) {
            lastType = lastType.findFiled(lastType, lastLex);
        }
    }

    public void genDot() {
        generateArithmeticTriad(TRI_DOT);
    }

    public void startFunc(String name) {
        Tree.cur.openBlock(name, ObjFunc);
    }

    public void genMov() {
        triads.add(new Triad(TRI_MOV, new Operand("eax"), getOperand()));
        operands.add(new Operand(getLastTriadAddr()));
        triads.add(new Triad(TRI_RET));
    }

    public void checkFunc(String lastLex) {
        lastType = Tree.cur.FindUp(lastLex);
        if (Tree.flagInterpret && (lastType == null || lastType.getNode().type != ObjFunc)) {
            scanner.printSemError("Функция " + lastLex + " не объявлена", 0);
        }
    }

    public void callFunc(String name) {
        Operand operand = new Operand(name);
        operand.value.node.type = ObjFunc;
        triads.add(new Triad(TRI_CALL, operand, null));
        operands.add(new Operand(getLastTriadAddr()));
    }

    void checkAssignCast(Tree first, Tree second) {
        if (!Tree.flagInterpret) {
            return;
        }
        if (first.getNode().typeName != second.getNode().typeName) {
            if (second.getNode().typeName == ObjChar && first.getNode().typeName == ObjInt) {
                triads.add(new Triad(TRI_INT_CHAR, operands.get(operands.size() - 1), null));
                operands.remove(operands.size() - 1);
                operands.add(new Operand(getLastTriadAddr()));
            } else if (second.getNode().typeName == ObjInt && first.getNode().typeName == ObjChar) {
                triads.add(new Triad(TRI_CHAR_INT, operands.get(operands.size() - 1), null));
                operands.remove(operands.size() - 1);
                operands.add(new Operand(getLastTriadAddr()));
            }
        }
    }

    void checkCast(Tree first, Tree second) {
        if (!Tree.flagInterpret) {
            return;
        }
        if (first.getNode().typeName != second.getNode().typeName) {
            if (first.getNode().typeName == ObjChar) {
                triads.add(new Triad(TRI_CHAR_INT, operands.get(operands.size() - 1), null));
                operands.remove(operands.size() - 1);
                operands.add(new Operand(getLastTriadAddr()));
            } else if (second.getNode().typeName == ObjChar) {
                triads.add(new Triad(TRI_CHAR_INT, operands.get(operands.size() - 1), null));
                operands.remove(operands.size() - 1);
                operands.add(new Operand(getLastTriadAddr()));
            }
        }
    }

    private int getLastTriadAddr() {
        return triads.size() - 1;
    }

    private void generateArithmeticTriad(Tri operation) {
        Operand operand2 = getOperand();
        Operand operand1 = getOperand();
        triads.add(new Triad(operation, operand1, operand2));
        operands.add(new Operand(getLastTriadAddr()));
    }

    private Operand getOperand() {
        return getTopValue(operands, "operands");
    }

    private <T> T getTopValue(List<T> st, String name) {
        if (st.isEmpty())
            throw new RuntimeException("Empty collection " + name);
        var res = st.get(st.size() - 1);
        st.remove(st.size() - 1);
        return res;
    }

    void optimize() {
        for (int i = 0; i < triads.size(); i++) {
            if (triads.get(i) == null) {
                continue;
            }
            Operand op = tryOptimizeConst(triads.get(i));
            if (op != null) {
                triads.set(i, null);
                for (int j = i + 1; j < triads.size(); j++) {
                    Operand o1 = triads.get(j).getOperand1();
                    Operand o2 = triads.get(j).getOperand2();
                    if (o1 != null && o1.type == ADDRESS && o1.value.address == i) {
                        triads.get(j).setOperand1(op);
                    }
                    if (o2 != null && o2.type == ADDRESS && o2.value.address == i) {
                        triads.get(j).setOperand2(op);
                    }
                }
            }
        }

        for (int i = 0; i < triads.size(); i++) {
            if (triads.get(i) == null) {
                continue;
            }
            int res = tryOptimizeDouble(triads.get(i), i);
            if (res > 0) {
                triads.set(res, null);
                for (int j = res + 1; j < triads.size(); j++) {
                    if (triads.get(j) == null) {
                        continue;
                    }
                    Operand o1 = triads.get(j).getOperand1();
                    Operand o2 = triads.get(j).getOperand2();
                    if (o1 != null && o1.type == ADDRESS && o1.value.address == res) {
                        triads.get(j).setOperand1(new Operand(i));
                    }
                    if (o2 != null && o2.type == ADDRESS && o2.value.address == res) {
                        triads.get(j).setOperand2(new Operand(i));
                    }
                }
                i = 0;
            }
        }

        for (int i = 0; i < triads.size(); i++) {
            if (triads.get(i) == null) {
                continue;
            }
            tryOptimizeFunction(triads.get(i), i);
        }

    }

    void tryOptimizeFunction (Triad triad, int idx) {
        if (triad.operation != TRI_CALL) {
            return;
        }
        String name = triad.getOperand1().value.node.lex;
        if (!checkFunctionForRecurcive(name)) {
            return;
        }
        List<Triad> triadsToInsert = new ArrayList<>();
        boolean insert = false;
        for (Triad tri: triads) {
            if (tri == null) {
                continue;
            }
            if (tri.operation == TRI_PROC && tri.operand1.value.node.lex.equals(name)) {
                insert = true;
                continue;
            }
            if (tri.operation == TRI_ENDP && tri.operand1.value.node.lex.equals(name)) {
                break;
            }
            if (insert) {
                triadsToInsert.add(tri.copy());
            }
        }
        triads.remove(idx);
        triads.addAll(triadsToInsert);
    }

    boolean checkFunctionForRecurcive(String name) {
        boolean check = false;
        for (Triad triad : triads) {
            if (triad == null) {
                continue;
            }
            if (triad.operation == TRI_PROC && Objects.equals(triad.operand1.value.node.lex, name)) {
                check = true;
            }
            if (triad.operation == TRI_ENDP && Objects.equals(triad.operand1.value.node.lex, name)) {
                break;
            }
            if (check && triad.operation == TRI_CALL && Objects.equals(triad.operand1.value.node.lex, name)) {
                return false;
            }
        }
        return true;
    }

    Operand tryOptimizeConst(Triad triad) {
        Operand o1 = triad.getOperand1();
        Operand o2 = triad.getOperand2();
        if (o1 != null && o1.type == NODE && o2 != null && o2.type == NODE) {
            Node n1 = o1.value.node;
            Node n2 = o2.value.node;
            if (isNumeric(n1.lex) && isNumeric(n2.lex)) {
                int v1 = Integer.parseInt(n1.lex);
                int v2 = Integer.parseInt(n2.lex);
                int res = 0;
                switch (triad.getOperation()) {
                    case TRI_PLUS:
                        res = v1 + v2;
                        break;
                    case TRI_MINUS:
                        res = v1 - v2;
                        break;
                    case TRI_MUL:
                        res = v1 * v2;
                        break;
                    case TRI_DIV:
                        res = v1 / v2;
                        break;
                    case TRI_MOD:
                        res = v1 % v2;
                        break;
                    case TRI_LEFT_SHIFT:
                        res = v1 << v2;
                        break;
                    case TRI_RIGHT_SHIFT:
                        res = v1 >> v2;
                        break;
                }
                return new Operand(String.valueOf(res));
            }
        }
        return null;
    }

    int tryOptimizeDouble(Triad triad, int i) {
        for (int j = i + 1; j < triads.size(); j++) {
            if (triads.get(j) == null || triads.get(j).getOperation() == TRI_NOP) {
                continue;
            }
            if (triads.get(j).getOperand1() != null && triads.get(j).getOperand1().equals(triad.getOperand1())
                    && triads.get(j).getOperand2() != null && triads.get(j).getOperand2().equals(triad.getOperand2())
                    && checkNothingChanged(i, j, triads.get(j))) {
                return j;
            }
        }
        return -1;
    }

    boolean checkNothingChanged(int i, int j, Triad pTriad) {
        List<Operand> used = new ArrayList<>();
        findAllOperands(pTriad.getOperand1(), used);
        findAllOperands(pTriad.getOperand2(), used);
        for (int k = i + 1; k < j; k++) {
            if (triads.get(k) == null) {
                continue;
            }
            if (triads.get(k).getOperation() == TRI_ASSIGNMENT) {
                for(Operand o: used) {
                    if (Objects.equals(o.value.node.lex, triads.get(k).getOperand1().value.node.lex)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void findAllOperands(Operand op, List<Operand> vector1) {
        if (op.type == ADDRESS) {
            findAllOperands(triads.get(op.value.address).getOperand1(), vector1);
            findAllOperands(triads.get(op.value.address).getOperand2(), vector1);
        } else if (op.type == NODE) {
            vector1.add(op);
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    void outTriads() {
        int skipped = 0;
        System.out.println( "Триады: ");
        for (int i = 0; i < triads.size(); i++) {
            if (triads.get(i) == null) {
                skipped++;
                continue;
            }
            System.out.print(i + ") ");
            outOneTriad(triads.get(i));
            System.out.println();
        }
        System.out.println( "skipped " + skipped );
    }

    void outOneTriad(Triad triad) {
        System.out.print(codeOperationToString(triad.getOperation()) + " ");
        outOneOperand(triad.getOperand1());
        System.out.print(' ');
        outOneOperand(triad.getOperand2());
    }

    private String codeOperationToString(Tri code) {
        String str = "";
        switch (code) {
            case TRI_MUL:
                str = "*";
                break;
            case TRI_DIV:
                str = "/";
                break;
            case TRI_MOD:
                str = "%";
                break;
            case TRI_PLUS:
                str = "+";
                break;
            case TRI_MINUS:
                str = "-";
                break;
            case TRI_ASSIGNMENT:
                str = "=";
                break;
            case TRI_GT:
                str = ">";
                break;
            case TRI_LT:
                str = "<";
                break;
            case TRI_GE:
                str = ">=";
                break;
            case TRI_LE:
                str = "<=";
                break;
            case TRI_EQ:
                str = "==";
                break;
            case TRI_NEQ:
                str = "!=";
                break;
            case TRI_PROC:
                str = "proc";
                break;
            case TRI_ENDP:
                str = "endp";
                break;
            case TRI_JMP:
                str = "jmp";
                break;
            case TRI_NOP:
                str = "nop";
                break;
            case TRI_IF:
                str = "if";
                break;
            case TRI_LEFT_SHIFT:
                str = "<<";
                break;
            case TRI_RIGHT_SHIFT:
                str = ">>";
                break;
            case TRI_DOT:
                str = ".";
                break;
            case TRI_IDX:
                str = "idx";
                break;
            case TRI_INT_CHAR:
                str = "i->ch";
                break;
            case TRI_CHAR_INT:
                str = "ch->i";
                break;
            case TRI_CALL:
                str = "call";
                break;
            case TRI_MOV:
                str = "mov";
                break;
            case TRI_RET:
                str = "ret";
                break;
            default:
                str = "unknown";
        }
        return str;
    }

    void outOneOperand(Operand operand) {
        if (operand == null || operand.type == EMPTY)
            System.out.print('-');
        else if (operand.type == ADDRESS)
            System.out.print( "(" + operand.value.address + ")");
        else if (operand.type == NODE)
            System.out.print(operand.value.node.lex);
    }
}
