import javax.swing.*;

public abstract class Calculator {

    //Swing elements
    private final JTextField field1;
    private final JTextField field2;
    private final JComboBox combo;
    private JButton equals;
    private JButton c;

    //Reference types
    private String[] operations = {"+", "-", "*", "/"};
    private String x1String;
    private String x2String;

    private JButton[] keyPad;


    //Primitive types
    private double x1;
    private double x2;

    public Calculator() {
        field1 = new JTextField();
        field2 = new JTextField();
        combo = new JComboBox(operations);
        equals = new JButton("=");
        c = new JButton("C");
        keyPad = initializeKeyPad();
    }

    private JButton[] initializeKeyPad() {
        JButton[] keyPad = new JButton[9];
        for (int i = 0; i < 10; i++) {
            if(i < 9) {
                keyPad[i] = new JButton("" + (i++));
            } else {
                keyPad[i] = new JButton("0");
            }
        }
        return keyPad;
    }

    public double calculate(double x1, double x2, String operation) {
        double result;

        switch(operation) {
            case "+":
                result = x1 + x2;
            case "-":
                result = x1 - x2;
            case "*":
                result = x1 * x2;
            case "/":
                result = x1 / x2;
            default:
                result = 0;
        }

        return result;
    }


    //Getters




    public double getX1() { return x1; }

    public double getX2() { return x2; }

    //Setters
    public void setX1(double x1) { this.x1 = x1; }

    public void setX2(double x2) { this.x2 = x2; }

    public void setOperations(String[] operations) {
        this.operations = operations;
    }
}
