package Services;


import Models.Passenger;

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

}
