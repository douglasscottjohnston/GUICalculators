import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BinaryCalculator extends Calculator implements BinaryConverter {

    BinaryCalculator() {
        this.setTitle("Binary Calculator");
    }

    @Override
    public int convertToInt(String x) {
        return Integer.parseUnsignedInt(x, 2);
    }

    @Override
    public String convertToBinary(int x) {
        return Integer.toBinaryString(x);
    }

    @Override
    public void initializeMainPanel() {
        getMainPanel().add(getField1());
        getMainPanel().add(getCombo());
        getMainPanel().add(getField2());
        getMainPanel().add(getResult());
        getMainPanel().add(getEquals());
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
    }

    @Override
    public void initializeKeyAdapter() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent key) {
                if(key.getKeyChar() == '0' || key.getKeyChar() == '1') {
                    getLastClicked().setEditable(true);
                } else getLastClicked().setEditable(key.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }
        };
        setKeyAdapter(keyAdapter);
    }

    @Override
    public String findResult() {
        int x1 = convertToInt(getField1().getText());
        int x2 = convertToInt(getField2().getText());

        return convertToBinary(calculate(x1, x2, String.valueOf(getCombo().getSelectedItem())));
    }
}
