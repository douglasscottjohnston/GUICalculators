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
    static int convertToInt(String x) {
        return Integer.parseUnsignedInt(x, 16);
    }

    /**
     * Convert int to hex string.
     *
     * @param x the int
     * @return the hex string
     */
    static String convertToHex(int x) {
        return Integer.toHexString(x).toUpperCase();
    }
}
