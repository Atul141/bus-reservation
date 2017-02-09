package com.sample.Controller;

import Models.*;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class OrderDetailsController {

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrderDetails(Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        PassengerWrapper passengerWrapper = (PassengerWrapper) httpSession.getAttribute("passengerWrapper");
        Route route = (Route) httpSession.getAttribute("route");
        String email = (String) httpSession.getAttribute("email");
        ConfigDB configDB = (ConfigDB) httpSession.getAttribute("configDB");

        OrderDetails orderDetails = new OrderDetails();
        OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);
        orderDetails.setPrice((Integer) httpSession.getAttribute("price"));
        orderDetails.setRoute_id(route.getId());
        orderDetails.setEmail(email);
        orderDetails.setStatus(SyntaxSugar.PENDING);

        AvailableSeatWrapper availableSeatWrapper = (AvailableSeatWrapper) httpSession.getAttribute("availableSeatWrapper");
        SelectedSeatWrapper selectedSeatWrapper = (SelectedSeatWrapper) httpSession.getAttribute("selectedSeatWrapper");

        availableSeatWrapper = orderDetailsService.updateAvailableSeats(availableSeatWrapper, selectedSeatWrapper);
        SeatSelectionService seatSelectionService = new SeatSelectionService(configDB);
        seatSelectionService.updateAvailableSeats(availableSeatWrapper);

        route = orderDetailsService.updateRoute(route, passengerWrapper.getPassengerList().size());

        RouteService routeService = new RouteService(configDB);
        routeService.updateRoute(route);
        java.util.Date today = new java.util.Date();
        Timestamp timestamp = new java.sql.Timestamp(today.getTime());
        orderDetails.setTime(timestamp);
        passengerWrapper.setTimestamp(timestamp);

        long orderId = orderDetailsService.saveOrder(orderDetails);

        PassengerDetailsService passengerDetailsService = new PassengerDetailsService(configDB);
        passengerDetailsService.savePassengerDetails(passengerWrapper, orderId);


        httpSession.setAttribute("orderDetails", orderDetails);


        return "redirect:/payment";
    }


    @RequestMapping(value = "DisplayOrderDetails", method = RequestMethod.GET)
    public String displayOrderDetails(Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        PassengerWrapper passengerWrapper = (PassengerWrapper) httpSession.getAttribute("passengerWrapper");
        OrderDetails orderDetails = (OrderDetails) httpSession.getAttribute("orderDetails");
        Route route = (Route) httpSession.getAttribute("route");
        orderDetails.setStatus(SyntaxSugar.CONFIRM);
        ConfigDB configDB = (ConfigDB) httpSession.getAttribute("configDB");
        OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);
        orderDetailsService.updateOrderDetails(orderDetails);

        MessageService messageService = new MessageService(orderDetails, route);
        messageService.sendMessage();

        model.addAttribute("route", route);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("passengerWrapper", passengerWrapper);
        return "DisplayOrderDetails";
    }

}
