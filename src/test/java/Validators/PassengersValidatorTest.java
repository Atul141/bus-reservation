package Validators;


import Models.Passenger;
import Models.PassengerWrapper;
import Models.SelectedSeatWrapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PassengersValidatorTest {

    private PassengerValidators passengerValidators;
    private PassengerWrapper passengerWrapper;
    private Passenger passenger;
    private SelectedSeatWrapper selectedSeatWrapper;

    @Before
    public void setup() {
        passengerValidators = new PassengerValidators();
        passengerWrapper = new PassengerWrapper();
        passenger = new Passenger();
        passenger.setSeat("C1");
        passenger.setName("abd");
        passenger.setGender("Male");
        passenger.setAge(22);
        Passenger passenger1 = new Passenger();
        passenger1.setSeat("A1");
        passenger1.setName("abd");
        passenger1.setGender("Female");
        passenger1.setAge(34);
        Passenger passenger2 = new Passenger();
        passenger2.setSeat("B1");
        passenger2.setName("Sample");
        passenger2.setGender("Female");
        passenger2.setAge(24);
        passenger2.setIsDisabled(true);
        List<Passenger> passengerList = new ArrayList<Passenger>();
        passengerList.add(passenger1);
        passengerList.add(passenger2);
        passengerList.add(passenger);
        passengerWrapper.setPassengerList(passengerList);
        selectedSeatWrapper = new SelectedSeatWrapper();
    }

    @Test
    public void shouldReturnErrorWhenNameIsEmpty() {
        passenger.setName("   ");
        assertEquals("Error!:Name filed in empty in Passenger3 ", passengerValidators.validatePassengers(passengerWrapper));
    }

    @Test
    public void shouldReturnNotErrorWhenNameIsNotEmpty() {
        assertEquals(null, passengerValidators.validatePassengers(passengerWrapper));
    }

    @Test
    public void shouldReturnErrorWhenAgeIsInvalid() {
        passenger.setAge(200);
        assertEquals("Error!:Age filed in invalid in Passenger3 ", passengerValidators.validatePassengers(passengerWrapper));
    }

    @Test
    public void shouldReturnErrorIfNumberOfPassengersIsNotEqualToNumberOfSelectedSeats() {
        List<String> selectedSeatGeneral = new ArrayList<String>();
        selectedSeatGeneral.add("C1");
        selectedSeatGeneral.add("C2");
        selectedSeatWrapper.setSelectedSeatGeneral(selectedSeatGeneral);
        assertEquals("Error!:Number of selected seats doesn't match with number of passengers", passengerValidators.validateSelectedSeatsWithPassengers(passengerWrapper, selectedSeatWrapper));
    }

    @Test
    public void shouldReturnErrorIfSeatSelectedForWomenIsNotCorrect() {
        List<String> selectedSeatWomen = new ArrayList<String>();
        selectedSeatWomen.add("A1");
        selectedSeatWomen.add("A2");
        selectedSeatWomen.add("A3");

        selectedSeatWrapper.setSelectedSeatWomen(selectedSeatWomen);
        assertEquals("Error!:Number of seat selected under Women reservation doesn't match with the passenger details", passengerValidators.validateSelectedSeatsWithPassengers(passengerWrapper, selectedSeatWrapper));
    }

    @Test
    public void shouldNotReturnErrorIfSeatSelectedForWomenIsCorrect() {
        List<String> selectedSeatGeneral = new ArrayList<String>();
        selectedSeatGeneral.add("C1");
        List<String> selectedSeatWomen = new ArrayList<String>();
        selectedSeatWomen.add("A1");
        selectedSeatWomen.add("A2");

        selectedSeatWrapper.setSelectedSeatGeneral(selectedSeatGeneral);
        selectedSeatWrapper.setSelectedSeatWomen(selectedSeatWomen);
        assertEquals(null, passengerValidators.validateSelectedSeatsWithPassengers(passengerWrapper, selectedSeatWrapper));
    }

    @Test
    public void shouldNotReturnErrorIfSeatSelectedForDisabledIsCorrect() {
        List<String> selectedSeatGeneral = new ArrayList<String>();
        selectedSeatGeneral.add("C1");
        List<String> selectedSeatWomen = new ArrayList<String>();
        selectedSeatWomen.add("A1");

        List<String> selectedSeatDisabled = new ArrayList<String>();
        selectedSeatDisabled.add("B1");

        selectedSeatWrapper.setSelectedSeatGeneral(selectedSeatGeneral);
        selectedSeatWrapper.setSelectedSeatWomen(selectedSeatWomen);
        selectedSeatWrapper.setSelectedSeatDisabled(selectedSeatDisabled);

        assertEquals(null, passengerValidators.validateSelectedSeatsWithPassengers(passengerWrapper, selectedSeatWrapper));
    }

    @Test
    public void shouldReturnErrorIfSeatSelectedForDisabledIsNotCorrect() {
        List<String> selectedSeatWomen = new ArrayList<String>();
        selectedSeatWomen.add("A1");

        List<String> selectedSeatDisabled = new ArrayList<String>();
        selectedSeatDisabled.add("B1");
        selectedSeatDisabled.add("B2");

        selectedSeatWrapper.setSelectedSeatWomen(selectedSeatWomen);
        selectedSeatWrapper.setSelectedSeatDisabled(selectedSeatDisabled);

        assertEquals("Error!:Number of seat selected under Disabled reservation doesn't match with the passenger details", passengerValidators.validateSelectedSeatsWithPassengers(passengerWrapper, selectedSeatWrapper));
    }

    @Test
    public void shouldReturnErrorIfSeatSelectedForSeniorCitizenIsNotCorrect() {
        List<String> selectedSeatWomen = new ArrayList<String>();
        selectedSeatWomen.add("A1");

        List<String> selectedSeatSenior = new ArrayList<String>();
        selectedSeatSenior.add("B3");
        selectedSeatSenior.add("B4");

        selectedSeatWrapper.setSelectedSeatWomen(selectedSeatWomen);
        selectedSeatWrapper.setSelectedSeatSeniorCitizen(selectedSeatSenior);

        assertEquals("Error!:Number of seat selected under Senior Citizen reservation doesn't match with the passenger details", passengerValidators.validateSelectedSeatsWithPassengers(passengerWrapper, selectedSeatWrapper));
    }
    @Test
    public void shouldReturnNotErrorIfSeatSelectedForSeniorCitizenIsCorrect() {
        List<String> selectedSeatWomen = new ArrayList<String>();
        selectedSeatWomen.add("A1");

        List<String> selectedSeatGeneral = new ArrayList<String>();
        selectedSeatGeneral.add("C1");
        selectedSeatGeneral.add("C2");

        selectedSeatWrapper.setSelectedSeatWomen(selectedSeatWomen);
        selectedSeatWrapper.setSelectedSeatGeneral(selectedSeatGeneral);

        assertEquals(null, passengerValidators.validateSelectedSeatsWithPassengers(passengerWrapper, selectedSeatWrapper));
    }


}
