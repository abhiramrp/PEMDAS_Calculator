package operators;


import evaluator.Operand;

import java.util.HashMap;

public abstract class Operator {

    public static HashMap<String, Operator> operators;

    static {
        operators = new HashMap<>();
        operators.put("+", new AddOperator());
        operators.put("-", new SubtractOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("^", new ExponentOperator());
        operators.put("(", new OpenOperator());
        operators.put(")", new CloseOperator());
    }


    public abstract int priority();

    public abstract String getOperatorString();


    public abstract Operand execute(Operand operandOne, Operand operandTwo);


    public static Operator getOperator(String token) {
        if (check(token)) {
            return operators.get(token);
        }

        return null;
    }

    public static boolean check(String token) {
        return operators.containsKey(token);
    }
}

