/**
 * The interface Binary converter.
 * TCSS 305 Project_#02
 * @author Douglas Johnston
 */
public interface BinaryConverter {
    /**
     * Convert binary string to int.
     *
     * @param x the binary string
     * @return the int form of the binary string
     */
    int convertToInt(String x);

    /**
     * Convert int to binary string.
     *
     * @param x the int
     * @return the binary string
     */
    String convertToBinary(int x);
}
