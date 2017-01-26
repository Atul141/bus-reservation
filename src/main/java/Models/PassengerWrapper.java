package Models;


import java.util.ArrayList;
import java.util.List;

public class PassengerWrapper {

    private List<Passenger> passengerList;

    public PassengerWrapper(){
        passengerList=new ArrayList<Passenger>();
    }
    public List<Passenger> getPassengerList(){
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList){
        this.passengerList=passengerList;
    }
    public void add(Passenger passenger){
        passengerList.add(passenger);
    }

}
