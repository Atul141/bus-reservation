package Validators;

import Models.Passenger;
import Models.PassengerWrapper;

public class PassengerValidators {

    public String validatePassengers(PassengerWrapper passengerWrapper) {

        Passenger passenger;
        for (int index = 0; index < passengerWrapper.getPassengerList().size(); index++) {
            passenger = passengerWrapper.getPassengerList().get(index);

            if (passenger.getName().replaceAll("\\s+", "").equals(""))
                return "Error!:Name filed in empty in Passenger" + (index + 1) + " ";

            if (passenger.getAge() > 125|| passenger.getAge()<=0 )
                return "Error!:Age filed in invalid in Passenger" + (index + 1) + " ";
        }
        return null;
    }
}
