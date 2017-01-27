package Services;


import java.util.HashMap;
import java.util.Map;

public class NumberOfSeatService {

    private Map<Integer,Integer> seatNumber;

    public NumberOfSeatService(){
        seatNumber=new HashMap<Integer, Integer>();
    }
    public Map<Integer,Integer> getSeatNumber(){
        seatNumber.put(1,1);
        seatNumber.put(2,2);
        seatNumber.put(3,3);
        seatNumber.put(4,4);
        seatNumber.put(5,5);
        seatNumber.put(6,6);
        seatNumber.put(7,7);
        seatNumber.put(8,8);
        seatNumber.put(9,9);
        return seatNumber;
    }
}
