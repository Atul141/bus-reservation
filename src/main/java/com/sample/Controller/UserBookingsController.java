package com.sample.Controller;


import Models.OrderDetails;
import Models.OrderWrapper;
import Models.PassengerWrapper;
import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import Services.*;
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
        try {
            HttpSession httpSession = request.getSession();
            String status = (String) httpSession.getAttribute("status");
            if ((status.compareTo(SyntaxSugar.LOGGED_IN) != 0)) {
                return "redirect:/login";
            }

            ConfigDB configDB = new ConfigDB();
            httpSession.setAttribute("configDB", configDB);
            String email = (String) httpSession.getAttribute("email");

            AutoCancellationService autoCancellationService = new AutoCancellationService(configDB);
            autoCancellationService.autoCancelOrder();

            UserBookingsService userBookingsService = new UserBookingsService(configDB);
            List<OrderDetails> orderDetailsList = userBookingsService.getOrderDetailsList(email);

            OrderWrapper orderWrapper = new OrderWrapper();
            model.addAttribute("orderWrapper", orderWrapper);
            model.addAttribute("orderDetailsList", orderDetailsList);
            return "UserBookings";

        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }

    @RequestMapping(name = "/savedOrderDetails", method = RequestMethod.POST)
    public String displaySavedOrderDetails(@ModelAttribute("orderWrapper") OrderWrapper orderWrapper, Model model, HttpServletRequest request) {
        try {
            HttpSession httpSession = request.getSession();

            ConfigDB configDB = new ConfigDB();
            RouteService routeService = new RouteService(configDB);
            OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);
            OrderDetails orderDetails = orderDetailsService.getOrderBasedOnId(orderWrapper.getId());
            Route route = routeService.getRouteBasedOnId(orderDetails.getRoute_id());


            PassengerDetailsService passengerDetailsService = new PassengerDetailsService(configDB);
            PassengerWrapper passengerWrapper = passengerDetailsService.getPassengerDetails(orderWrapper.getId());

            httpSession.setAttribute("cancelRoute", route);
            httpSession.setAttribute("passengerWrapper", passengerWrapper);
            httpSession.setAttribute("cancelOrderDetails", orderDetails);


            model.addAttribute("number", orderWrapper.getId());
            model.addAttribute("orderDetails", orderDetails);
            model.addAttribute("route", route);
            model.addAttribute("passengerWrapper", passengerWrapper);
            return "/savedOrderDetails";

        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }

}
