package Services;

import Models.Payment;

import java.io.*;
import java.net.Socket;

public class PaymentGateway {

    private Socket socket;

    public PaymentGateway(Socket socket) {
        this.socket = socket;
    }

    public boolean ValidateCredentials(Payment payment) {
        int port = 6066;
        boolean isValid = false;
        String serverName = "localhost";
        String key = "asdfqaqwsaerdqsw";

        try {
            Socket client = socket;
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            String output = "" + payment.getCardNumber() + "-" + payment.getCvvNumber() + "-" + payment.getCardType() + "-" + payment.getMonth() + "-" + payment.getYear() + "-" + payment.getName() + "";
            EncryptService encryptService = new EncryptService();
            output = encryptService.encryptString(output, key);
            out.writeUTF(output);
            String isValidString = in.readUTF();
            isValidString = encryptService.decryptString(isValidString, key);
            isValid = isValidString.equals("true");
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isValid;

    }



}
