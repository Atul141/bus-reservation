package com.sample.Controller;

import Dao.UserDetailsDao;
import Models.UserDetails;
import Services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private LoginService loginService;

    public LoginController() {
        loginService = new LoginService();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(Model model) {
        UserDetails userDetails = new UserDetails();
        model.addAttribute("UserDetails", userDetails);
        return "login";
    }

    @RequestMapping(value = "/loginValidation", method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("User") UserDetails userDetails) {
        System.out.println("UserDetails" + userDetails.getPassword());
        UserDetailsDao loggedInUser = loginService.validateLogin(userDetails);
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        System.out.println("Password"+loggedInUser.getPassword());
        return "redirect:/Home";
    }


}
