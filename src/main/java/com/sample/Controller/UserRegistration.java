package com.sample.Controller;

import Models.UserDetails;
import Services.UserDetailsService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRegistration {

    private UserDetailsService userDetailsService;
    public UserRegistration(){
        userDetailsService=new UserDetailsService();
    }
    @RequestMapping(value = "/Registration", method = RequestMethod.GET)
    public String setupForm(Model model) {
        UserDetails userDetails = new UserDetails();
        model.addAttribute("UserDetails", userDetails);
        return "register";
    }

    @RequestMapping(value = "/RegisterUserDetails", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("User") UserDetails userDetails) {
        userDetailsService.saveUserDetails(userDetails);
        return "redirect:/success";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "success";
    }

}
