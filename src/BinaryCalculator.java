public class BinaryCalculator implements BinaryConverter {


    @Override
    public double convertToDouble(String x) {
        return Double.parseDouble(x);
    }

    @Override
    public String convertToBinary(double x) {
        return Integer.toBinaryString((int) x);
    }
}
