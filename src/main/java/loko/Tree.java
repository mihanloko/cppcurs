package loko;

import java.util.Objects;

import static java.lang.System.exit;
import static loko.Type.*;
import static loko.TypeName.*;

public class Tree {
    public static Tree cur;
    private Tree left;
    private Tree right;
    private Tree parent;
    private Node node;
    public static boolean flagInterpret = false;
    private Scanner scanner = new Scanner();



    public Tree getLeft() {
        return left;
    }

    public Tree getParent() {
        return parent;
    }

    public Tree() {
        node = new Node();
        left = right = parent = null;
    }

    public Tree(Tree left, Tree right, Tree parent, Node node) {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.node = node;
    }

    void SetLeft(Node Data) {
        left = new Tree(null, null, this, Data);
    }

    void SetRight(Node Data) {
        right = new Tree(null, null, this, Data);
    }

    Tree FindUp(Tree From, String id) {
        if (!flagInterpret) return null;
        Tree cur = From;
        while (cur != null && (!Objects.equals(cur.node.lex, id))) {
            cur = cur.parent;
        }
        return cur;
    }

    Tree FindUpOneLevel(Tree From, String id)
// Поиск элемента id вверх по дереву от текущей вершины From.
// Поиск осуществляется на одном уровне вложенности по левым связям
    {
        if (!flagInterpret) return null;
        Tree i = From;
// текущая вершина поиска
        while ((i != null) && (i.parent != null && i.parent.right != i)) {
            if (id == i.node.lex)
                return i; // нaшли совпадающий идентификатор
            i = i.parent;
// поднимаемся наверх по связям
        }
        return null;
    }

    int DupControl(Tree Addr, String a)
// Проверка идентификатора а на повторное описание внутри блока.
// Поиск осуществляется вверх от вершины Addr.
    {
        if (!flagInterpret) return 0;
        if (FindUpOneLevel(Addr, a) == null) return 0;
        return 1;
    }

    Tree FindUp(String id) {
        if (!flagInterpret) return null;
        return FindUp(this, id);
    }

    Tree FindRightLeft(String id) {
        if (!flagInterpret) return null;
        return FindRightLeft(this, id);
    }

    Tree FindRightLeft(Tree From, String id) {
        if (!flagInterpret) return null;
        Tree cur = From.right;
        while (cur != null && (cur.node.lex != id)) {
            cur = cur.left;
        }
        return cur;
    }

    Tree(Node data) {
        this.node = data;
        left = right = parent = null;
    }

    Tree goUp(Tree blockParent) {
        if (!flagInterpret) return null;
    /*while (cur != blockParent)
        cur = cur.parent;*/
        cur = blockParent;
        return cur;
    }

    Tree openBlock(String lex, Type type) {
        if (!flagInterpret) return null;
        if (!lex.isEmpty() && cur.FindUp(lex) != null) {
            scanner.printSemError("Идентификатор уже объявлен " + lex, lex.length());
        }
        Node newNode = new Node();
        newNode.lex = lex;
        newNode.type = type;
        cur.SetLeft(newNode);
        cur = cur.left;
        Tree res = cur;
        Node fictive = new Node();
        fictive.type = ObjFictive;
        cur.SetRight(fictive);
        cur = cur.right;
        return res;
    }

    void Print(int k) {
        for (int i = 0; i < k; ++i) {
            System.out.printf("\t");
        }
        System.out.printf("Вершина с данными %s ----.", node.lex);
        if (left != null) System.out.printf("  слева данные %s", left.node.lex);
        if (right != null) System.out.printf("  справа данные %s", right.node.lex);
        System.out.printf("\n");
        if (right != null) right.Print(k + 1);
        if (left != null) left.Print(k);
    }

    Tree isExist(String lex) {
        if (!flagInterpret) return null;
        Tree n = cur.FindUp(lex);
        if (n == null) {
            scanner.printSemError("Идентификатор " + lex + " не объявлен", lex.length());
            Node fakeNode = new Node();
            fakeNode.lex = lex;
            fakeNode.type = ObjUnknown;
            cur.SetLeft(fakeNode);
            cur = cur.left;
            return cur;
        }
        return n;
    }

