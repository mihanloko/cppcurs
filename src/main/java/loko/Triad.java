package loko;

public class Triad {
    Tri operation;  //операция
    Operand operand1, operand2;

    public void setOperand1(Operand operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(Operand operand2) {
        this.operand2 = operand2;
    }

    public Triad(Tri operation, Operand operand1, Operand operand2) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Triad(Tri operation) {
        this.operation = operation;
    }

    public Tri getOperation() {
        return operation;
    }

    public Operand getOperand1() {
        return operand1;
    }

    public Operand getOperand2() {
        return operand2;
    }
}
