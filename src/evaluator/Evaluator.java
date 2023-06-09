package evaluator;


import exceptions.InvalidTokenException;
import operators.*;

import javax.management.openmbean.OpenMBeanAttributeInfo;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer expressionTokenizer;
    private final String delimiters = " +/*-^)(";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    // DEBUG METHODS
    private void printBothStacks() {
        Iterator<Operand> orIterator = operandStack.iterator();
        System.out.print("Operand Stack: ");
        while (orIterator.hasNext()) {
            System.out.print(orIterator.next().getValue());
            System.out.print(" ");
        }

        System.out.println("");

        Iterator<Operator> opIterator = operatorStack.iterator();
        System.out.print("Operator Stack: ");
        while (opIterator.hasNext()) {
            Operator o = opIterator.next();
            System.out.print(o.getOperatorString());
            System.out.print(o.priority());
            System.out.print(" ");
        }

        System.out.println("");


    }

    public int evaluateExpression(String expression ) throws InvalidTokenException {
        String expressionToken;


        this.expressionTokenizer = new StringTokenizer( expression, this.delimiters, true );



        int tokenCounter = 0;

        while ( this.expressionTokenizer.hasMoreTokens() ) {
            // filter out spaces
            if ( !( expressionToken = this.expressionTokenizer.nextToken() ).equals( " " )) {
                tokenCounter++;
                //System.out.println("\n");
                //printBothStacks();
                //System.out.println("Token " + Integer.toString(tokenCounter) + ": " + expressionToken);

                // check if token is an operand
                if ( Operand.check( expressionToken )) {
                    //System.out.println("Operand " + expressionToken + " started pushing");
                    operandStack.push( new Operand( expressionToken ));
                    //System.out.println("Operand " + expressionToken + " pushed");
                } else {

                    if ( ! Operator.check( expressionToken )) {
                        throw new InvalidTokenException(expressionToken);
                    }

                    // TODO Operator is abstract - these two lines will need to be fixed:
                    // The Operator class should contain an instance of a HashMap,
                    // and values will be instances of the Operators.  See Operator class
                    // skeleton for an example.

                    Operator newOperator = Operator.getOperator(expressionToken);

                    //System.out.println("Operator " + expressionToken + " started pushing");

                    if(operatorStack.empty() && !(")".equals(expressionToken))) {
                        operatorStack.push(newOperator);

                        //System.out.println("Operator " + expressionToken + " pushed into empty stack");
                        continue;
                    }

                    if(expressionToken.equals("(")) {
                        operatorStack.push(newOperator);
                        //System.out.println("Operator " + expressionToken + " pushed into stack");
                        continue;
                    }


                    if(expressionToken.equals(")")) {
                        //System.out.println("In )");
                        while(!(operandStack.isEmpty()) && operatorStack.peek().priority() != 4) {
                            Operator operatorFromStack = operatorStack.pop();
                            Operand operandTwo = operandStack.pop();
                            Operand operandOne = operandStack.pop();

                            Operand result = operatorFromStack.execute(operandOne, operandTwo);
                            //System.out.println("Operand Result in ) " + Integer.toString(result.getValue()) + " started pushing");
                            operandStack.push(result);
                            //System.out.println("Operand Result in ) " + Integer.toString(result.getValue())+ " pushed");
                        }
                        //System.out.println("Should be ( Popping " + operatorStack.peek().getOperator());
                        operatorStack.pop(); // pops '('
                        continue;
                    }

                    if(operatorStack.peek().priority() != 4) {

                        while (!(operandStack.isEmpty()) && operatorStack.peek().priority() >= newOperator.priority()) {

                            Operator operatorFromStack = operatorStack.pop();
                            Operand operandTwo = operandStack.pop();
                            Operand operandOne = operandStack.pop();
                            Operand result = operatorFromStack.execute(operandOne, operandTwo);
                            //System.out.println("Operand Result " + Integer.toString(result.getValue()) + " started pushing");
                            operandStack.push(result);
                            //System.out.println("Operand Result " + Integer.toString(result.getValue())+ " pushed");

                            if(operatorStack.isEmpty()) {
                                break;
                            }

                            if(operatorStack.peek().priority() == 4) {
                                break;
                            }

                        }

                        //System.out.println("Out of loop");
                    }

                    operatorStack.push(newOperator);
                    //System.out.println("Operator " + expressionToken + " pushed into stack");

                }
            }
        }

        while(!(operatorStack.isEmpty())) {
            Operator operatorFromStack = operatorStack.pop();
            Operand operandTwo = operandStack.pop();
            Operand operandOne = operandStack.pop();
            Operand result = operatorFromStack.execute(operandOne, operandTwo);
            operandStack.push(result);
        }



        return operandStack.pop().getValue();
    }

}