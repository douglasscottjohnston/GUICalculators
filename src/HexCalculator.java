public class HexCalculator extends Calculator  implements HexConverter{
    @Override
    public double convertToDouble(String x) {
        return Double.parseDouble(x);
    }

    @Override
    public String convertToHex(double x) {
        return Integer.toHexString((int) x);
    }
}
