package ServiceImpl;


import Services.EncryptService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MessageImpl {

    public void sendMessage(String message, int port) {
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
