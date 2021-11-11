import javax.swing.*;

public abstract class Calculator {

    private final JTextField field1;
    private final JTextField field2;
    private final JComboBox combo;
    private JButton equals;
    private JButton c;

    private String[] operations = {"+", "-", "*", "/"};

    public Calculator() {
        field1 = new JTextField();
        field2 = new JTextField();
        combo = new JComboBox(operations);
        equals = new JButton("=");
        c = new JButton("C");
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

    public JTextField getField1() {
        return field1;
    }

    public JTextField getField2() {
        return field2;
    }

    public JComboBox getCombo() {
        return combo;
    }

    public JButton getEquals() {
        return equals;
    }

    public JButton getC() {
        return c;
    }

    public String[] getOperations() {
        return operations;
    }

    public void setOperations(String[] operations) {
        this.operations = operations;
    }
}
