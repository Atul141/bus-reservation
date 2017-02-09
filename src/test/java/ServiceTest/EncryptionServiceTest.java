package ServiceTest;


import Services.EncryptService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class EncryptionServiceTest {

    private EncryptService encryptService;

    @Before
    public void setup() {
        encryptService = new EncryptService();
    }

    @Test
    public void shouldEncryptAnDecryptString() {
        String key = "asdfqaqwsaerdqsw";
        String text = "sample";
        String encrypt = encryptService.encryptString(text, key);
        String decrypt = encryptService.decryptString(encrypt, key);
        assertTrue(text.equals(decrypt));
    }
}
