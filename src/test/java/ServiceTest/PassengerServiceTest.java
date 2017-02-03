package ServiceTest;

import Models.Passenger;
import Models.PassengerWrapper;
import Models.SelectedSeatWrapper;
import ServiceImplTest.ConfigTest;
import Services.PassengerService;
import Validators.PassengerValidators;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PassengerServiceTest {

    private PassengerWrapper passengerWrapper;
    private SelectedSeatWrapper selectedSeatWrapper;
    private PassengerService passengerService;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        passengerWrapper = configTest.getPassengerWrapper();
        passengerService = new PassengerService();
        selectedSeatWrapper = new SelectedSeatWrapper();
    }

    @Test
    public void shouldReturnEmptyListOfPassengersIfZeroIsPasses() {
        PassengerService passengerService = new PassengerService();
        List<Passenger> passengerList = new ArrayList<Passenger>();
        assertEquals(passengerList, passengerService.getPassengerList(0));
    }

    @Test
    public void shouldReturnAListOfSinglePassengerIfOneIsPasses() {
        PassengerService passengerService = new PassengerService();
        assertEquals(1, passengerService.getPassengerList(1).size());
    }

    @Test
    public void shouldReturnListOfSpecifiedNumberOfPassengerForTheNumberPasses() {
        PassengerService passengerService = new PassengerService();
        assertEquals(5, passengerService.getPassengerList(5).size());
    }

    @Test
    public void shouldReturnListOfAge() {
        PassengerService passengerService = new PassengerService();
        List<Integer> ageList = new ArrayList<Integer>();
        for (int index = 1; index <= 125; index++) {
            ageList.add(index);
        }
        assertEquals(ageList, passengerService.getAgeList());

    }

    @Test
    public void shouldReturnListOfGenders() {
        PassengerService passengerService = new PassengerService();
        List<String> genderList = new ArrayList<String>();
        genderList.add("-----");
        genderList.add("Female");
        genderList.add("Male");
        assertEquals(genderList, passengerService.getGenderList());

    }

    @Test
    public void shouldAllocateSeats() {

        List<String> selectedSeatWomen = new ArrayList<String>();
        selectedSeatWomen.add("A1");

        List<String> selectedSeatGeneral = new ArrayList<String>();
        selectedSeatGeneral.add("C1");
        selectedSeatGeneral.add("C2");

        selectedSeatWrapper.setSelectedSeatWomen(selectedSeatWomen);
        selectedSeatWrapper.setSelectedSeatGeneral(selectedSeatGeneral);

        passengerWrapper = passengerService.allocateSeats(passengerWrapper, selectedSeatWrapper);

        assertEquals("A1", passengerWrapper.getPassengerList().get(0).getSeat());
        assertEquals("C1", passengerWrapper.getPassengerList().get(1).getSeat());
        assertEquals("C2", passengerWrapper.getPassengerList().get(2).getSeat());
    }
}
