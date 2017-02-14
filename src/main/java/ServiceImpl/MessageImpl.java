package ServiceImpl;


import Services.EncryptService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MessageImpl {

    public boolean sendMessage(String message, Socket socket) {
        String key = "asdfqaqwsaerdqsw";
        try {
            Socket client = socket;
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            EncryptService encryptService = new EncryptService();
            message = encryptService.encryptString(message, key);
            out.writeUTF(message);
            client.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
