import javax.swing.*;
import java.awt.event.*;

/**
 * Abstract calculator class which provides the blueprint for all calculators
 * TCSS 305 Project_#02
 * @author Douglas Johnston
 */
public abstract class Calculator extends JFrame {

    //Swing elements
    private final JPanel mainPanel;
    private JTextField lastClicked;
    private final JTextField field1;
    private final JTextField field2;
    private final JTextField result;
    private final JComboBox<String> combo;
    private final JButton equals;
    private final JButton backSpace;
    private final JButton c;
    private final JButton dot;
    private final JLabel converted;

    private KeyAdapter keyAdapter;
    private ActionListener listener;
    private FocusAdapter adapter;

    /**
     * Instantiates a new Calculator.
     */
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

        String[] operations = {"+", "-", "*", "/"};
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


    /**
     * Initializes the main panel in the correct order
     */
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

    /**
     * Initializes the key pad digits.
     */
    public void initializeKeyPadDigits() {
        JButton[] keyPadDigits = new JButton[10];
        for (int i = 0; i < 10; i++) {
            keyPadDigits[i] = new JButton(i + "");
            keyPadDigits[i].addActionListener(getListener());
            getMainPanel().add(keyPadDigits[i]);
        }
    }

    /**
     * Initializes key adapter so that it only accepts numbers, dots, and adds functionality for the enter and backspace keys.
     */
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

    /**
     * Initializes adapter.
     */
    public void initializeAdapter() {
        FocusAdapter adapter = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setLastClicked((JTextField) e.getSource());
            }
        };
        setAdapter(adapter);
    }

    /**
     * Initializes listener.
     */
    public void initializeListener() {
        ActionListener listener = e -> getLastClicked().setText(getLastClicked().getText() + ((JButton) e.getSource()).getText());
        setListener(listener);
    }

    /**
     * Find the result of the two fields and return it as a string.
     *
     * @return the string
     */
    public String findResult() {
        if((getField1().getText().isBlank() && !getField2().getText().isBlank()) || (getField2().getText().length() == 1 && getField2().getText().contains("."))) {
            return getField2().getText();
        } else if((!getField1().getText().isBlank() && getField2().getText().isBlank()) || (getField1().getText().length() == 1 && getField1().getText().contains("."))) {
            return getField1().getText();
        } else if((getField1().getText().isBlank() && getField2().getText().isBlank())) {
            return "";
        } else {
            try {
                double x1 = Double.parseDouble(getField1().getText());
                double x2 = Double.parseDouble(getField2().getText());

                return Double.toString(calculate(x1, x2, String.valueOf(getCombo().getSelectedItem())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "Not an acceptable input";
            }

        }
    }

    /**
     * Calculate the two values depending on the operation given as a string and returns the result as a double.
     *
     * @param x1        the first value
     * @param x2        the second value
     * @param operation the operation
     * @return the result of the calculation
     */
    public static double calculate(double x1, double x2, String operation) {
        double result;
        //noinspection DuplicatedCode
        switch(operation) {
            case "+" -> result = x1 + x2;
            case "-" -> result = x1 - x2;
            case "*" -> result = x1 * x2;
            case "/" -> result = x1 / x2;
            default -> throw new IllegalArgumentException();
        }
        return result;
    }

    /**
     * Calculate the two values depending on the operation given as a string and returns the result as an int.
     *
     * @param x1        the first value
     * @param x2        the second value
     * @param operation the operation
     * @return the result of the calculation
     */
    public static int calculate(int x1, int x2, String operation) {
        int result;
        //noinspection DuplicatedCode
        switch(operation){
            case "+" -> result = x1 + x2;
            case "-" -> result = x1 - x2;
            case "*" -> result = x1 * x2;
            case "/" -> result = x1 / x2;
            default -> throw new IllegalArgumentException();
        }
        return result;
    }


    /**
     * Gets main panel.
     *
     * @return the main panel
     */
//Getters
    public JPanel getMainPanel() { return this.mainPanel; }

    /**
     * Gets key adapter.
     *
     * @return the key adapter
     */
    public KeyAdapter getKeyAdapter() { return this.keyAdapter; }

    /**
     * Gets adapter.
     *
     * @return the adapter
     */
    public FocusAdapter getAdapter() { return this.adapter; }

    /**
     * Gets listener.
     *
     * @return the listener
     */
    public ActionListener getListener() { return this.listener; }

    /**
     * Gets last clicked.
     *
     * @return the last clicked
     */
    public JTextField getLastClicked() { return this.lastClicked; }

    /**
     * Gets field 1.
     *
     * @return the field 1
     */
    public JTextField getField1() { return this.field1; }

    /**
     * Gets field 2.
     *
     * @return the field 2
     */
    public JTextField getField2() { return this.field2; }

    /**
     * Gets combo.
     *
     * @return the combo
     */
    public JComboBox<String> getCombo() { return this.combo; }

    /**
     * Gets result.
     *
     * @return the result
     */
    public JTextField getResult() { return this.result; }

    /**
     * Gets equals.
     *
     * @return the equals
     */
    public JButton getEquals() { return this.equals; }

    /**
     * Gets backspace.
     *
     * @return the backspace
     */
    public JButton getBackSpace() { return this.backSpace; }

    /**
     * Gets c.
     *
     * @return the c
     */
    public JButton getC() { return this.c; }

    /**
     * Gets dot.
     *
     * @return the dot
     */
    public JButton getDot() { return this.dot; }

    /**
     * Gets converted.
     *
     * @return the converted
     */
    public JLabel getConverted() { return converted; }

    /**
     * Sets key adapter.
     *
     * @param keyAdapter the key adapter
     */
//Setters
    public void setKeyAdapter(KeyAdapter keyAdapter) { this.keyAdapter = keyAdapter; }

    /**
     * Sets adapter.
     *
     * @param adapter the adapter
     */
    public void setAdapter(FocusAdapter adapter) { this.adapter = adapter; }

    /**
     * Sets listener.
     *
     * @param listener the listener
     */
    public void setListener(ActionListener listener) { this.listener = listener; }

    /**
     * Sets last clicked.
     *
     * @param lastClicked the last clicked
     */
    public void setLastClicked(JTextField lastClicked) { this.lastClicked = lastClicked; }
}