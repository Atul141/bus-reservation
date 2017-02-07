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
        HttpSession httpSession = request.getSession();
        ConfigDB configDB = (ConfigDB) httpSession.getAttribute("configDB");
        SeatSelectionService seatSelectionService = new SeatSelectionService(configDB);
        RouteService routeService = new RouteService(configDB);
        CancelBookingService cancelBookingService = new CancelBookingService(configDB);

        Route route = (Route) httpSession.getAttribute("cancelRoute");
        PassengerWrapper passengerWrapper = (PassengerWrapper) httpSession.getAttribute("passengerWrapper");
        route = cancelBookingService.updateRoute(route, passengerWrapper.getPassengerList().size());
        routeService.updateRoute(route);

        TotalSeatSelectionImpl totalSeatSelection = new TotalSeatSelectionImpl(configDB);
        AvailableSeatWrapper totalAvailableSeats = totalSeatSelection.getAvailableSeats(route.getBus_no(), route.getId());
        AvailableSeatWrapper availableSeatWrapper = seatSelectionService.getAvailableSeat(route.getBus_no(), route.getId());
        availableSeatWrapper = cancelBookingService.updateAvailableSeats(passengerWrapper, availableSeatWrapper, totalAvailableSeats);
        seatSelectionService.updateAvailableSeats(availableSeatWrapper);

        OrderDetails orderDetails = (OrderDetails) httpSession.getAttribute("cancelOrderDetails");
        CancellationService cancellationService = new CancellationService();
        int cancellationFee = cancellationService.getRefundAmount(orderDetails, route);
        int refundAmount=orderDetails.getPrice()-cancellationFee;

        PassengerDetailsService passengerDetailsService = new PassengerDetailsService(configDB);
        passengerDetailsService.deletePassengerList(passengerWrapper, orderDetails.getId());

        OrderDetailsService orderDetailsService = new OrderDetailsService(configDB);
        orderDetailsService.deleteOrder(orderDetails);

        model.addAttribute("refundAmount",refundAmount);
        model.addAttribute("cancellationFee", cancellationFee);
        return "cancelOrder";
    }
}
