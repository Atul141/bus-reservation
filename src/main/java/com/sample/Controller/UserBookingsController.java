package com.sample.Controller;


import Models.OrderDetails;
import Models.OrderWrapper;
import Models.PassengerWrapper;
import Models.Route;
import ServiceImpl.ConfigDB;
import Services.OrderDetailsService;
import Services.PassengerDetailsService;
import Services.RouteService;
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
        List<OrderDetails> orderDetailsList = userBookingsService.getOrderDetailsList(email);

        OrderWrapper orderWrapper = new OrderWrapper();
        model.addAttribute("orderWrapper", orderWrapper);
        model.addAttribute("orderDetailsList", orderDetailsList);
        return "UserBookings";

    }

    @RequestMapping(name = "/savedOrderDetails", method = RequestMethod.POST)
    public String displaySavedOrderDetails(@ModelAttribute("orderWrapper") OrderWrapper orderWrapper, Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        ConfigDB configDB = (ConfigDB) httpSession.getAttribute("configDB");

        RouteService routeService = new RouteService(configDB);
        OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);
        OrderDetails orderDetails = orderDetailsService.getOrderBasedOnId(orderWrapper.getId());
        Route route = routeService.getRouteBasedOnId(orderDetails.getRoute_id());


        PassengerDetailsService passengerDetailsService = new PassengerDetailsService(configDB);
        PassengerWrapper passengerWrapper = passengerDetailsService.getPassengerDetails(orderWrapper.getId());

        httpSession.setAttribute("cancelRoute",route);
        httpSession.setAttribute("passengerWrapper",passengerWrapper);
        httpSession.setAttribute("cancelOrderDetails",orderDetails);


        model.addAttribute("number", orderWrapper.getId());
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("route", route);
        model.addAttribute("passengerWrapper", passengerWrapper);
        return "/savedOrderDetails";

    }

}
