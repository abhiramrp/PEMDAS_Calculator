package evaluator;

import edu.csc413.calculator.exceptions.InvalidTokenException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField expressionTextField = new TextField();
    private Panel buttonPanel = new Panel();
    private Evaluator eval = new Evaluator();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] buttonText = {
            "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[buttonText.length];

    public static void main(String argv[]) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.expressionTextField.setPreferredSize(new Dimension(600, 50));
        this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 28));

        add(expressionTextField, BorderLayout.NORTH);
        expressionTextField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button tempButtonReference;
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            tempButtonReference = new Button(buttonText[i]);
            tempButtonReference.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = tempButtonReference;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     * @param actionEventObject Event object generated when a
     *                    button is pressed.
     */
    public void actionPerformed(ActionEvent actionEventObject) {
        String calcText = expressionTextField.getText();

        // Checks if 'C' is pressed. Clears the entry
        if(actionEventObject.getSource() == buttons[18]) {
            calcText = "";
        }

        // Deletes the last character. If 'CE' is pressed
        else if (actionEventObject.getSource() == buttons[19]) {
            if(calcText.length() != 0) {
                calcText = calcText.substring(0, (calcText.length()-1));
            }
        }

        // When '=' is pressed. Calculates entry by calling evaluateExpression();
        else if(actionEventObject.getSource() == buttons[14]) {
            // If calcText is empty, no action required
            if(calcText.length() != 0) {
                try {
                    calcText = Integer.toString(eval.evaluateExpression(calcText));
                } catch (InvalidTokenException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        else {
            for(int i=0; i<buttons.length; i++) {
                if(actionEventObject.getSource() == buttons[i]) {
                    calcText = calcText + buttons[i].getLabel();; // Concatenates string with button label
                }
            }
        }

        expressionTextField.setText(calcText);

    }
}
