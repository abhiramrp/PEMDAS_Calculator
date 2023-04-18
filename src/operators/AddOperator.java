package operators;

import evaluator.Operand;

public class AddOperator extends Operator{
    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String getOperatorString() {return "+";}

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int o1 = operandOne.getValue();
        int o2 = operandTwo.getValue();

        Operand r = new Operand(o1+o2);
        return r;
    }
}