    Tree findFiled(Tree p, String field) {
        if (!flagInterpret) return null;
        if (p.node.type != ObjStruct) {
            scanner.printSemError("Попытка обратиться к полю, но " + p.node.lex + " не является структурой",
                    field.length() + 1);
            exit(0);
        }

        Tree typeDefinition = p.node.dataType;
        String type = typeDefinition.getNode().stringTypeName;
//    while (typeDefinition != null && typeDefinition.node.type != ObjStructDefinition)
//        typeDefinition = cur.FindUp(typeDefinition.parent, type);
        if (typeDefinition == null) {
            scanner.printSemError("Класс не объявлен " + type, type.length());
            exit(0);
        }
        Tree fieldNode = typeDefinition.FindRightLeft(field);
        if (fieldNode == null) {
            scanner.printSemError("Класс не содержит поле " + field, field.length() + 1);
            Tree s = typeDefinition.right;
            while (s.left != null) {
                s = s.left;
            }
            Node fakeNode = new Node();
            fakeNode.lex = field;
            fakeNode.type = ObjUnknown;
            s.SetLeft(fakeNode);
            fieldNode = s.left;
            exit(0);
        }

        return fieldNode;
    }

    Tree checkArray(Tree p, Tree e) {
        if (!flagInterpret) return null;
        if (p.node.type != ObjArray) {
            scanner.printSemError(p.node.lex + " не является массивом, оператор [] неуместен", 1);
            exit(0);
        }

        if (e.node.type != ObjVar) {
            scanner.printSemError("Значеник выражения не является целочисленным типом", 0);
            exit(0);
        }

        return p;
    }

    Node getNode() {
        if (!flagInterpret) return null;
        return node;
    }

    Tree checkAssignCompatible(Tree t, Tree g) {
        if (!flagInterpret) return null;
        if (t.node.type == ObjStructDefinition || g.node.type == ObjStructDefinition ||
                t.node.type == ObjMain || g.node.type == ObjMain) {
            scanner.printSemError("Неприсваемый тип", 0);
            exit(0);
        }

        if (t.node.type == ObjStruct || g.node.type == ObjStruct) {
            if (t.node.type == ObjStruct && g.node.type == ObjStruct) {
                if (t.node.dataType == g.node.dataType)
                    return t.node.dataType;
                else {
                    scanner.printSemError("разные классы", 0);
                    exit(0);
                }
            }
            else {
                scanner.printSemError("разные классы", 0);
                exit(0);
            }
        }

        if (t.node.type == ObjUnknown || g.node.type == ObjUnknown)
            return t.node.type == ObjUnknown ? t : g;

        if (t.node.typeName == ObjChar && g.node.typeName == ObjChar)
            return t;
        if (t.node.typeName == ObjInt || g.node.typeName == ObjInt)
            return t.node.typeName == ObjInt ? t : g;


        scanner.printSemError("Неверный тип", 0);
        exit(0);
        return null;
    }

    Tree findClassDefinition(String lex) {
        if (!flagInterpret) return null;
        Tree type = cur.FindUp(lex);
        while (type != null && type.node.type != ObjStructDefinition)
            type = type.FindUp(type.parent, lex);
        if (type == null) {
            scanner.printSemError("класс " + lex + " не объявлен", 0);
            exit(0);
        }
        if (type.node.type != ObjStructDefinition) {
            scanner.printSemError(lex + " не является классом", 0);
            exit(0);
        }
        return type;
    }

    Tree createVar(Tree type, String lex) {
        if (!flagInterpret) return null;
        Tree p = cur.FindUp(lex);
        if (DupControl(this, lex) != 0) {
            scanner.printSemError("Объект " + lex + " уже существует", lex.length());
            exit(0);
        }
        Node n = new Node();
        n.dataType = type;
        n.stringTypeName = type.node.lex;
        n.lex = lex;

        if (type.node.lex == "int") {
            n.type = ObjVar;
            n.typeName = ObjInt;
        }
        else if (type.node.lex == "char") {
            n.type = ObjVar;
            n.typeName = ObjChar;
        }
        else {
            n.type = ObjStruct;
            n.typeName = ObjClass;
            //todo копировать
        }

        cur.SetLeft(n);
        cur = cur.left;
        if (n.type == ObjStruct) {
            cur.right = type.right.copy();
        /*Tree root = cur;
        while (root.parent != null)
            root = root.parent;
        root.Print(0);
        cout << endl << endl;*/
        }
//    Tree i = cur;
//    while (i.parent != null)
//        i = i.parent;
//    i.Print(0);
        return cur;
    }

