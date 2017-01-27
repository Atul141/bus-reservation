package com.sample.Controller;

import Models.NumberOfSeats;
import Models.Route;
import Models.UserDetails;
import Services.NumberOfSeatService;
import Services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String successLogin(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        Route route = new Route();
        Cookie[] cookie = request.getCookies();
        model.addAttribute("userName", cookie[2].getValue());
        RouteService routeService = new RouteService();
        List<Route> routeList;
        routeList = routeService.getRouteList(route);
        model.addAttribute("routesList", routeList);
        model.addAttribute("numberOfSeats", new NumberOfSeats());
        return "home";
    }
}
