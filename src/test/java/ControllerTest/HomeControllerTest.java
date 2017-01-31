package ControllerTest;


import Models.Route;
import com.sample.Controller.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static junit.framework.Assert.assertEquals;

public class HomeControllerTest {
    Model model;
    private HomeController homeController;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedirectAttributes redirectAttributes;

    @Before
    public void setup() {
        model = new ExtendedModelMap();
        homeController = new HomeController();
    }

    @Test
    public void shouldReturnToHomePage(){
        Route route=new Route();
//        assertEquals("home",homeController.successLogin(  Route,model, request,request,"2017-15",redirectAttributes));
    }


}
