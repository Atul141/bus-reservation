package com.sample.Controller;

import Models.PassengerWrapper;
import Models.Route;
import Models.SelectedSeatWrapper;
import ServiceImpl.ConfigDB;
import Services.PassengerService;
import Services.PriceCalculation;
import Validators.PassengerValidators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class ConfirmationController {


    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String confirmation(Model model, @ModelAttribute("passengerWrapper") PassengerWrapper passengerWrapper, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            SelectedSeatWrapper selectedSeatWrapper = getSelectedSeatWrapper(request);
            HttpSession httpSession = request.getSession();

            Route route = (Route) httpSession.getAttribute("route");

            httpSession.setAttribute("selectedSeatWrapper", selectedSeatWrapper);
            httpSession.setAttribute("passengerWrapper", passengerWrapper);

            model.addAttribute("route", route);
            model.addAttribute("numberOfSeats");

            PassengerValidators passengerValidators = new PassengerValidators();
            String error = passengerValidators.validatePassengers(passengerWrapper);

            if (error != null) {
                redirectAttributes.addAttribute("error", error);

                return "redirect:/reBooking";
            }
            error = passengerValidators.validateSelectedSeatsWithPassengers(passengerWrapper, selectedSeatWrapper);
            if (error != null) {
                redirectAttributes.addAttribute("error", error);
                return "redirect:/reBooking";
            }
            PassengerService passengerService = new PassengerService();
            passengerWrapper = passengerService.allocateSeats(passengerWrapper, selectedSeatWrapper);
            PriceCalculation priceCalculation = new PriceCalculation();
            int price = priceCalculation.calculateprice(route.getPrice(), passengerWrapper.getPassengerList().size());

            httpSession.setAttribute("passengerWrapper", passengerWrapper);
            httpSession.setAttribute("price", price);
            httpSession.setAttribute("selectedSeatWrapper", selectedSeatWrapper);
            model.addAttribute("price", new Integer(price));
            model.addAttribute("passengerWrapper", passengerWrapper);

            return "/confirmation";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }

    public SelectedSeatWrapper getSelectedSeatWrapper(HttpServletRequest request) {
        SelectedSeatWrapper selectedSeatWrapper = new SelectedSeatWrapper();
        try {
            selectedSeatWrapper.setSelectedSeatWomen(Arrays.asList(request.getParameterValues("selectedSeatWomen")));
        } catch (NullPointerException ex) {
        }
        try {
            selectedSeatWrapper.setSelectedSeatSeniorCitizen(Arrays.asList(request.getParameterValues("selectedSeatSeniorCitizen")));
        } catch (NullPointerException ex) {

        }
        try {
            selectedSeatWrapper.setSelectedSeatDisabled(Arrays.asList(request.getParameterValues("selectedSeatDisabled")));
        } catch (NullPointerException ex) {
        }
        try {
            selectedSeatWrapper.setSelectedSeatGeneral(Arrays.asList(request.getParameterValues("selectedSeatGeneral")));
        } catch (NullPointerException ex) {

        }
        return selectedSeatWrapper;
    }
}
