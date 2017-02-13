package com.sample.Controller;

import Models.*;
import ServiceImpl.ConfigDB;
import ServiceImpl.TotalSeatSelectionImpl;
import Services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CancelOrderController {

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public String deleteOrder(HttpServletRequest request, Model model) {
        try {
            HttpSession httpSession = request.getSession();

            ConfigDB configDB = new ConfigDB();
            CancelBookingService cancelBookingService = new CancelBookingService(configDB);

            Route route = (Route) httpSession.getAttribute("cancelRoute");
            PassengerWrapper passengerWrapper = (PassengerWrapper) httpSession.getAttribute("passengerWrapper");

            OrderDetails orderDetails = (OrderDetails) httpSession.getAttribute("cancelOrderDetails");
            CancellationService cancellationService = new CancellationService();
            int cancellationFee = cancellationService.getRefundAmount(orderDetails, route);
            int refundAmount = orderDetails.getPrice() - cancellationFee;

            cancelBookingService.cancelBooking(route, passengerWrapper, orderDetails);

            httpSession.removeAttribute("cancelRoute");
            httpSession.removeAttribute("passengerWrapper");
            httpSession.removeAttribute("cancelOrderDetails");

            model.addAttribute("refundAmount", refundAmount);
            model.addAttribute("cancellationFee", cancellationFee);
            return "cancelOrder";
        } catch (Exception ex) {
            System.out.println(ex);
            return "redirect:/searchRoutes";

        }
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
    public String deleteOrder() {
        return "redirect:/searchRoutes";

    }


}
