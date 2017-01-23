package Validators;


import Models.UserDetails;

public class LoginValidator {
    private EmailValidator emailValidator;

    public LoginValidator() {
        emailValidator = new EmailValidator();
    }

    public String validate(UserDetails userDetails) {
        if (userDetails.getEmail().replaceAll("\\s+", "").equals(""))
            return "Email is Compulsory field";
        else {
            if (!emailValidator.validate(userDetails.getEmail()))
                return "Invalid Email";
        }
        if (userDetails.getPassword().replaceAll("\\s+", "").equals("")) {
            return "Password is Compulsory field";
        }
        return  null;
    }
}
