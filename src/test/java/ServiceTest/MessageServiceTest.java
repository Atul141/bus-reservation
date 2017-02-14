package ServiceTest;


import ServiceImpl.MessageImpl;
import ServiceImplTest.ConfigTest;
import Services.MessageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.Socket;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MessageServiceTest {

    private ConfigTest configTest;
    private MessageService messageService;

    @Before
    public void setup() {
        initMocks(this);
        configTest = new ConfigTest();
    }

    @Mock
    MessageImpl messageImpl;

    @Test
    public void shouldCallMessageImpl() throws IOException {
        messageService = new MessageService(configTest.getOrderDetails(), configTest.getRouteDetails(), messageImpl);
        when(messageImpl.sendMessage(anyString(), any(Socket.class)));
        messageService.sendMessage("1234567899", "abc@gmail.com");
    }
}
