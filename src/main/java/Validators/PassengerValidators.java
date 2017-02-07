package Validators;

import Models.Passenger;
import Models.PassengerWrapper;
import Models.SelectedSeatWrapper;

public class PassengerValidators {

    private InjectionValidator injectionValidator;

    public PassengerValidators() {

        injectionValidator=new InjectionValidator();
    }

    public String validatePassengers(PassengerWrapper passengerWrapper) {
        String injectionError = "Special symbols are not allowed";

        Passenger passenger;
        for (int index = 0; index < passengerWrapper.getPassengerList().size(); index++) {
            passenger = passengerWrapper.getPassengerList().get(index);

            if (passenger.getName().replaceAll("\\s+", "").equals("")) {

                return "Error!:Name filed in empty in Passenger" + (index + 1) + " ";
            }
            if (passenger.getAge() > 125 || passenger.getAge() <= 0)
                return "Error!:Age filed in invalid in Passenger" + (index + 1) + " ";

            if (passenger.getGender().equals("-----"))
                return "Error!:Please Enter Gender in Passenger" + (index + 1) + " ";

            if(injectionValidator.validateInjection(passenger.getName()))
                return injectionError;
        }
        return null;
    }

    public String validateSelectedSeatsWithPassengers(PassengerWrapper passengerWrapper, SelectedSeatWrapper selectedSeatWrapper) {

        int passengerWomen = 0, passengerSenior = 0, passengerDisabled = 0, passengerGeneral = 0, passengerTotal = 0;
        Passenger passenger;
        passengerGeneral = selectedSeatWrapper.getSelectedSeatGeneral().size();
        passengerTotal = selectedSeatWrapper.getSelectedSeatDisabled().size() + passengerGeneral + selectedSeatWrapper.getSelectedSeatWomen().size() + selectedSeatWrapper.getSelectedSeatSeniorCitizen().size();

        if (passengerTotal != passengerWrapper.getPassengerList().size())
            return "Error!:Number of selected seats doesn't match with number of passengers";

        for (int index = 0; index < passengerWrapper.getPassengerList().size(); index++) {
            passenger = passengerWrapper.getPassengerList().get(index);
            if (passenger.getIsDisabled() == true && selectedSeatWrapper.getSelectedSeatDisabled().size() > passengerDisabled) {
                passengerDisabled++;
                continue;
            }
            if (passenger.getIsSeniorCitizen() == true && selectedSeatWrapper.getSelectedSeatSeniorCitizen().size() > passengerSenior) {
                passengerSenior++;
                continue;
            }
            if (passenger.getGender().equals("Female") && selectedSeatWrapper.getSelectedSeatWomen().size() > passengerWomen) {
                passengerWomen++;
                continue;
            }
        }

        if (selectedSeatWrapper.getSelectedSeatWomen().size() > passengerWomen)
            return "Error!:Number of seat selected under Women reservation doesn't match with the passenger details";
        if (selectedSeatWrapper.getSelectedSeatSeniorCitizen().size() > passengerSenior)
            return "Error!:Number of seat selected under Senior Citizen reservation doesn't match with the passenger details";
        if (selectedSeatWrapper.getSelectedSeatDisabled().size() > passengerDisabled)
            return "Error!:Number of seat selected under Disabled reservation doesn't match with the passenger details";

        return null;

    }
}
