package operators;

package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator{
    @Override
    public int priority() {
        return 3;
    }

    @Override
    public String getOperatorString() {return "*";}

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int o1 = operandOne.getValue();
        int o2 = operandTwo.getValue();

        // uses Math library and converts float to int
        int rt = (int) Math.pow(o1, o2);

        Operand r = new Operand(rt);

        return r;
    }
}
