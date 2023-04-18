package evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {

    private int num;

    /**
     * construct operand from string token.
     */
    public Operand(String token) {
        if(check(token)) {
            num = Integer.parseInt(token);
        }
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        num = value;
    }

    /**
     * return value of operand
     */
    public int getValue() {
        return num;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        try {
            Integer.parseInt(token);
            return true;
        }

        catch (NumberFormatException e) {
            return false;
        }
    }
}
