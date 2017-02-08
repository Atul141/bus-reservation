package Services;

import Models.Payment;

import java.io.*;
import java.net.Socket;

class PaymentGateway {
    public boolean run(Payment payment) {
        int port = 6066;
        boolean isValid = false;
        String serverName = "localhost";
        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            out.writeUTF("" + payment.getCardNumber() + "-" + payment.getCvvNumber() + "-" + payment.getCardType() + "-" + payment.getMonth() + "-" + payment.getYear() + "-" + payment.getName() + "");
            String isValidString = in.readUTF();
            if (isValidString.equals("true"))
                isValid = true;
            else
                isValid = false;
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isValid;

    }

}
