import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigNumberCalculator that extends Calculator and is used to calculate BigNumbers.
 * TCSS 305 Project_#02
 * @author Douglas Johnston
 */
public class BigNumberCalculator extends Calculator {
    /**
     * Instantiates a new Big number calculator.
     */
    BigNumberCalculator() {
        this.setTitle("Big Number Calculator");
    }

    @Override
    public String findResult() {
        if((getField1().getText().isBlank() && !getField2().getText().isBlank()) || (getField2().getText().length() == 1 && getField2().getText().contains("."))) {
            return getField2().getText();
        } else if((!getField1().getText().isBlank() && getField2().getText().isBlank()) || (getField1().getText().length() == 1 && getField1().getText().contains("."))) {
            return getField1().getText();
        } else if((getField1().getText().isBlank() && getField2().getText().isBlank())) {
            return "";
        } else {
            try {
                BigDecimal x1 = new BigDecimal(getField1().getText());
                BigDecimal x2 = new BigDecimal(getField2().getText());
                try {
                    return calculate(x1, x2, String.valueOf(getCombo().getSelectedItem())).toString();
                } catch(ArithmeticException e) {
                    e.printStackTrace();
                    return "Do not divide by zero";
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "Not an acceptable input";
            }
        }
    }

    /**
     * Calculate the two values depending on the operation given as a string and returns the result as a BigDecimal.
     *
     * @param x1        the first value
     * @param x2        the second value
     * @param operation the operation
     * @return the result of the calculation
     */
    public BigDecimal calculate(BigDecimal x1, BigDecimal x2, String operation) {
        BigDecimal result;
        if(operation.equals("/") && x2.equals(BigDecimal.ZERO)) {
            getResult().setText("Do not divide by zero");
            throw new ArithmeticException();
        }
        switch(operation) {
            case "+" -> result = x1.add(x2);
            case "-" -> result = x1.subtract(x1);
            case "*" -> result = x1.multiply(x2);
            case "/" -> result = x1.divide(x2, RoundingMode.UP);
            default -> throw new IllegalArgumentException();
        }
        return result;
    }
}
