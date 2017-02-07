package Validators;

import Models.Payment;

public class PaymentValidator {

    private InjectionValidator injectionValidator;

    public PaymentValidator() {
        injectionValidator = new InjectionValidator();
    }

    public String validatePayment(Payment payment) {
        String injectionError = "Special symbols are not allowed";

        if (!(payment.getCardNumber().matches("\\d{10}"))) {
            return "Error!:Credit card number should have 10 digits";
        }
        int length = (int) (Math.log10(payment.getCvvNumber()) + 1);

        if (length != 3)
            return "Error!:CVV should have 3 digits";
        if (payment.getName().replaceAll("\\s+", "").equals("")) {

            return "Error!:Name field cannot be empty";
        }
        if (injectionValidator.validateInjection(payment.getName()))
            return injectionError;
        return null;

    }
}