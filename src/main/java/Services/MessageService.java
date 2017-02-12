package Services;


import Models.OrderDetails;
import Models.Route;
import ServiceImpl.MessageImpl;

import static ServiceImpl.SyntaxSugar.portEmail;
import static ServiceImpl.SyntaxSugar.portSMS;

public class MessageService {

    private OrderDetails orderDetails;
    private Route route;
    private MessageImpl messageImpl;

    public MessageService(OrderDetails orderDetails, Route route) {
        this.orderDetails = orderDetails;
        this.route = route;
        messageImpl = new MessageImpl();
    }

    public void sendMessage(String phoneNumner, String email) {
        String message = createMessage(orderDetails, route);
        String emailMessage = email + "%" + message;
        String phoneMessage = phoneNumner + "%" + message;
        messageImpl.sendMessage(emailMessage, portEmail);
        messageImpl.sendMessage(phoneMessage, portSMS);
    }

    private String createMessage(OrderDetails orderDetails, Route route) {

        String output = "Your Order with OrderId: " + orderDetails.getId() + " has been confimed. Yor Bus will depart " + route.getSource() + " at " + route.getDepartureTime() + " on "
                + route.getSelectedDate() + " and reach it's destination " + route.getDestination() + " at " + route.getArrivalTime() + " ";
        return output;
    }


}
