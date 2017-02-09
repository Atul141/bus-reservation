package Services;

import Models.Payment;

import java.io.IOException;
import java.net.Socket;


public class PaymentService {

    public boolean validateCreditCard(Payment payment) throws IOException {
        Socket socket = new Socket("localhost", 6066);
        PaymentGateway paymentGateway = new PaymentGateway(socket);
        return paymentGateway.ValidateCredentials(payment);

    }
}