package com.sample.Controller;


import Models.NumberOfSeats;
import Models.PassengerWrapper;
import Models.Route;
import Services.PassengerService;
import Services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BookingController {

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public String bookTickets(Model model, @ModelAttribute("numberOfSeats") NumberOfSeats numberOfSeats, HttpServletRequest request) {
        PassengerService passengerService = new PassengerService();
        PassengerWrapper passengerWrapper = new PassengerWrapper();
        passengerWrapper.setPassengerList(passengerService.getPassengerList(numberOfSeats.getNumber()));
        RouteService routeService=new RouteService();
        Route route=routeService.getRouteBasedOnId(numberOfSeats.getRoute_id());
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("route",route);
        model.addAttribute("passengerWrapper", passengerWrapper);
        model.addAttribute("route",route);
        return "/booking";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String confirmation(Model model, @ModelAttribute("passengerWrapper") PassengerWrapper passengerWrapper,HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Route route=(Route)httpSession.getAttribute("route");
        model.addAttribute("route",route);
        model.addAttribute("passengerWrapper", passengerWrapper);
        return "/confirmation";
    }
}
