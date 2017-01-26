package com.sample.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookingController {

@RequestMapping(value="/booking", method = RequestMethod.GET)
    public String bookTickets(){
    return "/booking";
}
}
