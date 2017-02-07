package Validators;


import Models.Route;
import Models.SelectedSeatWrapper;

public class SearchRoutesValidator {

    private InjectionValidator injectionValidator;

    public SearchRoutesValidator(){
        injectionValidator=new InjectionValidator();
    }
    public String validateAllFields(Route route, String date) {

        String injectionError = "Special symbols are not allowed";

        if (route.getSource().replaceAll("\\s+", "").equals("")) {
            return "Error!:Source is Compulsory field";
        }
        if(injectionValidator.validateInjection(route.getSource()))
            return injectionError;

        if (route.getDestination().replaceAll("\\s+", "").equals("")) {
            return "Error!:Destination is Compulsory field";
        }
        if(injectionValidator.validateInjection(route.getDestination()))
            return injectionError;

        if (date.replaceAll("\\s+", "").equals("")) {

            return "Error!:Date is Compulsory field";
        }
        if(injectionValidator.validateInjection(route.getDate().toString()))
            return injectionError;
        return null;

    }
}