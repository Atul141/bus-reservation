package Services;


import Models.OrderDetails;
import Models.PassengerWrapper;
import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

import java.sql.Timestamp;
import java.util.List;

public class AutoCancellationService {

    private ConfigDB configDB;
    private CancelBookingService cancelBookingService;

    public AutoCancellationService(ConfigDB configDB) {
        this.configDB = configDB;
        cancelBookingService = new CancelBookingService(configDB);
    }

    public void autoCancelOrder() {

        RouteService routeService = new RouteService(configDB);
        PassengerDetailsService passengerDetailsService = new PassengerDetailsService(configDB);
        OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);

        List<OrderDetails> orderDetailsList = orderDetailsService.getOrderDetailsBasedOnStatus(SyntaxSugar.PENDING);
        for (OrderDetails orderDetails : orderDetailsList) {
            if (shouldDelete(orderDetails)) {
                Route route = routeService.getRouteBasedOnId(orderDetails.getRoute_id());
                PassengerWrapper passengerWrapper = passengerDetailsService.getPassengerDetails(orderDetails.getId());
                cancelBookingService.cancelBooking(route, passengerWrapper, orderDetails);
            }
        }
    }

    private boolean shouldDelete(OrderDetails orderDetails) {
        boolean shouldDelete = false;
        Timestamp timestamp = orderDetails.getTime();
        DateTime start = new DateTime(timestamp);
        DateTime end = new DateTime();
        int minutes = Minutes.minutesBetween(end, start).getMinutes();
        int hours = Hours.hoursBetween(start, end).getHours();
        if (minutes < 0) minutes = -minutes;

        if (minutes > 1)
            shouldDelete = true;
        else if (hours > 0)
            shouldDelete = true;
        return shouldDelete;
    }
}
