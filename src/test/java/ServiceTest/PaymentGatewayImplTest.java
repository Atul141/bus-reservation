package ServiceTest;


import Models.Payment;
import ServiceImplTest.ConfigTest;
import ServiceImpl.PaymentGatewayImpl;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentGatewayImplTest {

    private PaymentGatewayImpl paymentGatewayImpl;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        try {
            paymentGatewayImpl = new PaymentGatewayImpl(configTest.getSocket("localhost",6066));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shouldSendMessageToServer() {

        String encoding = "UTF-8";
        String response = "false";

        try {
            Socket socket = mock(Socket.class);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);

            InputStream inputStream = new ByteArrayInputStream(response.getBytes(encoding));
            when(socket.getInputStream()).thenReturn(inputStream);

            PaymentGatewayImpl paymentGatewayImpl = new PaymentGatewayImpl(socket);
            assertEquals(paymentGatewayImpl.ValidateCredentials(configTest.getPayment()), false); // message sent and got a response

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldValidateCredentials() {
        Payment payment = configTest.getPayment();
        assertTrue(paymentGatewayImpl.ValidateCredentials(payment));
    }

}
