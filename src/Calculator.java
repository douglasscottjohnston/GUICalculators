import javax.swing.*;

public class Calculator {

    private JTextField field1;
    private JTextField field2;
    private JComboBox combo;
    private JButton equals;
    private JButton c;

    private String[] operations = {"+", "-", "*", "/"};

    public Calculator() {
        field1 = new JTextField();
        field2 = new JTextField();
        combo = new JComboBox(operations);
    }



}
