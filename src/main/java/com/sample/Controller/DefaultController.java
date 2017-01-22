package com.sample.Controller;

import Models.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("UserDetails",new UserDetails());
        return "index";
    }

}
