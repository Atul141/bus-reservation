package Services;

import Models.OrderDetails;
import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImpl.MessageImpl;
import ServiceImpl.SyntaxSugar;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.List;


public class SendReminderMessageService {

    private ConfigDB configDB;
    private MessageImpl messageImpl;

    public SendReminderMessageService(ConfigDB configDB) {
        this.configDB = configDB;
        messageImpl = new MessageImpl();
    }

    public void sendReminderMessage() throws IOException {
        RouteService routeService = new RouteService(configDB);
        List<Route> routeList = routeService.getRoutelistBasedOnDate(new Date());
        for (Route route : routeList) {
            if (shouldSendMessage(route)) {
                sendMessageToMobile(route);
            }
        }
    }

    private void sendMessageToMobile(Route route) throws IOException {
        OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);
        List<OrderDetails> orderDetailsList = orderDetailsService.getOrderBasedOnRouteId(route.getId());
        for (OrderDetails orderDetails : orderDetailsList) {
            String phoneNumber = getPhoneNumber(orderDetails.getEmail());
            String message = phoneNumber + "%" + getMessage(route);
            messageImpl.sendMessage(message, new Socket(SyntaxSugar.SERVER, SyntaxSugar.portSMS));
        }
    }

    private String getPhoneNumber(String email) {
        UserDetailsService userDetailsService = new UserDetailsService(configDB);
        return userDetailsService.getPhoneNumber(email);
    }

    private String getMessage(Route route) {
        return "This is to remind you that Your Bus will Depart " + route.getSource() + " at " + route.getDepartureTime();
    }

    private boolean shouldSendMessage(Route route) {
        DateTime routeTime = new DateTime(route.getDate());
        routeTime.plusHours(route.getDepartureTime().getHours());
        routeTime.plusHours(route.getDepartureTime().getMinutes());

        DateTime currentTime = new DateTime();
        int minutes = Minutes.minutesBetween(currentTime, routeTime).getMinutes();
        if (minutes < 0) minutes = -minutes;

        return minutes > 31;
    }
}
