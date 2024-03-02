package omo.nov.keyboardtrainer.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encrypter {
    public static Integer numberEncrypter(String encryptedValue) {
        try {
            String key = "your16ByteKey123";
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
            String decryptedValue = new String(cipher.doFinal(decodedValue));
            return Integer.valueOf(decryptedValue);
        } catch (Exception e) {
            return null;
        }
    }
}
