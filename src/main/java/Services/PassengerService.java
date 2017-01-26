package Services;


import Models.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerService {

    public List<Passenger> getPassengerList() {
        Passenger passenger1 = new Passenger();
        Passenger passenger2 = new Passenger();
        Passenger passenger3 = new Passenger();
        Passenger passenger4 = new Passenger();
        Passenger passenger5 = new Passenger();
        passenger1.setSeat("D1");
        List<Passenger> passengerList = new ArrayList<Passenger>();
        passengerList.add(passenger1);
        passengerList.add(passenger2);
        passengerList.add(passenger3);
        passengerList.add(passenger4);
        passengerList.add(passenger5);
        return passengerList;
    }
}
