package com.sample.Controller;

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

    public LoginController(){
    loginService=new LoginService();
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(Model model) {
        UserDetails userDetails = new UserDetails();
        model.addAttribute("UserDetails", userDetails);
        return "login";
    }

    @RequestMapping(value = "/loginValidation", method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("User") UserDetails userDetails) {
        System.out.println("UserDetails"+ userDetails.getPassword());
        boolean isValideCredentials=loginService.validateLogin(userDetails);
       if(isValideCredentials)
           System.out.println("true");
       else            System.out.println("false");

        return "redirect:/Home";
    }

    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String successLogin() {
        return "home";
    }
}
