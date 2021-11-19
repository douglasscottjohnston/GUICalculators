import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Calculator extends JFrame {

    //Swing elements
    private static JPanel mainPanel;
    private static JPanel keyPad;
    private static JComboBox combo;
    private JTextField lastClicked;
    private static JTextField field1;
    private static JTextField field2;
    private static JTextField result;
    private static JButton equals;
    private static JButton backSpace;
    private static JButton c;
    private static JButton dot;
    
    private KeyAdapter keyAdapter;
    private ActionListener listener;
    private FocusAdapter adapter;

    private JButton[] keyPadDigits;

    //Reference types
    private String[] operations = {"+", "-", "*", "/"};
    private String x1String;
    private String x2String;

    //Primitive types
    private double x1;
    private double x2;

    public Calculator() {

        mainPanel = new JPanel();
        this.add(mainPanel);

        field1 = new JTextField(25);
        field2 = new JTextField(25);
        result = new JTextField(15);

        result.setEditable(false);

        //disallows character input from the keyboard
        keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                String display = lastClicked.getText();
                int len = display.length();
                
                if(key.getKeyChar() >= '0' && key.getKeyChar() <= '9') {
                    lastClicked.setEditable(true);
                } else if(key.getKeyChar() == '.' && !lastClicked.getText().contains(".")) {
                    lastClicked.setEditable(true);
                } else {
                    lastClicked.setEditable(false);
                }
            }
        };

        //changes lastClicked depending on which field was clicked last
        adapter = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                lastClicked = (JTextField) e.getSource();
            }
        };
        
        listener = e -> {
            lastClicked.setText(lastClicked.getText() + ((JButton) e.getSource()).getText());

        };

        field1.addKeyListener(keyAdapter);
        field2.addKeyListener(keyAdapter);

        field1.addFocusListener(adapter);
        field2.addFocusListener(adapter);

        combo = new JComboBox(operations);

        equals = new JButton("=");
        backSpace = new JButton("\u21E6");
        c = new JButton("C");
        dot = new JButton(".");


        equals.addActionListener(e -> {
            result.setText("");
            result.setText(findResult());
        });

        backSpace.addActionListener(e -> {
            if(lastClicked.getText().length() > 0) {
                lastClicked.setText(lastClicked.getText().substring(0, lastClicked.getText().length() - 1));
            }
        });

        c.addActionListener(e -> {
            field1.setText("");
            field2.setText("");
            result.setText("");
        });

        dot.addActionListener(e -> {
            if(!lastClicked.getText().contains(".")) {
                lastClicked.setText(lastClicked.getText() + ((JButton) e.getSource()).getText());
            }
        });


        mainPanel.add(field1);
        mainPanel.add(combo);
        mainPanel.add(field2);
        mainPanel.add(result);
        mainPanel.add(equals);
        mainPanel.add(backSpace);
        mainPanel.add(c);
        mainPanel.add(dot);

        initializeKeyPadDigits();

    }


    public void initializeKeyPadDigits() {
        JButton[] keyPadDigits = new JButton[10];
        for (int i = 0; i < 10; i++) {
            keyPadDigits[i] = new JButton(i + "");
            keyPadDigits[i].addActionListener(listener);
            mainPanel.add(keyPadDigits[i]);
        }
    }

    public String findResult() {
        double x1 = Double.parseDouble(getField1().getText());
        double x2 = Double.parseDouble(getField2().getText());

        return Double.toString(calculate(x1, x2, String.valueOf(getCombo().getSelectedItem())));
    }

    public double calculate(double x1, double x2, String operation) {
        double result;

        switch(operation) {
            case "+" -> result = x1 + x2;
            case "-" -> result = x1 - x2;
            case "*" -> result = x1 * x2;
            case "/" -> result = x1 / x2;
            default -> throw new IllegalArgumentException();
        }

        return result;
    }


    //Getters
    public JPanel getMainPanel() { return mainPanel; }

    public ActionListener getListener() { return listener; }

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

    public void setBackgroundColor(Color c){ this.mainPanel.setBackground(c); }

    public void setX1String(String x1String) { this.x1String = x1String; }

    public void setX2String(String x2String) { this.x2String = x2String; }

    public void setX1(double x1) { this.x1 = x1; }

    public void setX2(double x2) { this.x2 = x2; }
}