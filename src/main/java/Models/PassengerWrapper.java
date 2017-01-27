package Models;


import java.util.ArrayList;
import java.util.List;

public class PassengerWrapper {

    private List<Passenger> passengerList;
    private List<String> genderList;

    public PassengerWrapper() {

        passengerList = new ArrayList<Passenger>();
        genderList = new ArrayList<String>();

    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public void add(Passenger passenger) {
        passengerList.add(passenger);
    }

    public List<String> getGenderList() {
        genderList.clear();
        genderList.add("Female");
        genderList.add("Male");
        return genderList;
    }

}
