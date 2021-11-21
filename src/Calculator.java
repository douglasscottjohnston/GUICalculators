import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Calculator extends JFrame {

    //Swing elements
    private JPanel mainPanel;
    private JTextField lastClicked;
    private JTextField field1;
    private JTextField field2;
    private JTextField result;
    private JComboBox<String> combo;
    private JButton equals;
    private JButton backSpace;
    private JButton c;
    private JButton dot;
    private JLabel converted;

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

        converted = new JLabel();

        initializeKeyAdapter();

        initializeAdapter();

        initializeListener();



        field1.addKeyListener(getKeyAdapter());
        field2.addKeyListener(getKeyAdapter());

        field1.addFocusListener(getAdapter());
        field2.addFocusListener(getAdapter());

        combo = new JComboBox<>(operations);

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

        initializeMainPanel();

        initializeKeyPadDigits();

    }

    public void initializeMainPanel() {
        getMainPanel().add(getField1());
        getMainPanel().add(getCombo());
        getMainPanel().add(getField2());
        getMainPanel().add(getEquals());
        getMainPanel().add(getResult());
        getMainPanel().add(getBackSpace());
        getMainPanel().add(getC());
        getMainPanel().add(getDot());
    }

    public void initializeKeyPadDigits() {
        JButton[] keyPadDigits = new JButton[10];
        for (int i = 0; i < 10; i++) {
            keyPadDigits[i] = new JButton(i + "");
            keyPadDigits[i].addActionListener(getListener());
            getMainPanel().add(keyPadDigits[i]);
        }
    }

    public void initializeKeyAdapter() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                if(key.getKeyChar() >= '0' && key.getKeyChar() <= '9') {
                    getLastClicked().setEditable(true);
                } else if(key.getKeyChar() == '.' && !getLastClicked().getText().contains(".")) {
                    getLastClicked().setEditable(true);
                } else if(key.getKeyCode() == KeyEvent.VK_ENTER) {
                    getEquals().doClick();
                } else getLastClicked().setEditable(key.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }
        };
        setKeyAdapter(keyAdapter);
    }

    public void initializeAdapter() {
        FocusAdapter adapter = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setLastClicked((JTextField) e.getSource());
            }
        };
        setAdapter(adapter);
    }

    public void initializeListener() {
        ActionListener listener = e -> getLastClicked().setText(getLastClicked().getText() + ((JButton) e.getSource()).getText());
        setListener(listener);
    }

    public String findResult() {
        if((getField1().getText().isBlank() && !getField2().getText().isBlank()) || (getField2().getText().length() == 1 && getField2().getText().contains("."))) {
            return getField2().getText();
        } else if((!getField1().getText().isBlank() && getField2().getText().isBlank()) || (getField1().getText().length() == 1 && getField1().getText().contains("."))) {
            return getField1().getText();
        } else if((getField1().getText().isBlank() && getField2().getText().isBlank())) {
            return "";
        } else {
            double x1 = Double.parseDouble(getField1().getText());
            double x2 = Double.parseDouble(getField2().getText());

            return Double.toString(calculate(x1, x2, String.valueOf(getCombo().getSelectedItem())));
        }
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

    public int calculate(int x1, int x2, String operation) {
        int result;
        switch(operation){
            case "+" -> result = x1 + x2;
            case "-" -> result = x1 - x2;
            case "*" -> result = x1 * x2;
            case "/" -> result = x1 / x2;
            default -> throw new IllegalArgumentException();
        }
        return result;
    }


    //Getters
    public JPanel getMainPanel() { return this.mainPanel; }

    public KeyAdapter getKeyAdapter() { return this.keyAdapter; }

    public FocusAdapter getAdapter() { return this.adapter; }

    public ActionListener getListener() { return this.listener; }

    public JTextField getLastClicked() { return this.lastClicked; }

    public JTextField getField1() { return this.field1; }

    public JTextField getField2() { return this.field2; }

    public JComboBox<String> getCombo() { return this.combo; }

    public JTextField getResult() { return this.result; }

    public JButton getEquals() { return this.equals; }

    public JButton getBackSpace() { return this.backSpace; }

    public JButton getC() { return this.c; }

    public JButton getDot() { return this.dot; }

    public JLabel getConverted() { return converted; }

    public String[] getOperations() { return this.operations; }

    public JButton[] getKeyPadDigits() { return this.keyPadDigits; }

    public String getX1String() { return x1String; }

    public String getX2String() { return x2String; }

    public double getX1() { return x1; }

    public double getX2() { return x2; }

    //Setters
    public void setKeyAdapter(KeyAdapter keyAdapter) { this.keyAdapter = keyAdapter; }

    public void setAdapter(FocusAdapter adapter) { this.adapter = adapter; }

    public void setListener(ActionListener listener) { this.listener = listener; }

    public void setOperations(String[] operations) { this.operations = operations; }

    public void setKeyPadDigits(JButton[] keyPadDigits) { this.keyPadDigits = keyPadDigits; }

    public void setLastClicked(JTextField lastClicked) { this.lastClicked = lastClicked; }

    public void setConverted(JLabel converted) { this.converted = converted; }

    public void setBackgroundColor(Color c){ this.mainPanel.setBackground(c); }

    public void setX1String(String x1String) { this.x1String = x1String; }

    public void setX2String(String x2String) { this.x2String = x2String; }

    public void setX1(double x1) { this.x1 = x1; }

    public void setX2(double x2) { this.x2 = x2; }
}