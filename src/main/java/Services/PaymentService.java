package Services;

import Models.Payment;
import ServiceImpl.PaymentGatewayImpl;
import ServiceImpl.SyntaxSugar;

import java.io.IOException;
import java.net.Socket;


public class PaymentService {

    public boolean validateCreditCard(Payment payment) throws IOException {
        Socket socket = new Socket(SyntaxSugar.SERVER, SyntaxSugar.portPayment);
        PaymentGatewayImpl paymentGatewayImpl = new PaymentGatewayImpl(socket);
        return paymentGatewayImpl.ValidateCredentials(payment);

    }
}