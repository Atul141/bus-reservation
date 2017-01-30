package Validators;


import Models.Passenger;
import Models.PassengerWrapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PassengersValidatorTest {

    private PassengerValidators passengerValidators;
    private PassengerWrapper passengerWrapper;
    private Passenger passenger;

    @Before
    public void setup() {
        passengerValidators = new PassengerValidators();
        passengerWrapper = new PassengerWrapper();
        passenger = new Passenger();
        passenger.setSeat("A1");
        passenger.setName("abd");
        passenger.setGender("Male");
        passenger.setAge(22);
        List<Passenger> passengerList = new ArrayList<Passenger>();
        passengerList.add(passenger);
        passengerWrapper.setPassengerList(passengerList);
    }

    @Test
    public void shouldReturnErrorWhenNameIsEmpty() {
        passenger.setName("   ");
        assertEquals("Error!:Name filed in empty in Passenger1 ", passengerValidators.validatePassengers(passengerWrapper));
    }
    @Test
    public void shouldReturnNotErrorWhenNameIsNotEmpty() {
        assertEquals(null, passengerValidators.validatePassengers(passengerWrapper));
    }
    @Test
    public void shouldReturnErrorWhenAgeIsInvalid() {
        passenger.setAge(200);
        assertEquals("Error!:Age filed in invalid in Passenger1 ", passengerValidators.validatePassengers(passengerWrapper));
    }


}
