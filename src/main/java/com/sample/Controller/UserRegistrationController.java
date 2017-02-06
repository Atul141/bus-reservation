package com.sample.Controller;

import Models.UserDetails;
import ServiceImpl.ConfigDB;
import Services.UserDetailsService;
import Validators.RegistrationFormValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserRegistrationController {

    private UserDetailsService userDetailsService;
    private RegistrationFormValidator validator;
    private ConfigDB configDB;

    public UserRegistrationController() {
        configDB = new ConfigDB();
        userDetailsService = new UserDetailsService(configDB);
        validator = new RegistrationFormValidator();
    }


    @RequestMapping(value = "/Registration", method = RequestMethod.GET)
    public String setupForm(@ModelAttribute("registrationError") String error, Model model) {
        UserDetails userDetails = new UserDetails();
        model.addAttribute("UserDetails", userDetails);
        model.addAttribute("registrationError", error);
        return "register";
    }

    @RequestMapping(value = "/ReRegistration", method = RequestMethod.GET)
    public String setupFormAgain(@ModelAttribute("registrationError") String error, Model model, HttpServletRequest request) {
        UserDetails userDetails;
        HttpSession httpSession = request.getSession();
        userDetails = (UserDetails) httpSession.getAttribute("formDetails");
        model.addAttribute("UserDetails", userDetails);
        model.addAttribute("registrationError", error);
        return "register";
    }

    @RequestMapping(value = "/RegisterUserDetails", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("User") UserDetails userDetails, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String error = validator.validateAllFields(userDetails);
        if (error == null) {
            if (userDetailsService.checkIfUserExists(userDetails.getEmail()) == false) {
                userDetailsService.saveUserDetails(userDetails);
                redirectAttributes.addAttribute("userName", userDetails.getFirstName());
                return "redirect:/success";
            } else {
                error = "Error!!:Email already registered";
            }
        } else {
            error = "Error!!:" + error;
        }
        redirectAttributes.addAttribute("registrationError", error);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("formDetails", userDetails);
        return "redirect:/ReRegistration";

    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(@ModelAttribute("userName") String name, Model model) {
        model.addAttribute("userName", name);
        return "success";
    }

}
