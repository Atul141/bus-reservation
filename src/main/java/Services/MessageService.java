package Services;


import Models.OrderDetails;
import Models.Route;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MessageService {

    private OrderDetails orderDetails;
    private Route route;

    public MessageService(OrderDetails orderDetails, Route route) {
        this.orderDetails = orderDetails;
        this.route = route;
    }

    public void sendMessage() {
        String message = createMessage(orderDetails, route);
        int portEmail = 6064;
        int portSMS = 6065;
        send(message, portEmail);
        send(message, portSMS);
    }

    private String createMessage(OrderDetails orderDetails, Route route) {

        String output = "Your Order with OrderId: " + orderDetails.getId() + " has been confimed. Yor Bus will depart " + route.getSource() + " at " + route.getDepartureTime() + " on "
                + route.getSelectedDate() + " and reach it's destination " + route.getDestination() + " at " + route.getArrivalTime() + " ";
        return output;
    }

    public void send(String message, int port) {
        String key = "asdfqaqwsaerdqsw";

        try {
            Socket client = new Socket("localhost", port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            EncryptService encryptService = new EncryptService();
            message = encryptService.encryptString(message, key);
            out.writeUTF(message);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
