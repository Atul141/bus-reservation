package com.sample.Controller;

import Models.Payment;
import Models.PaymentWrapper;
import Services.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment(Model model, HttpServletRequest request) {
        PaymentWrapper paymentWrapper = new PaymentWrapper();
        PaymentService paymentService = new PaymentService();
        paymentWrapper.setCardType(paymentService.getCardTypes());
        paymentWrapper.setMonth(paymentService.getMonthsList());
        paymentWrapper.setYear(paymentService.getYearsList());

        Payment payment = new Payment();

        model.addAttribute("payment", payment);
        model.addAttribute("paymentWrapper", paymentWrapper);
        return "payment";

    }

    @RequestMapping(value = "/validatePayment", method = RequestMethod.POST)
    public String validatePayment(@ModelAttribute("payment") Payment payment) {

        return "redirect:/DisplayOrderDetails";
    }

}
