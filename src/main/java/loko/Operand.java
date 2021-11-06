package loko;

import static loko.OperandType.*;

public class Operand {
    OperandType type = EMPTY;
    OperandValue value;

    public Operand(String operand) {
        this(new Node(operand));
    }

    public Operand(Node node) {
        value = new OperandValue();
        value.node = node;
    }

    public Operand(int triNum) {
        value = new OperandValue();
        value.address = triNum;
    }
}