    Tree makeVarArray(String length) {
        if (!flagInterpret) return null;
        this.node.size = Integer.parseInt(length);
        this.node.type = ObjArray;
        if (this.node.typeName == ObjInt) {
            this.node.data.intArray = new int[this.node.size];
        } else if (this.node.typeName == ObjChar) {
            this.node.data.charArray = new char[this.node.size];
        }
    else {
            this.node.data.structArray = new Tree[this.node.size];
            for (int i = 0; i < this.node.size; i++) {
                this.node.data.structArray[i] = this.node.dataType.copy();
            }
        }
        return this;
    }

    Tree makeVarArray() {
        if (!flagInterpret) return null;
        this.node.size = 0;
        this.node.type = ObjArray;
        if (this.node.typeName == ObjInt) {
            this.node.data.intArray = new int[this.node.size];
        } else if (this.node.typeName == ObjChar) {
            this.node.data.charArray = new char[this.node.size];
        }
    else {
            this.node.data.structArray = new Tree[this.node.size];
            for (int i = 0; i < this.node.size; i++) {
                this.node.data.structArray[i] = this.node.dataType.copy();
            }
        }
        return this;
    }

    Tree makeTypeFromArray(Tree pTree) {
        if (!flagInterpret) return null;
        Node n = new Node();
        if (pTree.node.stringTypeName == "int") {
            n.type = ObjVar;
            n.typeName = ObjInt;
        } else if (pTree.node.stringTypeName == "char") {
            n.type = ObjVar;
            n.typeName = ObjClass;
        } else {
            n.type = ObjStruct;
            n.typeName = ObjClass;
        }
        n.dataType = pTree.node.dataType;

        return new Tree(n);
    }

    Tree makeIntVar(String lex) {
        if (!flagInterpret) return null;
        Node n = new Node();
        n.type = ObjVar;
        n.typeName = ObjInt;
        n.stringTypeName = "int";
        n.data.intVariable = Integer.parseInt(lex);

        return new Tree(n);
    }

    Tree makeIntVar() {
        if (!flagInterpret) return null;
        Node n = new Node();
        n.type = ObjVar;
        n.typeName = ObjInt;
        n.stringTypeName = "int";
        n.data.intVariable = 0;

        return new Tree(n);
    }


    Tree check6Compatible(Tree t) {
        if (!flagInterpret) return null;
        if (t.node.type == ObjStructDefinition ||
                t.node.type == ObjMain ||
                        t.node.type == ObjArray ||
                                t.node.type == ObjStruct) {
            scanner.printSemError("Невозможный тип для операций + или -", 0);
            exit(0);
        }
        return t;
    }

    Tree check5Compatible(Tree t, Tree g) {
        if (!flagInterpret) return null;
        if (t.node.type == ObjStructDefinition || g.node.type == ObjStructDefinition ||
                t.node.type == ObjMain || g.node.type == ObjMain ||
                        t.node.type == ObjArray || g.node.type == ObjArray ||
                                t.node.type == ObjStruct || g.node.type == ObjStruct) {
            scanner.printSemError("Невозможный тип для операций , / или %", 0);
            exit(0);
        }
        if (t.node.type == ObjUnknown || g.node.type == ObjUnknown)
            return t.node.type == ObjUnknown ? t : g;
        if (t.node.typeName == ObjInt || g.node.typeName == ObjInt)
            return t.node.typeName == ObjInt ? t : g;
        if (t.node.typeName == ObjChar && g.node.typeName == ObjChar)
            return t;
        return null;
    }

    Tree check4Compatible(Tree t, Tree g) {
        if (!flagInterpret) return null;
        if (t.node.type == ObjStructDefinition || g.node.type == ObjStructDefinition ||
                t.node.type == ObjMain || g.node.type == ObjMain ||
                        t.node.type == ObjArray || g.node.type == ObjArray ||
                                t.node.type == ObjStruct || g.node.type == ObjStruct) {
            scanner.printSemError("Невозможный тип для операций + или -", 0);
            exit(0);
        }
        if (t.node.type == ObjUnknown || g.node.type == ObjUnknown)
            return t.node.type == ObjUnknown ? t : g;
        if (t.node.typeName == ObjInt || g.node.typeName == ObjInt)
            return t.node.typeName == ObjInt ? t : g;
        if (t.node.typeName == ObjChar && g.node.typeName == ObjChar)
            return t;
        return null;
    }

