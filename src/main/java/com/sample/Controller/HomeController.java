package com.sample.Controller;

import Models.NumberOfSeats;
import Models.Route;
import Models.UserDetails;
import Services.NumberOfSeatService;
import Services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @RequestMapping(value = "/Home", method = RequestMethod.POST)
    public String successLogin(@ModelAttribute("roue")Route route, Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        Cookie[] cookie = request.getCookies();

        System.out.println(route.getSource());
        System.out.println(route.getDestination());
        RouteService routeService = new RouteService();
        List<Route> routeList = routeService.getRouteList(route);

        model.addAttribute("userName", cookie[2].getValue());
        model.addAttribute("routesList", routeList);
        model.addAttribute("numberOfSeats", new NumberOfSeats());
        return "home";
    }

    @RequestMapping(value = "/searchRoutes",method = RequestMethod.GET)
    public String searchRoutes(Model model,HttpServletRequest request){
        Route route=new Route();
        Cookie[] cookie = request.getCookies();
        model.addAttribute("route",route);
        model.addAttribute("userName", cookie[2].getValue());
        return "searchRoutes";

    }
}
