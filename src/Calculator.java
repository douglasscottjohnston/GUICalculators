import javax.swing.*;

public abstract class Calculator {

    //Swing elements
    private JPanel mainPanel;
    private JPanel keyPad;
    private final JComboBox combo;
    private final JTextField field1;
    private final JTextField field2;
    private JButton plus;
    private JButton minus;
    private JButton multiply;
    private JButton divide;
    private JButton equals;
    private JButton c;

    private JButton[] keyPadDigits;

    //Reference types
    private String[] operations = {"+", "-", "*", "/"};
    private String x1String;
    private String x2String;


    //Primitive types
    private double x1;
    private double x2;

    public Calculator() {
        keyPadDigits = initializeKeyPadDigits();
        keyPad = initializeKeyPad(keyPadDigits);

        field1 = new JTextField();
        field2 = new JTextField();
        combo = new JComboBox(operations);

        plus = new JButton("+");
        minus = new JButton("-");
        multiply = new JButton("*");
        divide = new JButton("/");
        equals = new JButton("=");
        c = new JButton("C");

        mainPanel = new JPanel();
        mainPanel.add(field1);
        mainPanel.add(combo);
        mainPanel.add(field2);
        mainPanel.add(keyPad);
        mainPanel.add(c);
        mainPanel.add(equals);


    }


    private JButton[] initializeKeyPadDigits() {
        JButton[] keyPadDigits = new JButton[9];
        for (int i = 0; i < 10; i++) {
            if(i < 9) {
                keyPadDigits[i] = new JButton("" + (i++));
            } else {
                keyPadDigits[i] = new JButton("0");
            }
        }
        return keyPadDigits;
    }
    
    private JPanel initializeKeyPad(JButton[] keyPadDigits) {
        JPanel keyPad = new JPanel();

        for (int i = 0; i < 10; i++) {
            keyPad.add(keyPadDigits[i]);
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
    public JTextField getField1() { return field1; }

    public JTextField getField2() { return field2; }

    public JComboBox getCombo() { return combo; }

    public JButton getEquals() { return equals; }

    public JButton getC() { return c; }

    public String[] getOperations() { return operations; }

    public JButton[] getKeyPadDigits() { return keyPadDigits; }

    public String getX1String() { return x1String; }

    public String getX2String() { return x2String; }

    public double getX1() { return x1; }

    public double getX2() { return x2; }

    //Setters
    public void setOperations(String[] operations) { this.operations = operations; }

    public void setKeyPadDigits(JButton[] keyPadDigits) { this.keyPadDigits = keyPadDigits; }

    public void setX1String(String x1String) { this.x1String = x1String; }

    public void setX2String(String x2String) { this.x2String = x2String; }

    public void setX1(double x1) { this.x1 = x1; }

    public void setX2(double x2) { this.x2 = x2; }
}
