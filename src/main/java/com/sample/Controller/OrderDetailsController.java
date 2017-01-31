package com.sample.Controller;

import Models.OrderDetails;
import Models.Passenger;
import Models.PassengerWrapper;
import Models.Route;
import Services.OrderDetailsService;
import Services.PassengerService;
import Services.RouteService;
import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.RasterOp;
import java.sql.Timestamp;

@Controller
public class OrderDetailsController {

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrderDetails(Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        PassengerWrapper passengerWrapper = (PassengerWrapper) httpSession.getAttribute("passengerWrapper");
        Route route = (Route) httpSession.getAttribute("route");
        String email = (String) httpSession.getAttribute("email");

        OrderDetails orderDetails = new OrderDetails();
        OrderDetailsService orderDetailsService = new OrderDetailsService();
        orderDetails.setPrice((Integer) httpSession.getAttribute("price"));
        orderDetails.setRoute_id(route.getId());
        orderDetails.setEmail(email);
        orderDetails.setStatus("Confirm");


        route=orderDetailsService.updateRoute(route,passengerWrapper.getPassengerList().size());

        RouteService routeService =new RouteService();
        routeService.updateRoute(route);
        java.util.Date today = new java.util.Date();
        Timestamp timestamp = new java.sql.Timestamp(today.getTime());
        orderDetails.setTime(timestamp);
        passengerWrapper.setTimestamp(timestamp);


        httpSession.setAttribute("orderDetails", orderDetails);


        return "redirect:/DisplayOrderDetails";
    }

    @RequestMapping(value = "DisplayOrderDetails", method = RequestMethod.GET)
    public String displayOrderDetails(Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        PassengerWrapper passengerWrapper = (PassengerWrapper) httpSession.getAttribute("passengerWrapper");
        OrderDetails orderDetails = (OrderDetails) httpSession.getAttribute("orderDetails");
        Route route = (Route) httpSession.getAttribute("route");

        model.addAttribute("route", route);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("passengerWrapper", passengerWrapper);
        return "DisplayOrderDetails";
    }

}
