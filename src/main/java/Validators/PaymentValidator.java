package Validators;

import Models.Payment;

public class PaymentValidator {

    public String validatePayment(Payment payment) {

        if (!(payment.getCardNumber().matches("\\d{10}")))
            return "Error!:Credit card number should have 10 digits";

        int length = (int) (Math.log10(payment.getCvvNumber()) + 1);

        if (length != 3)
            return "Error!:CVV should have 3 digits";
        System.out.println(payment.getName());
        if (payment.getName().replaceAll("\\s+", "").equals(""))
            return "Error!:Name field cannot be empty";


        return null;

    }
}