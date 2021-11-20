import javax.swing.*;

public class Main {
    public static DecimalCalculator decimal;
    public static HexCalculator hex;
    public static BinaryCalculator binary;
    public static BigNumberCalculator bigNum;


    public static void main(String[] args) {
        decimal = new DecimalCalculator();
        hex = new HexCalculator();
        binary = new BinaryCalculator();
        bigNum = new BigNumberCalculator();

        decimal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        decimal.setSize(300, 300);
        decimal.setVisible(true);

        hex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hex.setSize(300, 300);
        hex.setVisible(true);

        binary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        binary.setSize(300, 300);
        binary.setVisible(true);

    }
}
