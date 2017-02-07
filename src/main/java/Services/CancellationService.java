package Services;


import Models.OrderDetails;
import Models.Route;
import org.joda.time.DateTime;
import org.joda.time.Hours;

import java.sql.Time;
import java.util.Date;

public class CancellationService {
    private final static int tenDay = 240;
    private final static int oneDay = 24;
    private final static double twentyPercent = .2;
    private final static double fiftyPercent = .5;
    private final static double hundredPercent = 1.0;

    public int getRefundAmount(OrderDetails orderDetails, Route route) {
        Date date = route.getDate();
        Time time = route.getDepartureTime();
        date.setHours(time.getHours());

        DateTime start = new DateTime();
        DateTime end = new DateTime(date);
        long hours = Hours.hoursBetween(start, end).getHours();

        Double cancellationFactor;
        if (hours > tenDay)
            cancellationFactor = twentyPercent;
        else if (hours > oneDay)
            cancellationFactor = fiftyPercent;
        else
            cancellationFactor = hundredPercent;
        return getCancellationFee(cancellationFactor, orderDetails.getPrice());
    }

    private int getCancellationFee(Double cancellation, int amount) {
        double cancellationFee = (hundredPercent - cancellation) * amount;
        return (int) cancellationFee;
    }


}
