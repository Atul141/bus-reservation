package Validators;

import Models.UserDetails;

public class RegistrationFormValidator {

    private EmailValidator emailValidator;
    private PhoneValidators phoneValidators;
    private InjectionValidator injectionValidator;

    public RegistrationFormValidator() {
        injectionValidator = new InjectionValidator();
        emailValidator = new EmailValidator();
        phoneValidators = new PhoneValidators();
    }

    public String validateAllFields(UserDetails userDetails) {
        String injectionError = "Special symbols are not allowed";
        if (userDetails.getFirstName().replaceAll("\\s+", "").equals("")) {
            return "First Name is Compulsory field";
        }
        if (injectionValidator.validateInjection(userDetails.getFirstName()))
            return injectionError;

        if (userDetails.getLastName().replaceAll("\\s+", "").equals("")) {
            return "Last Name is Compulsory field";
        }
        if (injectionValidator.validateInjection(userDetails.getLastName()))
            return injectionError;

        if (userDetails.getEmail().replaceAll("\\s+", "").equals(""))
            return "Email is Compulsory field";
        else {
            if (!emailValidator.validate(userDetails.getEmail())) {

                return "Invalid Email";
            }
        }
        if (injectionValidator.validateInjection(userDetails.getEmail()))

            return injectionError;
        if (userDetails.getPhone().replaceAll("\\s+", "").equals(""))
            return "Phone Number is Compulsory field";
        else {
            if (!phoneValidators.validate(userDetails.getPhone()))
                return "Phone Number is Invalid";
        }

        if (userDetails.getPassword().replaceAll("\\s+", "").equals("")) {
            return "Password is Compulsory field";
        }
        if (injectionValidator.validateInjection(userDetails.getPassword()))

            return injectionError;

        return null;

    }

}
