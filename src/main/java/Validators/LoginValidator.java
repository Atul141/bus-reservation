package Validators;


import Models.UserDetails;

public class LoginValidator {

    private EmailValidator emailValidator;
    private InjectionValidator injectionValidator;


    public LoginValidator() {
        emailValidator = new EmailValidator();
        injectionValidator = new InjectionValidator();
    }

    public String validate(UserDetails userDetails) {

        String injectionError = "Special symbols are not allowed";

        if (userDetails.getEmail().replaceAll("\\s+", "").equals(""))
            return "Email is Compulsory field";
        else {
            if (!emailValidator.validate(userDetails.getEmail())) {

                return "Invalid Email";
            }
        }
        if (userDetails.getPassword().replaceAll("\\s+", "").equals("")) {

            return "Password is Compulsory field";
        }

        if (injectionValidator.validateInjection(userDetails.getEmail()))
            return injectionError;

        if (injectionValidator.validateInjection(userDetails.getPassword()))
            return injectionError;
        return null;
    }
}
