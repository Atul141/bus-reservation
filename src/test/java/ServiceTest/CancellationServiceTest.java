package ServiceTest;


import Models.OrderDetails;
import Models.Route;
import ServiceImplTest.ConfigTest;
import Services.CancellationService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CancellationServiceTest {

    private CancellationService cancellationService;
    private OrderDetails orderDetails;
    private ConfigTest configTest;
    private Route route;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        route = configTest.getRouteDetails();
        cancellationService = new CancellationService();

    }

    @Test
    public void shouldReturnRefundAmountIfCancelledBeforeOneDay() {
        setRouteDate(30);
        orderDetails = configTest.getOrderDetails();
        assertEquals(300, cancellationService.getRefundAmount(orderDetails, route));
    }

    @Test
    public void shouldReturnNotRefundAmountIfCancelledOneDayBefore() {
        setRouteDate(3);
        orderDetails = configTest.getOrderDetails();
        assertEquals(0, cancellationService.getRefundAmount(orderDetails, route));
    }

    @Test
    public void shouldReturnNRefundAmountIfCancelledTenDaysBefore() {
        setRouteDate(242);
        orderDetails = configTest.getOrderDetails();
        assertEquals(480, cancellationService.getRefundAmount(orderDetails, route));
    }

    private void setRouteDate(int hours) {
        DateTime start = new DateTime();
        start = start.plusHours(hours);
        int year = start.getYear();
        int month = start.getMonthOfYear();
        int day = start.getDayOfMonth();
        String routeDate = year + "-" + month + "-" + day;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = null;
        try {
            dates = dateFormat.parse(routeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int hour=start.getHourOfDay();
        int minutes=start.getMinuteOfHour();
        route.setDate(dates);
        route.setDepartureTime(new Time(hour,minutes,00));
    }
}
