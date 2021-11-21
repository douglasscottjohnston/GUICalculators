import javax.swing.*;

public class Main {
    public static DecimalCalculator decimal;
    public static HexCalculator hex;
    public static BinaryCalculator binary;
    public static BigNumberCalculator bigNum;
    public static JTabbedPane tabs;
    public static JFrame main;


    public static void main(String[] args) {
        decimal = new DecimalCalculator();
        hex = new HexCalculator();
        binary = new BinaryCalculator();
        bigNum = new BigNumberCalculator();
        tabs = new JTabbedPane();
        main = new JFrame("Calculators");

        tabs.addTab("Decimal Calculator", decimal.getMainPanel());
        tabs.addTab("Hexadecimal Calculator", hex.getMainPanel());
        tabs.addTab("Binary Calculator", binary.getMainPanel());
        tabs.addTab("Big Number Caclulaotr", bigNum.getMainPanel());


        main.add(tabs);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(850, 400);
        main.setVisible(true);

    }
}
