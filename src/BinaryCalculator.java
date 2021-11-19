import javax.swing.*;

public class BinaryCalculator extends Calculator implements BinaryConverter {

    private final int base = 2;

    BinaryCalculator() {
        this.setTitle("Binary Calculator");
    }

    @Override
    public int convertToInt(String x) {
        return Integer.parseUnsignedInt(x, base);
    }

    @Override
    public String convertToBinary(int x) {
        return Integer.toBinaryString(x);
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
    public String findResult() {
        int x1 = convertToInt(getField1().getText());
        int x2 = convertToInt(getField2().getText());

        return convertToBinary(calculate(x1, x2, String.valueOf(getCombo().getSelectedItem())));
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
}
