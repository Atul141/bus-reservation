package ServiceImplTest;


import ServiceImpl.MessageImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageImplTest {

    @Test
    public void shouldConnectSuccessfully() {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MessageImpl message = new MessageImpl();
        Assert.assertTrue("Message sent successfully", message.sendMessage("sample", socket));
    }

}
