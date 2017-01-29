package com.sample.Controller;


import Models.*;
import Services.PassengerService;
import Services.RouteService;
import Services.SeatSelectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class BookingController {

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public String bookTickets(Model model, @ModelAttribute("numberOfSeats") NumberOfSeats numberOfSeats, HttpServletRequest request) {
        PassengerService passengerService = new PassengerService();
        PassengerWrapper passengerWrapper = new PassengerWrapper();
        passengerWrapper.setPassengerList(passengerService.getPassengerList(numberOfSeats.getNumber()));

        RouteService routeService = new RouteService();
        Route route = routeService.getRouteBasedOnId(numberOfSeats.getRoute_id());

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("route", route);

        SeatSelectionService seatSelectionService = new SeatSelectionService();
        AvailableSeatWrapper availableSeatWrapper = seatSelectionService.getAvailableSeat(route.getBus_no(), route.getId());

        model.addAttribute("availableSeatWrapper", availableSeatWrapper);
        model.addAttribute("passengerWrapper", passengerWrapper);
        model.addAttribute("route", route);
        return "/booking";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String confirmation(Model model, @ModelAttribute("passengerWrapper") PassengerWrapper passengerWrapper, HttpServletRequest request) {
        try {

            SelectedSeatWrapper selectedSeatWrapper = new SelectedSeatWrapper();
            selectedSeatWrapper.setSelectedSeatWomen(Arrays.asList(request.getParameterValues("selectedSeatWomen")));
            selectedSeatWrapper.setSelectedSeatSeniorCitizen(Arrays.asList(request.getParameterValues("selectedSeatSeniorCitizen")));
            selectedSeatWrapper.setSelectedSeatDisabled(Arrays.asList(request.getParameterValues("selectedSeatDisabled")));
            selectedSeatWrapper.setSelectedSeatGeneral(Arrays.asList(request.getParameterValues("selectedSeatGeneral")));
        }catch (NullPointerException ex){

        }
        HttpSession httpSession = request.getSession();
        Route route = (Route) httpSession.getAttribute("route");
        model.addAttribute("route", route);
        model.addAttribute("passengerWrapper", passengerWrapper);
        return "/confirmation";
    }
}
