package Services;


import Models.OrderDetails;
import Models.Route;
import org.joda.time.DateTime;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class CancellationService {

    public long getRefundAmount(OrderDetails orderDetails, Route route) {
        Date date = route.getDate();
        Time time = route.getDepartureTime();
        int hours = getTimeDifference(time);
        int days = getDateDifference(date);

        if (hours < 0) {
            hours += 24;
            days -= 1;
        }

//        System.out.println(days);
        System.out.println("HOUR"+hours);
        Double cancellationFactor = 0.0;
        if (days > 10)
            cancellationFactor = 0.2;
        else if (days > 1)
            cancellationFactor = 0.5;
        else if (hours < 3)
            cancellationFactor = 1.0;
        return getCancellationFee(cancellationFactor, orderDetails.getPrice());
    }

    private int getCancellationFee(Double cancellation, int amount) {
//        System.out.println(cancellation);
        int cancellationFee = (int) (1.0 - cancellation) * amount;

        return cancellationFee;
    }

    private int getTimeDifference(Time time) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR, time.getHours());
        calendar.set(Calendar.MINUTE, time.getMinutes());
        Calendar currentCalender=Calendar.getInstance();
        int currentHour=currentCalender.get(Calendar.HOUR);
        int currentAm=currentCalender.get(Calendar.AM_PM);
        int hour = calendar.get(Calendar.HOUR);

        int minute = calendar.get(Calendar.MINUTE);
        int am = calendar.get(Calendar.AM_PM);
        if(currentAm==1) currentHour+=12;
        if (am == 0) hour += 12;
        int diffhour = currentHour - hour;
        System.out.println("diff"+currentHour);
        System.out.println("diff"+hour);

        return diffhour;
    }

    private int getDateDifference(Date date) {

        java.util.Date today = new java.util.Date();

        long time = today.getTime() - date.getTime();
        int minutes = (int) time / 60000;
        minutes = (minutes < 0 ? -minutes : minutes);
        return minutes / 60;
    }
}
