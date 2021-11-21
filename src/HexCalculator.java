import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * HexCalculator class that extends Calculator and is used to calculate hexadecimal numbers
 * TCSS 305 Project_#02
 *
 * @author Douglas Johnston
 */
public class HexCalculator extends Calculator  implements HexConverter{

    /**
     * Instantiates a new Hex calculator.
     */
    HexCalculator() {
        this.setTitle("Hexadecimal Calculator");
    }

    @Override
    public int convertToInt(String x) {
        return Integer.parseUnsignedInt(x, 16);
    }

    @Override
    public String convertToHex(int x) {
        return Integer.toHexString(x).toUpperCase();
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
        JButton[] keyPadDigits = new JButton[16];
        for (int i = 0; i < 16; i++) {
            if(i > 9) {
                keyPadDigits[i] = new JButton(((char)(i + 55)) + ""); //A-F buttons
            } else {
                keyPadDigits[i] = new JButton(i + "");
            }
            keyPadDigits[i].addActionListener(getListener());
            getMainPanel().add(keyPadDigits[i]);
        }
        getMainPanel().add(getConverted());
    }

    @Override
    public void initializeKeyAdapter() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                if(key.getKeyChar() <= '9' && key.getKeyChar() >= '0') {
                    getLastClicked().setEditable(true);
                } else if(key.getKeyChar() >= 'A' && key.getKeyChar() <= 'F') {
                    getLastClicked().setEditable(true);
                } else if(key.getKeyChar() >= 'a' && key.getKeyChar() <= 'f') {
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
            int x1 = convertToInt(getField1().getText());
            int x2 = convertToInt(getField2().getText());
            int result = calculate(x1, x2, String.valueOf(getCombo().getSelectedItem()));

            getConverted().setText("\n Converted to integers: \n" + x1 +" + " + x2 + " = " + result);

            return convertToHex(result);
        }
    }
}
