package loko;

import static loko.Type.*;
import static loko.TypeName.*;

public class Node {
    public Type type;  //тип
    public String lex;  //обозначение
    public TypeName typeName;
    public Tree dataType; // тип переменной или массива //rework
    public int size; // размер массива
    public String stringTypeName;

    public DataValue data; // значение

    public Node() {
        type = ObjFictive;
        lex = "";
        stringTypeName = "";
        size = 0;
        data = new DataValue();
    }

    public Node(String lex) {
        type = ObjUnknown;
        this.lex = lex;
        typeName = ObjUnknownType;
        dataType = null;
        stringTypeName = "";
        data = new DataValue();
    }

    public Node copy() {
        Node newNode = new Node();
        newNode.stringTypeName = stringTypeName;
        newNode.typeName = typeName;
        newNode.type = type;
        newNode.dataType = dataType;
        newNode.data = data;
        newNode.lex = lex;
        newNode.size = size;

        return newNode;
    }
}
