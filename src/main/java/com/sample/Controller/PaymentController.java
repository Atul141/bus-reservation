package com.sample.Controller;

import Models.Payment;
import Models.PaymentWrapper;
import ServiceImpl.SyntaxSugar;
import Services.PaymentService;
import Services.PaymentWrapperService;
import Validators.PaymentValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PaymentController {

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment(Model model, @ModelAttribute("paymentError") String error, HttpServletRequest request) {
        try {

            HttpSession httpSession = request.getSession();
            String status = (String) httpSession.getAttribute("status");
            if ((status.compareTo(SyntaxSugar.LOGGED_IN)) != 0) {
                return "redirect:/login";
            }

            PaymentWrapper paymentWrapper = new PaymentWrapper();
            PaymentWrapperService paymentWrapperService = new PaymentWrapperService();
            paymentWrapper.setCardType(paymentWrapperService.getCardTypes());
            paymentWrapper.setMonth(paymentWrapperService.getMonthsList());
            paymentWrapper.setYear(paymentWrapperService.getYearsList());

            Payment payment = new Payment();
            model.addAttribute("error", error);
            model.addAttribute("payment", payment);
            model.addAttribute("paymentWrapper", paymentWrapper);
            return "payment";

        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }

    @RequestMapping(value = "/validatePayment", method = RequestMethod.GET)
    public String validatePayment() {
        return "redirect:/searchRoutes";
    }


    @RequestMapping(value = "/validatePayment", method = RequestMethod.POST)
    public String validatePayment(@ModelAttribute("payment") Payment payment, RedirectAttributes redirectAttribute) {
        try {

            PaymentService paymentService = new PaymentService();
            PaymentValidator paymentValidator = new PaymentValidator();
            String error = paymentValidator.validatePayment(payment);

            if (error != null) {
                redirectAttribute.addAttribute("paymentError", error);

                return "redirect:/payment";
            }
            boolean isCrreditCardValid = false;
            try {
                isCrreditCardValid = paymentService.validateCreditCard(payment);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(isCrreditCardValid);
            if (isCrreditCardValid == false) {
                redirectAttribute.addAttribute("paymentError", "Invalid Credit Card Details");

                return "redirect:/payment";
            }


            return "redirect:/DisplayOrderDetails";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "redirect:/searchRoutes";

        }
    }
}
