import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigNumberCalculator extends Calculator {
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
            BigDecimal x1 = new BigDecimal(getField1().getText());
            BigDecimal x2 = new BigDecimal(getField2().getText());

            return calculate(x1, x2, String.valueOf(getCombo().getSelectedItem())).toString();
        }
    }

    public BigDecimal calculate(BigDecimal x1, BigDecimal x2, String operation) {
        BigDecimal result;
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