    Tree check3Compatible(Tree t, Tree g) {
        if (!flagInterpret) return null;
        if (t.node.type == ObjStructDefinition || g.node.type == ObjStructDefinition ||
                t.node.type == ObjMain || g.node.type == ObjMain ||
                        t.node.type == ObjArray || g.node.type == ObjArray ||
                                t.node.type == ObjStruct || g.node.type == ObjStruct) {
            scanner.printSemError("Невозможный тип для операций логического сдвига", 0);
            exit(0);
        }

        if (t.node.type == ObjUnknown || g.node.type == ObjUnknown)
            return t.node.type == ObjUnknown ? t : g;
        if (t.node.typeName == ObjInt || g.node.typeName == ObjInt)
            return t.node.typeName == ObjInt ? t : g;
        if (t.node.typeName == ObjChar && g.node.typeName == ObjChar)
            return t;
        return null;
    }

    Tree check2Compatible(Tree t, Tree g) {
        if (!flagInterpret) return null;
        if (t.node.type == ObjStructDefinition || g.node.type == ObjStructDefinition ||
                t.node.type == ObjMain || g.node.type == ObjMain ||
                        t.node.type == ObjArray || g.node.type == ObjArray ||
                                t.node.type == ObjStruct || g.node.type == ObjStruct) {
            scanner.printSemError("Невозможный тип для операций сравнения", 0);
            exit(0);
        }

        if (t.node.type == ObjUnknown || g.node.type == ObjUnknown)
            return t.node.type == ObjUnknown ? t : g;
        if (t.node.typeName == ObjInt || g.node.typeName == ObjInt)
            return t.node.typeName == ObjInt ? t : g;
        if (t.node.typeName == ObjChar && g.node.typeName == ObjChar)
            return t;
        return null;
    }

    Tree check1Compatible(Tree t, Tree g) {
        if (!flagInterpret) return null;
        if (t.node.type == ObjStructDefinition || g.node.type == ObjStructDefinition) {
            scanner.printSemError("Невозможный тип для операций сравнения", 0);
            exit(0);
        }

        if (t.node.type == ObjMain || g.node.type == ObjMain) {
            if (t.node.type == ObjMain && g.node.type == ObjMain)
                return t;
            else {
                scanner.printSemError("Невозможный тип для операций сравнения", 0);
                exit(0);
            }
        }

        if (t.node.type == ObjArray || g.node.type == ObjArray) {
            if (t.node.type == ObjArray && g.node.type == ObjArray) {
                if (t.node.dataType == g.node.dataType)
                    return t;
                else {
                    scanner.printSemError("Разные типы массивов для сравнения", 0);
                    exit(0);
                }
            }
        }

        if (t.node.type == ObjStruct || g.node.type == ObjStruct) {
            if (t.node.type == ObjStruct && g.node.type == ObjStruct) {
                if (t.node.dataType == g.node.dataType)
                    return t;
                else {
                    scanner.printSemError("Разные типы структур для сравнения", 0);
                    exit(0);
                }
            } else {
                scanner.printSemError("Невозможный тип для операций сравнения", 0);
                exit(0);
            }
        }

        if (t.node.type == ObjUnknown || g.node.type == ObjUnknown)
            return t.node.type == ObjUnknown ? t : g;
        if (t.node.typeName == ObjInt || g.node.typeName == ObjInt)
            return t.node.typeName == ObjInt ? t : g;
        if (t.node.typeName == ObjChar && g.node.typeName == ObjChar)
            return t;
        return null;
    }

    Tree copy(Tree p) {
        Tree newTree = this.copy();
        newTree.parent= p;
        return newTree;
    }

    Tree copy() {
        Tree newTree = new Tree();
        newTree.node = node.copy();
        newTree.parent = parent;
        newTree.left = left == null ? null : left.copy();
        newTree.right = right == null ? null : right.copy();
        return newTree;
    }

    boolean isFlagInterpret() {
        return flagInterpret;
    }

    void setFlagInterpret(boolean flagInterpret) {
        flagInterpret = flagInterpret;
    }

    Tree getRight() {
        return right;
    }

    void nullRight() {
        this.right = null;
    }

    void setRight(Tree right) {
        right = right;
    }

    void goUp() {
    /*while (cur.parent.right != this) {
        cur = cur.parent;
    }
    cur = cur.parent;*/
    }
}
