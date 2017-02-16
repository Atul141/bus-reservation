package com.sample.Controller;

import Models.UserDetails;
import ServiceImpl.ConfigDB;
import Services.OTPService;
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

    @RequestMapping(value = "/RegisterUserDetails", method = RequestMethod.GET)
    public String submitForm() {
        return "redirect:/searchRoutes";
    }


    @RequestMapping(value = "/RegisterUserDetails", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("User") UserDetails userDetails, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String error = validator.validateAllFields(userDetails);
        HttpSession httpSession = request.getSession();
        if (error == null) {
            if (userDetailsService.checkIfUserExists(userDetails.getEmail()) == false) {
                httpSession.setAttribute("userDetails", userDetails);
                return "redirect:/verifyOTP";
            } else {
                error = "Error!!:Email already registered";
            }
        } else {
            error = "Error!!:" + error;
        }
        redirectAttributes.addAttribute("registrationError", error);
        httpSession.setAttribute("formDetails", userDetails);
        return "redirect:/ReRegistration";

    }

    @RequestMapping(value = "/verifyOTP", method = RequestMethod.GET)
    public String verify(HttpServletRequest request) {
        OTPService otpService = new OTPService();
        String otp = otpService.generateOTP();
        HttpSession httpSession = request.getSession();
        String phoneNumber = ((UserDetails) httpSession.getAttribute("userDetails")).getPhone();
        otpService.sendOTP(otp, phoneNumber);
        httpSession.setAttribute("otp", otp);
        return "verifyMobile";
    }

    @RequestMapping(value = "/validatePhoneNumber", method = RequestMethod.POST)
    public String validatePhoneNumber(Model model, @ModelAttribute("user") UserDetails userDetails, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        UserDetails userDetailsSaved = (UserDetails) httpSession.getAttribute("userDetails");
        String otpGenerated = (String) httpSession.getAttribute("otp");
        String otp = request.getParameter("otp");
        if (otpGenerated.equals(otp)) {
            userDetailsService.saveUserDetails(userDetailsSaved);
            return "success";
        }
        model.addAttribute("error", "Invalid OTP ");
        return "verifyMobile";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(HttpServletRequest request, Model model) {
        HttpSession httpSession = request.getSession();
        String name = ((UserDetails) httpSession.getAttribute("userDetails")).getFirstName();
        model.addAttribute("userName", name);
        return "success";
    }

}
