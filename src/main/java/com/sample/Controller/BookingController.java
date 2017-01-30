package com.sample.Controller;


import Models.*;
import Services.NumberOfSeatService;
import Services.PassengerService;
import Services.RouteService;
import Services.SeatSelectionService;
import Validators.PassengerValidators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        SeatSelectionService seatSelectionService = new SeatSelectionService();
        AvailableSeatWrapper availableSeatWrapper = seatSelectionService.getAvailableSeat(route.getBus_no(), route.getId());

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("route", route);
        httpSession.setAttribute("numberOfSeats", numberOfSeats);


        model.addAttribute("availableSeatWrapper", availableSeatWrapper);
        model.addAttribute("passengerWrapper", passengerWrapper);
        model.addAttribute("route", route);
        return "/booking";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String confirmation(Model model, @ModelAttribute("passengerWrapper") PassengerWrapper passengerWrapper, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        SelectedSeatWrapper selectedSeatWrapper = new SelectedSeatWrapper();
        try {

            selectedSeatWrapper.setSelectedSeatWomen(Arrays.asList(request.getParameterValues("selectedSeatWomen")));
            selectedSeatWrapper.setSelectedSeatSeniorCitizen(Arrays.asList(request.getParameterValues("selectedSeatSeniorCitizen")));
            selectedSeatWrapper.setSelectedSeatDisabled(Arrays.asList(request.getParameterValues("selectedSeatDisabled")));
            selectedSeatWrapper.setSelectedSeatGeneral(Arrays.asList(request.getParameterValues("selectedSeatGeneral")));
        } catch (NullPointerException ex) {

        }

        HttpSession httpSession = request.getSession();
        Route route = (Route) httpSession.getAttribute("route");
        httpSession.setAttribute("selectedSeatWrapper", selectedSeatWrapper);
        httpSession.setAttribute("passengerWrapper", passengerWrapper);
        model.addAttribute("route", route);
        model.addAttribute("numberOfSeats");

        PassengerValidators passengerValidators = new PassengerValidators();

        String error = passengerValidators.validatePassengers(passengerWrapper);
        redirectAttributes.addAttribute("error", error);

        if (error != null) {
            return "redirect:/reBooking";

        }
        return "/confirmation";
    }

    @RequestMapping(value = "/reBooking", method = RequestMethod.GET)
    public String reBooking(Model model, HttpServletRequest request, @ModelAttribute("error") String error, HttpServletResponse response) {


        HttpSession httpSession = request.getSession();
        NumberOfSeats numberOfSeats = (NumberOfSeats) httpSession.getAttribute("numberOfSeats");
        PassengerWrapper passengerWrapper = (PassengerWrapper) httpSession.getAttribute("passengerWrapper");
        RouteService routeService = new RouteService();
        Route route = routeService.getRouteBasedOnId(numberOfSeats.getRoute_id());


        SeatSelectionService seatSelectionService = new SeatSelectionService();
        AvailableSeatWrapper availableSeatWrapper = seatSelectionService.getAvailableSeat(route.getBus_no(), route.getId());

        model.addAttribute("error", error);
        model.addAttribute("availableSeatWrapper", availableSeatWrapper);
        model.addAttribute("passengerWrapper", passengerWrapper);
        model.addAttribute("route", route);
        return "booking";
    }
}
