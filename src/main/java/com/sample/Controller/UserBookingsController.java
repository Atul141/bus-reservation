package com.sample.Controller;


import Models.OrderDetails;
import ServiceImpl.ConfigDB;
import Services.UserBookingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserBookingsController {

    @RequestMapping(name = "/UserBookings", method = RequestMethod.GET)
    public String displayUserBookings(Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        ConfigDB configDB = (ConfigDB) httpSession.getAttribute("configDB");
        String email = (String) httpSession.getAttribute("email");

        UserBookingsService userBookingsService = new UserBookingsService(configDB);
        List<OrderDetails> orderDetailsList=userBookingsService.getOrderDetailsList(email);

        model.addAttribute("orderDetailsList",orderDetailsList);
        return "UserBookings";

    }

}
