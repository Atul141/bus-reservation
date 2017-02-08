package Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PaymentWrapperService {

    public List<Integer> getYearsList(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> yearsList =new ArrayList<Integer>();
        for(int i=year;i<=year+10;i++)
        yearsList.add(i);
        return yearsList;
    }
    public List<Integer> getMonthsList(){
        List<Integer> monthsList = new ArrayList<Integer>();
        for(int i=1;i<=12;i++){
            monthsList.add(i);
        }
        return monthsList;
    }
    public List<String> getCardTypes(){
        List<String > cardType= new ArrayList<String>();
        cardType.add("Visa");
        cardType.add("American Express");
        cardType.add("MaterCard");
        return cardType;
    }

}
