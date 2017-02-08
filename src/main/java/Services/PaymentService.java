package Services;

import Models.Payment;


public class PaymentService {

    public boolean validateCreditCard(Payment payment) {
        PaymentGateway paymentGateway = new PaymentGateway();
        return paymentGateway.run(payment);

    }
}