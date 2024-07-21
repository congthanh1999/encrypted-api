package utils;

import java.util.regex.Pattern;

public class Base64Utils {
    public static String encode(byte[] data) {
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String data) {
        return java.util.Base64.getDecoder().decode(data);
    }

    public static boolean isBase64Encoded (String message) {
        Pattern base64Pattern = Pattern.compile("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$");

        return base64Pattern.matcher(message).matches();
    }
}
