package com.sample.Controller;


import javax.servlet.http.*;

import Models.UserDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import Services.LoginService;
import Validators.LoginValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(@ModelAttribute("Error") String error, Model model) {
        UserDetails userDetails = new UserDetails();
        model.addAttribute("UserDetails", userDetails);
        model.addAttribute("loginError", error);
        return "login";
    }

    @RequestMapping(value = "/loginValidation", method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("User") UserDetails userDetails, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginValidator loginValidator = new LoginValidator();
            String loginError = loginValidator.validate(userDetails);
            ConfigDB configDB = new ConfigDB();
            LoginService loginService = new LoginService(configDB);
            if (loginError == null) {
                boolean isUserLoggedIn = loginService.validateLogin(userDetails);
                if (isUserLoggedIn == false) {
                    loginError = "Invalid Credentials";
                    redirectAttributes.addAttribute("Error", loginError);
                    return "redirect:/login";
                }
                userDetails.setEmail(userDetails.getEmail());
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("email", userDetails.getEmail());

                httpSession.setAttribute("status", SyntaxSugar.LOGGED_IN);

                redirectAttributes.addAttribute("userName", userDetails.getEmail());
                Cookie cookie = new Cookie("userEmail", userDetails.getEmail());
                response.addCookie(cookie);

                return "redirect:/searchRoutes";
            }

            redirectAttributes.addAttribute("Error", "ERROR!!:" + loginError);
            return "redirect:/login";

        } catch (Exception ex) {
            ex.printStackTrace();
            return "errorDisplay";
        }
    }
}
