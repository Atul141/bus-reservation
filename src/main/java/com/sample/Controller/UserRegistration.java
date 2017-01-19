package com.sample.Controller;

import Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserRegistration {

    @RequestMapping(value = "/Registration", method = RequestMethod.GET)
    public String setupForm(Model model) {
        User user = new User();
        model.addAttribute("User", user);
        return "register";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("User") User user) {
        System.out.println(user.getFirstName());
        return "redirect:/success";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "success";
    }

}
