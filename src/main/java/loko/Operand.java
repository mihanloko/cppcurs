package loko;

import java.util.Objects;

import static loko.OperandType.*;

public class Operand {
    OperandType type = EMPTY;
    OperandValue value;

    private Operand() {

    }

    public Operand(String operand) {
        this(new Node(operand));
    }

    public Operand(Node node) {
        type = NODE;
        value = new OperandValue();
        value.node = node;
    }

    public Operand(int triNum) {
        type = ADDRESS;
        value = new OperandValue();
        value.address = triNum;
    }

    public boolean equals(Operand other) {
        if (other == null || this.type != other.type)
            return false;
        if (this.type == ADDRESS && this.value.address == other.value.address)
            return true;
        if (this.type == NODE && Objects.equals(this.value.node.lex, other.value.node.lex))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    public Operand copy() {
        Operand clone = new Operand();
        clone.type = type;
        if (type == ADDRESS) {
            clone.value = new OperandValue();
            clone.value.address = value.address;
        } else if (type == NODE) {
            clone.value.node = new Node(value.node.lex);
        } else {
            clone.value = value;
        }
        return clone;
    }
}
