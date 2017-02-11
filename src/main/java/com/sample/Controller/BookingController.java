package com.sample.Controller;


import Models.*;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import Services.PassengerService;
import Services.RouteService;
import Services.SeatSelectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BookingController {


    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public String bookTickets(Model model, @ModelAttribute("numberOfSeats") NumberOfSeats numberOfSeats, HttpServletRequest request) {
        try {
            PassengerService passengerService = new PassengerService();
            PassengerWrapper passengerWrapper = new PassengerWrapper();
            HttpSession httpSession = request.getSession();

            passengerWrapper.setAgeList(passengerService.getAgeList());
            passengerWrapper.setGenderList(passengerService.getGenderList());
            passengerWrapper.setPassengerList(passengerService.getPassengerList(numberOfSeats.getNumber()));

            ConfigDB configDB = new ConfigDB();
            RouteService routeService = new RouteService(configDB);
            Route route = routeService.getRouteBasedOnId(numberOfSeats.getRoute_id());

            SeatSelectionService seatSelectionService = new SeatSelectionService(configDB);
            AvailableSeatWrapper availableSeatWrapper = seatSelectionService.getAvailableSeat(route.getBus_no(), route.getId());

            httpSession.setAttribute("route", route);
            httpSession.setAttribute("numberOfSeats", numberOfSeats);
            httpSession.setAttribute("availableSeatWrapper", availableSeatWrapper);

            model.addAttribute("availableSeatWrapper", availableSeatWrapper);
            model.addAttribute("passengerWrapper", passengerWrapper);
            model.addAttribute("route", route);
            return "booking";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }


    @RequestMapping(value = "/reBooking", method = RequestMethod.GET)
    public String reBooking(Model model, HttpServletRequest request, @ModelAttribute("error") String error) {
        try {

            HttpSession httpSession = request.getSession();
            ConfigDB configDB = new ConfigDB();

            String status = (String) httpSession.getAttribute("status");
            if ((status.compareTo(SyntaxSugar.LOGGED_IN)) != 0) {
                return "redirect:/login";
            }
            NumberOfSeats numberOfSeats = (NumberOfSeats) httpSession.getAttribute("numberOfSeats");
            PassengerWrapper passengerWrapper = (PassengerWrapper) httpSession.getAttribute("passengerWrapper");
            RouteService routeService = new RouteService(configDB);
            Route route = routeService.getRouteBasedOnId(numberOfSeats.getRoute_id());


            SeatSelectionService seatSelectionService = new SeatSelectionService(configDB);
            AvailableSeatWrapper availableSeatWrapper = seatSelectionService.getAvailableSeat(route.getBus_no(), route.getId());

            PassengerService passengerService = new PassengerService();
            passengerWrapper.setAgeList(passengerService.getAgeList());
            passengerWrapper.setGenderList(passengerService.getGenderList());

            model.addAttribute("error", error);
            model.addAttribute("availableSeatWrapper", availableSeatWrapper);
            model.addAttribute("passengerWrapper", passengerWrapper);
            model.addAttribute("route", route);
            return "booking";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }


    }
}
