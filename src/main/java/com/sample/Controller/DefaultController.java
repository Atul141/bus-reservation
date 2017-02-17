package com.sample.Controller;

import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import Services.ExecuteAutoCancelService;
import Services.ExecuteReminderMessageService;
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
        ConfigDB configDB = new ConfigDB();
        ExecuteAutoCancelService executeAutoCancelService = new ExecuteAutoCancelService(configDB);
        executeAutoCancelService.start();

        ExecuteReminderMessageService executeReminderMessageService =new ExecuteReminderMessageService(configDB);
        executeReminderMessageService.start();
        return "index";
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String homePageAgain(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("status", SyntaxSugar.LOGGED_OUT);
        httpSession.invalidate();

        return "index";
    }

}
