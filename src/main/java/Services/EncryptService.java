package Services;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptService {

    private static String IV = "aaaaaaaaaaaaaaaa";
    private static final String UNICODE_FORMAT = "ISO-8859-1";

    private String padd(String plaintext) {
        while (plaintext.length() % 16 != 0) {
            plaintext += "\0";
        }
        return plaintext;
    }

    public String encryptString(String plaintext, String encryptionKey) {
        try {
            byte[] cipher = encrypt(padd(plaintext), encryptionKey);
            return new String(cipher, UNICODE_FORMAT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String decryptString(String encString, String encryptionKey) {
        try {
            String decrypted = decrypt(encString.getBytes(UNICODE_FORMAT), encryptionKey);
            return unPad(decrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private String unPad(String decrypted) {

        String result = decrypted.replaceAll("\0", "");
        return result;
    }

    private byte[] encrypt(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(UNICODE_FORMAT), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes(UNICODE_FORMAT)));
        return cipher.doFinal(plainText.getBytes(UNICODE_FORMAT));
    }

    private String decrypt(byte[] cipherText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(UNICODE_FORMAT), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes(UNICODE_FORMAT)));
        return new String(cipher.doFinal(cipherText), UNICODE_FORMAT);
    }

}