package Services;


import java.util.ArrayList;
import java.util.List;

public class NumberOfSeatService {

    public List<Integer> getSeatNumber(int number) {
        List<Integer> seatNumber = new ArrayList<Integer>();
        for (int index = 1; index <= number; index++) {
            seatNumber.add(index);
        }
        return seatNumber;
    }
}
