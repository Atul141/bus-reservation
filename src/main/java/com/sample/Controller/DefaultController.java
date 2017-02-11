package com.sample.Controller;

import ServiceImpl.SyntaxSugar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("status", SyntaxSugar.LOGGED_OUT);
        return "index";
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String homePageAgain(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        HttpSession httpSession1 = request.getSession(true);
        httpSession1.setAttribute("status", SyntaxSugar.LOGGED_OUT);

        return "index";
    }

}
