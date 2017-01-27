package Services;


import java.util.ArrayList;
import java.util.List;

public class NumberOfSeatService {

    private List<Integer> seatNumber;

    public NumberOfSeatService(){
        seatNumber=new ArrayList<Integer>();
    }
    public List<Integer> getSeatNumber(int number){
        for(int index=1;index<=number;index++) {
            seatNumber.add(index);
        }return seatNumber;
    }
}
