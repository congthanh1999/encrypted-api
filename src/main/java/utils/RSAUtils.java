package utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    private static final String privateKeyString = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCctXhb+/n30g3KxhbDGuceoQmba2/lQbWzwrrtyonjfP2lTj8WMgcfjmB5X+IE/SZQTiKQ7QeHz8Ga1vo0DUW/qSZ/ctoSQEcjqvpeqT/EEO6v6zRiv4aykitKLAhTd2LKbHcFmZWCGvqdq4W4miAMVhm7eIKtnmfuGwS+zcWdIeYjiNfjA97VNCAv25eHxOnMZjVRVPizEwFgPNPCFjP5Sa32hGdK9UxEJvcanRGQsgesuz3XIAYajcrjOGCaL7A8z2uxE8uZ1NHkSPhAmgGRepH6fipqqciFUFg6ptRkdab164j0Roegj0UyKSg2u5bHsVRsNjeKZ2BZPGPSc+a5AgMBAAECggEAD6/VQGz69vEsnwShDO8NnXnsmKWitfnzzs6ykGDu2Go/yEtKwqWno6OomeHMoef27MFDkcsebryP8nhsY6dD+QV9VHL6bCsJzALerPjniJCS2iKS9AMc4FWENpitjTBlX+LHb9Sx3AwoE/4yZLot9NouGX984lUZ7YKAITr/OgU9dHANTff1ggIwfOXLjFv9p8t6+fd8AZvsds5btMzdxFF7y9zQJ0OJMwNVWg8Qca6YZxd9R0HQ6cy0T+abgo9gPZdf22RmpF3CAfmfKkYc6mKndtwDirdwWo6f9DviPd2aCFsj9eYMhIJxV4NzvHQfoM9TpEnNuH6u8dSYLQaUkQKBgQDM8/WwjGyFtPzcacKIwQfRc7mCSTOE5InPNK1iPsNYGZOQuRA7UBgUdPYpoFAmX3wKb+ePyesIyWnqi/u/7o4+uuIdgob4dDJefxrfIA1dGj3PI9YhTuNEo+hSi4vlgn4FhU/a324ELRAP1cU25lbByuWb+Y0+mkcfrKg0+h8qMQKBgQDDvWkMjvkKgdi0VbA6D30zW6tMcI909st95Lj3pn1TtqZ5+3LLr1RQqN/dv9JcGR44rjl/p4V00VuEf33HUfoPjG3gLD9azo9+7AzcRXLYpfGz9LWNBM/I+WkiRzNNrWLc1BBgyCl2nY85G9YWXAhnoWcbzvbXPY84KXF/NhxbCQKBgQC3iloh6v9clgJEWSnvUf5wYhRlSqyqfAe12dTTwKUrhmwSFMkmdMm7uCqiiIB+fNAWgLR8njSvP8BsXUPsU/Rq1O2dw8CfS1+4JFeqLYgKMm/bypn+Zik81RQ+p912gW/d/c7PkXtynfBeXfApBAn5rnwFPGxy/bJn+TUBjGTQYQKBgC9VVlnbGQ4ZaDXzorHnnPcWa84ISiiWpY7ECJaahCQCPy+wRopK07DNpZUSFeR/jndWakEcWYFuPxtDWT8h675mzwsuqANlf/3JRzLc+HC7cD98JYr8ZuLKrUnEvftCum18OpmszZoJb+D3gkXV65JjzXNEERzrnf6OxwtkImdBAoGBAMSyLs16+VJplU00AqFIWcRXWWac3b84O7Jsa717W9lWKSj/DHS8tb0rfogA2RAIgS6MTxjSwWdMyOU+0KkXUlQ4gsv3paBRMZ15tQqzEHq8kr62I+rMF9s4g6/iDBhAjddccvs/H+0i/z2cOtiTd6CrQQ3HGT8N3L+A+Gars6qe";
    private static final String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnLV4W/v599INysYWwxrnHqEJm2tv5UG1s8K67cqJ43z9pU4/FjIHH45geV/iBP0mUE4ikO0Hh8/Bmtb6NA1Fv6kmf3LaEkBHI6r6Xqk/xBDur+s0Yr+GspIrSiwIU3diymx3BZmVghr6nauFuJogDFYZu3iCrZ5n7hsEvs3FnSHmI4jX4wPe1TQgL9uXh8TpzGY1UVT4sxMBYDzTwhYz+Umt9oRnSvVMRCb3Gp0RkLIHrLs91yAGGo3K4zhgmi+wPM9rsRPLmdTR5Ej4QJoBkXqR+n4qaqnIhVBYOqbUZHWm9euI9EaHoI9FMikoNruWx7FUbDY3imdgWTxj0nPmuQIDAQAB";

    public static void initFromString() {
        try {
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(Base64Utils.decode(publicKeyString));
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(Base64Utils.decode(privateKeyString));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(keySpecPublic);
            privateKey = keyFactory.generatePrivate(keySpecPrivate);
        } catch (Exception ignored) {
        }
    }

    public static byte[] encrypt(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(messageToBytes);
    }

    public static byte[] decrypt(String message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] encryptedBytes = Base64Utils.decode(message);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedBytes);
    }

    public static String signData(String data) throws Exception {
        byte[] signature = DigitalSignatureUtils.signData(data.getBytes(), privateKey);
        return Base64Utils.encode(signature);
    }

    public static Boolean verifySignature(String signedData, String data) throws Exception {
        byte[] signature = Base64Utils.decode(signedData);
        return DigitalSignatureUtils.verifyData(data.getBytes(), signature, publicKey);
    }
}
