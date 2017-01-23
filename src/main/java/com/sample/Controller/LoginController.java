package com.sample.Controller;

import Dao.UserDetailsDao;
import Models.UserDetails;
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

    private LoginService loginService;
    private LoginValidator loginValidator;

    public LoginController() {
        loginService = new LoginService();
        loginValidator = new LoginValidator();
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
        String loginError = loginValidator.validate(userDetails);
        if (loginError == null) {
            UserDetailsDao loggedInUser = loginService.validateLogin(userDetails);
            if (loggedInUser == null) {
                loginError = "Invalid Credentials";
                redirectAttributes.addAttribute("Error", loginError);
                return "redirect:/login";
            }
            redirectAttributes.addAttribute("userName", loggedInUser.getFirstName());
            return "redirect:/Home";
        }

        redirectAttributes.addAttribute("Error", "ERROR!!:"+loginError);
        return "redirect:/login";

    }
}
