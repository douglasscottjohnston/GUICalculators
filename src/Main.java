import javax.swing.*;

public class Main {
    public static DecimalCalculator decimal;
    public static HexCalculator hex;
    public static BinaryCalculator binary;
    public static BigNumberCalculator bigNum;


    public static void main(String[] args) {
        decimal = new DecimalCalculator();
        binary = new BinaryCalculator();

        decimal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        decimal.setSize(300, 300);
        decimal.setVisible(true);

        binary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        binary.setSize(300, 300);
        binary.setVisible(true);

    }
}
