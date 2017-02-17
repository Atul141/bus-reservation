package ServiceTest;

import ServiceImpl.ConfigDB;
import ServiceImpl.MessageImpl;
import Services.PromotionalOfferService;
import Services.UserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromotionalOfferServiceTest {

    @Mock
    MessageImpl messageImpl = new MessageImpl();

    @Mock
    UserDetailsService userDetailsService;


    private  PromotionalOfferService promotionalOfferService;

    @Test
    public void shouldCallSendMessage() throws IOException {
        promotionalOfferService=new PromotionalOfferService(new ConfigDB(),messageImpl,userDetailsService);
        List<String> phoneNumber = new ArrayList<String>();
        phoneNumber.add("111111111");
        when(messageImpl.sendMessage(anyString(), any(Socket.class))).thenReturn(true);
        when(userDetailsService.getPhoneNumberList()).thenReturn(phoneNumber);
        promotionalOfferService.sendPromotionOfferToAllCustomers("Hello");
        verify(messageImpl).sendMessage(anyString(), any(Socket.class));

    }
}
