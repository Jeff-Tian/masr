package masr.common;

public class ArrayHelper {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String convertBytesToHex(byte[] a) {
        char[] hexChars = new char[a.length * 2];
        for (int j = 0; j < a.length; j++) {
            int v = a[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }
}
