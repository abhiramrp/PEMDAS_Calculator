package evaluator;


public class Operand {

    private int num;


    public Operand(String token) {
        if(check(token)) {
            num = Integer.parseInt(token);
        }
    }

    public Operand(int value) {
        num = value;
    }

    public int getValue() {
        return num;
    }


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
