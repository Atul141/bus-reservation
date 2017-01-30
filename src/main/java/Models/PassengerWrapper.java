package Models;


import java.util.ArrayList;
import java.util.List;

public class PassengerWrapper {

    private List<Passenger> passengerList;
    private List<String> genderList;
    private List<Integer> ageList;

    public PassengerWrapper() {

        passengerList = new ArrayList<Passenger>();
        genderList = new ArrayList<String>();
        ageList = new ArrayList<Integer>();

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



    public List<Integer> getAgeList() {
        return ageList;
    }

    public void setAgeList(List<Integer> ageList) {
        this.ageList = ageList;
    }

    public List<String> getGenderList() {
        return genderList;
    }

    public void setGenderList(List<String> genderList) {
        this.genderList = genderList;
    }
}
