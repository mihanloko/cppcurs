package loko;

import java.util.Objects;

import static loko.OperandType.*;

public class Operand {
    OperandType type = EMPTY;
    OperandValue value;

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
}
