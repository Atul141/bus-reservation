package Services;


import Models.OrderDetails;
import Models.Route;
import ServiceImpl.MessageImpl;
import ServiceImpl.SyntaxSugar;

import java.io.IOException;
import java.net.Socket;

import static ServiceImpl.SyntaxSugar.portEmail;
import static ServiceImpl.SyntaxSugar.portSMS;

public class MessageService {

    private OrderDetails orderDetails;
    private Route route;
    private MessageImpl messageImpl;

    public MessageService(OrderDetails orderDetails, Route route, MessageImpl messageImpl) {
        this.orderDetails = orderDetails;
        this.route = route;
        this.messageImpl = messageImpl;
    }

    public void sendMessage(String phoneNumner, String email) throws IOException {
        String message = createMessage(orderDetails, route);
        String emailMessage = email + "%" + message;
        String phoneMessage = phoneNumner + "%" + message;
        messageImpl.sendMessage(emailMessage, new Socket("localhost", SyntaxSugar.portEmail));
        messageImpl.sendMessage(phoneMessage, new Socket("localhost", SyntaxSugar.portSMS));
    }

    private String createMessage(OrderDetails orderDetails, Route route) {

        String output = "Your Order with OrderId: " + orderDetails.getId() + " has been confimed. Yor Bus will depart " + route.getSource() + " at " + route.getDepartureTime() + " on "
                + route.getSelectedDate() + " and reach it's destination " + route.getDestination() + " at " + route.getArrivalTime() + " ";
        return output;
    }


}
