package Services;


import org.jboss.aerogear.security.otp.Totp;

public class OTPService {

    public String generateOTP() {
        String secret = "B2374TNIQ3HKC44";
        Totp generator = new Totp(secret);
        return generator.now();
    }
}
