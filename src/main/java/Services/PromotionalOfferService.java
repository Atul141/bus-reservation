package Services;


import Models.UserDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.MessageImpl;
import ServiceImpl.SyntaxSugar;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class PromotionalOfferService {

    private ConfigDB configDB;
    private MessageImpl messageImpl;
    private UserDetailsService userDetailsService;

    public PromotionalOfferService(ConfigDB configDB,MessageImpl message,UserDetailsService userDetailsService) {
        this.configDB = configDB;
        this.messageImpl=message;
        this.userDetailsService=userDetailsService;
    }

    public void sendPromotionOfferToAllCustomers(String text) throws IOException {
        List<String> phoneNumberList = userDetailsService.getPhoneNumberList();
        for (String phoneNumber : phoneNumberList) {
            String message = phoneNumber + "%" + text;
            messageImpl.sendMessage(message, new Socket(SyntaxSugar.SERVER, SyntaxSugar.portSMS));
        }
    }
}
