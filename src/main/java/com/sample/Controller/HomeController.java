package com.sample.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(ModelMap model){
        return "index";
    }

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String loginUser(ModelMap model) {
        model.addAttribute("message", "Sample Spring MVC Framework");
        return "login";
    }
    @RequestMapping(value="/Register",method = RequestMethod.GET)
    public String registerNewUser(ModelMap model) {
        model.addAttribute("message", "Sample Spring MVC Framework");
        return "register";
    }
}
