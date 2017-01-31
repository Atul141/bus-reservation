package Services;


import Models.Passenger;
import Models.PassengerWrapper;
import Models.SelectedSeatWrapper;

import java.util.ArrayList;
import java.util.List;

public class PassengerService {

    private int maxAge = 125;

    public List<Passenger> getPassengerList(int number) {
        Passenger[] passengers = new Passenger[number];
        List<Passenger> passengerList = new ArrayList<Passenger>();
        for (int index = 0; index < number; index++) {
            passengers[index] = new Passenger();
            passengerList.add(passengers[index]);
        }
        return passengerList;
    }

    public List<Integer> getAgeList() {
        List<Integer> ageList = new ArrayList<Integer>();
        for (int index = 1; index <= maxAge; index++) {
            ageList.add(index);
        }
        return ageList;
    }

    public List<String> getGenderList() {
        List<String> genderList = new ArrayList<String>();
        genderList.add("-----");
        genderList.add("Female");
        genderList.add("Male");
        return genderList;
    }

    public PassengerWrapper allocateSeats(PassengerWrapper passengerWrapper, SelectedSeatWrapper selectedSeatWrapper) {
        int passengerWomen = 0, passengerSenior = 0, passengerDisabled = 0, passengerGeneral = 0;
        Passenger passenger;

        for (int index = 0; index < passengerWrapper.getPassengerList().size(); index++) {
            passenger = passengerWrapper.getPassengerList().get(index);
            if (passenger.getIsDisabled() == true && selectedSeatWrapper.getSelectedSeatDisabled().size() > passengerDisabled) {
                passenger.setSeat(selectedSeatWrapper.getSelectedSeatDisabled().get(passengerDisabled));
                passengerDisabled++;
                continue;
            }
            if (passenger.getIsSeniorCitizen() == true && selectedSeatWrapper.getSelectedSeatSeniorCitizen().size() > passengerSenior) {
                passenger.setSeat(selectedSeatWrapper.getSelectedSeatSeniorCitizen().get(passengerSenior));
                passengerSenior++;
                continue;
            }
            if (passenger.getGender().equals("Female") && selectedSeatWrapper.getSelectedSeatWomen().size() > passengerWomen) {
                passenger.setSeat(selectedSeatWrapper.getSelectedSeatWomen().get(passengerWomen));
                passengerWomen++;
                continue;
            }
            passenger.setSeat(selectedSeatWrapper.getSelectedSeatGeneral().get(passengerGeneral));
            passengerGeneral++;
        }
        return passengerWrapper;
    }
}
