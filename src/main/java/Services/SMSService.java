package Services;


import Models.OrderDetails;

import java.io.*;
import java.net.Socket;

public class SMSService {


    public void sendSMS(OrderDetails orderDetails) {
        String key = "asdfqaqwsaerdqsw";

        try {
            Socket client = new Socket("localhost", 6065);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            String output = createMessage(orderDetails);

            EncryptService encryptService = new EncryptService();
            output = encryptService.encryptString(output, key);
            out.writeUTF(output);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String createMessage(OrderDetails orderDetails) {

        String output = "your Order with OrderId: " + orderDetails.getId() + " has been confimed";
        return output;
    }
}

