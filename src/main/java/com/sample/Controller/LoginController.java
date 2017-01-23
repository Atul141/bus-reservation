package com.sample.Controller;

import Dao.UserDetailsDao;
import Models.UserDetails;
import Services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private LoginService loginService;

    public LoginController() {
        loginService = new LoginService();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(@ModelAttribute("Error")String error, Model model) {
        UserDetails userDetails = new UserDetails();
        model.addAttribute("UserDetails", userDetails);
        model.addAttribute("loginError",error);
        return "login";
    }

    @RequestMapping(value = "/loginValidation", method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("User") UserDetails userDetails, RedirectAttributes redirectAttributes) {
        UserDetailsDao loggedInUser = loginService.validateLogin(userDetails);
        if (loggedInUser == null) {
            String loginError="Invalid Credentials";
            redirectAttributes.addAttribute("Error",loginError);
            return "redirect:/login";
        }
        redirectAttributes.addAttribute("userName",loggedInUser.getFirstName());
        return "redirect:/Home";
    }


}
