package com.sample.Controller;

import Models.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String successLogin(Model model,  HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");

        model.addAttribute("userName", userDetails.getEmail());
        return "home";
    }
}
