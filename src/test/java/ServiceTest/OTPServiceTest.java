package ServiceTest;


import Services.OTPService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class OTPServiceTest {

    private OTPService otpService;

    @Before
    public void setup() {
        otpService = new OTPService();
    }

    @Test
    public void shouldGenerateOTP() {
        String otp = otpService.generateOTP();
        assertNotNull(otp);
    }
}
