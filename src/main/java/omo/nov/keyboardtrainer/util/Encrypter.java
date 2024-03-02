package omo.nov.keyboardtrainer.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encrypter {
    public static Integer decrypt(String encryptedValue) throws Exception {
        try {
            String key = "your16ByteKey123";
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] decodedEncryptedValue = decodeBase64WithoutBackslash(encryptedValue);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(decodedEncryptedValue);
            String decryptedValueString = new String(decryptedBytes);
            return Integer.parseInt(decryptedValueString);
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] decodeBase64WithoutBackslash(String input) {
        String base64 = input.replace("_", "\\"); // Replace underscore with backslash
        return Base64.getDecoder().decode(base64);
    }
}
