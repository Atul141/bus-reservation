package Services;


import ServiceImpl.MessageImpl;
import ServiceImpl.SyntaxSugar;
import org.jboss.aerogear.security.otp.Totp;

import java.io.IOException;
import java.net.Socket;

public class OTPService {

    public String generateOTP() {
        String secret = "B2374TNIQ3HKC44";
        Totp generator = new Totp(secret);
        return generator.now();
    }
    public void sendOTP(String otp, String phone) {
        String message=phone+"%"+otp;
        MessageImpl messageImpl=new MessageImpl();
        try {
            messageImpl.sendMessage(message,new Socket(SyntaxSugar.SERVER,SyntaxSugar.portSMS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
