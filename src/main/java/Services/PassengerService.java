package Services;


import Models.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerService {

    public List<Passenger> getPassengerList(int number) {
        Passenger[] passengers = new Passenger[number];
        List<Passenger> passengerList = new ArrayList<Passenger>();
        for (int index = 0; index < number; index++) {
            passengers[index] = new Passenger();
            passengerList.add(passengers[index]);
        }
        return passengerList;
    }
}
