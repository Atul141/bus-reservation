package Services;


import Models.OrderDetails;
import Models.Route;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;

import java.sql.Time;
import java.util.Date;

public class CancellationService {

    public int getRefundAmount(OrderDetails orderDetails, Route route) {
        Date date = route.getDate();
        Time time = route.getDepartureTime();
        date.setHours(time.getHours());

        DateTime start = new DateTime();
        DateTime end = new DateTime(date);
        int days = Days.daysBetween(start,end).getDays();
        long hours = Hours.hoursBetween(start,end).getHours();
        System.out.println("Days=" + days);
        System.out.println("Hours=" + hours);

        Double cancellationFactor;
        if (hours > 240)
            cancellationFactor = 0.2;
        else if (hours > 24)
            cancellationFactor = 0.5;
        else
            cancellationFactor = 1.0;
        return getCancellationFee(cancellationFactor, orderDetails.getPrice());
    }

    private int getCancellationFee(Double cancellation, int amount) {
        System.out.println(cancellation);
        System.out.println(amount);

        double cancellationFee =  (1.0 - cancellation) * amount;
        System.out.println(cancellationFee);

        return (int)cancellationFee;
    }


}
