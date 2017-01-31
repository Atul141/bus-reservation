package ServiceTest;

import Services.NumberOfSeatService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NumberOfSeatServiceTest {


    @Test
    public void shouldReturnAnEmptyListIfZeroIsPassed() {
        NumberOfSeatService numberOfSeatService = new NumberOfSeatService();
        List<Integer> integerList = new ArrayList<Integer>();
        assertEquals(integerList, numberOfSeatService.getSeatNumber(0));

    }
    @Test
    public void shouldReturnAListWithOneIfOneIsPassed() {
        NumberOfSeatService numberOfSeatService = new NumberOfSeatService();
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        assertEquals(integerList, numberOfSeatService.getSeatNumber(1));

    }

    @Test
    public void shouldReturnListOfSequenceNumbersForAGivenNumber() {
        NumberOfSeatService numberOfSeatService = new NumberOfSeatService();
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.add(6);
        assertEquals(integerList, numberOfSeatService.getSeatNumber(6));
    }

}
