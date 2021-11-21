import javax.swing.*;

/**
 * Main class that runs the calculator application and formats the different calculators into one frame.
 * TCSS 305 Project_#02
 * @author Douglas Johnston
 */
public class Main {
    public static DecimalCalculator decimal;
    public static HexCalculator hex;
    public static BinaryCalculator binary;
    public static BigNumberCalculator bigNum;
    public static JTabbedPane tabs;
    public static JFrame main;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        decimal = new DecimalCalculator();
        hex = new HexCalculator();
        binary = new BinaryCalculator();
        bigNum = new BigNumberCalculator();
        tabs = new JTabbedPane();
        main = new JFrame("Calculators");

        //formats the main panels of the calculators into one tabbed pane
        tabs.addTab("Decimal Calculator", decimal.getMainPanel());
        tabs.addTab("Hexadecimal Calculator", hex.getMainPanel());
        tabs.addTab("Binary Calculator", binary.getMainPanel());
        tabs.addTab("Big Number Calculator", bigNum.getMainPanel());

        //puts the tabbed pane into a frame
        main.add(tabs);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(850, 400);
        main.setVisible(true);

    }
}
