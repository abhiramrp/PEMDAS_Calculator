package operators;

import evaluator.Operand;

public class CloseOperator extends Operator{
    @Override
    public int priority() {
        return 4;
    }

    @Override
    public String getOperatorString() {return ")";}

    // Doesn't do any arithmetic. Returns 0 operand
    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        Operand r = new Operand(0);
        return r;
    }
}
