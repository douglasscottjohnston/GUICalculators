/**
 * The interface Hex converter.
 * TCSS 305 Project_#02
 * @author Douglas Johnston
 */
public interface HexConverter {
    /**
     * Convert hex string to int.
     *
     * @param x the hex string
     * @return the int form of the hex string
     */
    int convertToInt(String x);

    /**
     * Convert int to hex string.
     *
     * @param x the int
     * @return the hex string
     */
    String convertToHex(int x);
}
