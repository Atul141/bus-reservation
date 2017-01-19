package com.sample.Controller;

import Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(Model model) {
        User user = new User();
        model.addAttribute("User", user);
        return "login";
    }

    @RequestMapping(value = "/loginValidation", method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("User") User user, BindingResult result) {
        System.out.println(user.getFirstName());
        return "redirect:/Home";
    }

    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String successLogin() {
        return "home";
    }
}
