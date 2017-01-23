package com.sample.Controller;

import Models.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String successLogin(@ModelAttribute("userName")String userName, Model model) {
        model.addAttribute("userName",userName);
        return "home";
    }
}
