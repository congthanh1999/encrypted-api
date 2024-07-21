package utils;

public class Base64Utils {
    public static String encode(byte[] data) {
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String data) {
        return java.util.Base64.getDecoder().decode(data);
    }
}
