package Services;


import Models.OrderDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import java.sql.Timestamp;
import java.util.List;

public class AutoCancellationService {

    private ConfigDB configDB;

    public AutoCancellationService(ConfigDB configDB) {
        this.configDB = configDB;
    }

    public void autoCancelOrder() {
        OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);
        List<OrderDetails> orderDetailsList = orderDetailsService.getOrderDetailsBasedOnStatus(SyntaxSugar.PENDING);
        for (OrderDetails orderDetails : orderDetailsList) {
            if (shouldDelete(orderDetails)) {
                orderDetails.setStatus(SyntaxSugar.CANCEL);
                orderDetailsService.updateOrderDetails(orderDetails);
            }
        }
    }
    private boolean shouldDelete(OrderDetails orderDetails) {
        boolean shouldDelete = false;
        Timestamp timestamp = orderDetails.getTime();
        DateTime start = new DateTime(timestamp);
        DateTime end = new DateTime();
        int minutes = Minutes.minutesBetween(end,start).getMinutes();
        int hours = Hours.hoursBetween(start, end).getHours();
        if (minutes > 30)
            shouldDelete = true;
        else if (hours > 0)
            shouldDelete = true;
        return shouldDelete;
    }
}
