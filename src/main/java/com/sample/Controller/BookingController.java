package com.sample.Controller;


import Models.NumberOfSeats;
import Models.Passenger;
import Models.PassengerWrapper;
import Services.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BookingController {

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public String bookTickets(Model model,@ModelAttribute("numberOfSeats") NumberOfSeats numberOfSeats) {
        PassengerService passengerService = new PassengerService();
        PassengerWrapper passengerWrapper = new PassengerWrapper();
        passengerWrapper.setPassengerList(passengerService.getPassengerList(numberOfSeats.getNumber()));
        model.addAttribute("passengerWrapper", passengerWrapper);
        System.out.println(numberOfSeats.getNumber());
        System.out.println(numberOfSeats.getRoute_id());

        return "/booking";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String confirmation(Model model, @ModelAttribute("passengerWrapper") PassengerWrapper passengerWrapper, @ModelAttribute("numberOfSeats") NumberOfSeats numberOfSeats) {
//        model.addAttribute("numberOfSeats", numberOfSeats);
        model.addAttribute("passengerWrapper", passengerWrapper);
        System.out.println(numberOfSeats.getNumber());
        return "/confirmation";
    }
}
