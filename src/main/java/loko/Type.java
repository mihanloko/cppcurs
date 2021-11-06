package loko;

public enum Type {
    ObjVar, // простая переменная
    ObjArray, //массив
    ObjStruct, // класс
    ObjStructDefinition, // тип структуры
    ObjMain, // функция main, должна быть единственной
    ObjFictive,
    ObjUnknown
}
