package com.sample.Controller;

import Models.UserDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import Services.UserDetailsService;
import Validators.RegistrationFormValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EditUserDetailsController {


    @RequestMapping(value = "/viewUserDetails", method = RequestMethod.GET)
    public String viewUserDetails(Model model, HttpServletRequest request) {
        try {
            UserDetails userDetails = getUserDetails(request);

            HttpSession httpSession = request.getSession();
            String status = (String) httpSession.getAttribute("status");
            if ((status.compareTo(SyntaxSugar.LOGGED_IN)) != 0) {
                return "redirect:/login";
            }
            httpSession.setAttribute("error", "");
            httpSession.setAttribute("id", userDetails.getId());

            model.addAttribute("userDetails", userDetails);
            return "viewUserDetails";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }

    @RequestMapping(value = "/editUserDetails", method = RequestMethod.GET)
    public String editUserDetails(Model model, HttpServletRequest request) {
        try {
            UserDetails userDetails = getUserDetails(request);
            HttpSession httpSession = request.getSession();
            String status = (String) httpSession.getAttribute("status");
            if ((status.compareTo(SyntaxSugar.LOGGED_IN)) != 0) {
                return "redirect:/login";
            }
            String error = (String) httpSession.getAttribute("error");

            model.addAttribute("error", error);
            model.addAttribute("userDetails", userDetails);

            return "editUserDetails";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }

    @RequestMapping(value = "/updateUserDetails", method = RequestMethod.POST)
    public String updateUserDetails(@ModelAttribute("User") UserDetails userDetails, Model model, HttpServletRequest request) {
        try {

            HttpSession httpSession = request.getSession();

            RegistrationFormValidator validator = new RegistrationFormValidator();
            String error = validator.validateAllFields(userDetails);
            UserDetailsService userDetailsService = new UserDetailsService(new ConfigDB());
            if (error == null) {
                userDetails.setId((Integer) httpSession.getAttribute("id"));
                userDetailsService.updateUserDetails(userDetails);
                return "redirect:/viewUserDetails";
            }
            httpSession.setAttribute("error", error);
            return "redirect:/editUserDetails";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }


    public UserDetails getUserDetails(HttpServletRequest request) {
        UserDetailsService userDetailsService = new UserDetailsService(new ConfigDB());
        HttpSession httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("email");
        return userDetailsService.getUserDetails(email);
    }
}
