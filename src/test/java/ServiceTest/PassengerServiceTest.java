package ServiceTest;

import Models.Passenger;
import Services.PassengerService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PassengerServiceTest {

@Test
    public void shouldReturnEmptyListOfPassengersIfZeroIsPasses(){
    PassengerService passengerService=new PassengerService();
    List<Passenger> passengerList=new ArrayList<Passenger>();
    assertEquals(passengerList,passengerService.getPassengerList(0));
}

@Test
    public void shouldReturnAListOfSinglePassengerIfOneIsPasses(){
    PassengerService passengerService=new PassengerService();
    assertEquals(1,passengerService.getPassengerList(1).size());
}

@Test
    public void shouldReturnListOfSpecifiedNumberOfPassengerForTheNumberPasses(){
    PassengerService passengerService=new PassengerService();
    assertEquals(5,passengerService.getPassengerList(5).size());
}

@Test
    public void shouldReturnListOfAge(){
        PassengerService passengerService=new PassengerService();
        List<Integer> ageList=new ArrayList<Integer>();
        for(int index=1;index<=125;index++){
            ageList.add(index);
        }
    assertEquals(ageList,passengerService.getAgeList());

    }

    @Test
    public void shouldReturnListOfGenders(){
        PassengerService passengerService=new PassengerService();
        List<String > genderList=new ArrayList<String>();
        genderList.add("Female");
        genderList.add("Male");
        assertEquals(genderList,passengerService.getGenderList());

    }
}
