import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * BinaryCalculator class that extends Calculator and is used to calculate binary numbers
 * TCSS 305 Project_#02
 * @author Douglas Johnston
 */
public class BinaryCalculator extends Calculator implements BinaryConverter {

    /**
     * Instantiates a new Binary calculator.
     */
    BinaryCalculator() {
        this.setTitle("Binary Calculator");
    }

    @Override
    public void initializeMainPanel() {
        getMainPanel().add(getField1());
        getMainPanel().add(getCombo());
        getMainPanel().add(getField2());
        getMainPanel().add(getEquals());
        getMainPanel().add(getResult());
        getMainPanel().add(getBackSpace());
        getMainPanel().add(getC());
    }

    @Override
    public void initializeKeyPadDigits() {
        JButton zero = new JButton("0");
        JButton one = new JButton("1");

        zero.addActionListener(getListener());
        one.addActionListener(getListener());

        getMainPanel().add(zero);
        getMainPanel().add(one);
        getMainPanel().add(getConverted());
    }

    @Override
    public void initializeKeyAdapter() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                if(key.getKeyChar() == '0' || key.getKeyChar() == '1') {
                    getLastClicked().setEditable(true);
                } else if(key.getKeyCode() == KeyEvent.VK_ENTER) {
                    getEquals().doClick();
                } else getLastClicked().setEditable(key.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }
        };
        setKeyAdapter(keyAdapter);
    }

    @Override
    public String findResult() {
        if(getField1().getText().isBlank() && !getField2().getText().isBlank()) {
            return getField2().getText();
        } else if(!getField1().getText().isBlank() && getField2().getText().isBlank()) {
            return getField1().getText();
        } else if(getField1().getText().isBlank() && getField2().getText().isBlank()) {
            return "";
        } else {
            try {
                int x1 = BinaryConverter.convertToInt(getField1().getText());
                int x2 = BinaryConverter.convertToInt(getField2().getText());
                int result;
                try {
                    result = calculate(x1, x2, String.valueOf(getCombo().getSelectedItem()));
                } catch(ArithmeticException e) {
                    e.printStackTrace();
                    return "Do not divide by zero";
                }


                getConverted().setText("\n Converted to integers: \n" + x1 + " " + getCombo().getSelectedItem() + " " + x2 + " = " + result);
                return BinaryConverter.convertToBinary(result);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "Not an acceptable input";
            }
        }

    }
}
