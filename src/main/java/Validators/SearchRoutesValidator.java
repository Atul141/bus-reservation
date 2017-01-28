package Validators;


import Models.Route;

public class SearchRoutesValidator {

    public String validateAllFields(Route route, String date) {

        if (route.getSource().replaceAll("\\s+", "").equals(""))
            return "Error!:Source is Compulsory field";

        if (route.getDestination().replaceAll("\\s+", "").equals(""))
            return "Error!:Destination is Compulsory field";

        if (date.replaceAll("\\s+", "").equals(""))
            return "Error!:Date is Compulsory field";
        return null;

    }
}