package Validators;

import Models.UserDetails;

public class RegistrationFormValidator {

    private EmailValidator emailValidator;
    private PhoneValidators phoneValidators;

    public RegistrationFormValidator() {
        emailValidator = new EmailValidator();
        phoneValidators = new PhoneValidators();
    }

    public String validateAllFields(UserDetails userDetails) {

        if (userDetails.getFirstName().replaceAll("\\s+", "").equals(""))
            return "First Name is Compulsory field";

        if (userDetails.getLastName().replaceAll("\\s+", "").equals(""))
            return "Last Name is Compulsory field";

        if (userDetails.getEmail().replaceAll("\\s+", "").equals(""))
            return "Email is Compulsory field";
        else {
            if (!emailValidator.validate(userDetails.getEmail()))
                return "Invalid Email";
        }

        if (userDetails.getPhone().replaceAll("\\s+", "").equals(""))
            return "Phone Number is Compulsory field";
        else {
            if (!phoneValidators.validate(userDetails.getPhone()))
                return "Phone Number is Invalid";
        }

        if (userDetails.getPassword().replaceAll("\\s+", "").equals("")) {
            return "Password is Compulsory field";
        }


        return null;

    }

}
