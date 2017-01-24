package ControllerTest;


import com.sample.Controller.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static junit.framework.Assert.assertEquals;

public class HomeControllerTest {
    Model model;
    private HomeController homeController;
    @Autowired
    private HttpServletRequest request;

    @Before
    public void setup() {
        model = new ExtendedModelMap();
        homeController = new HomeController();
    }

    @Test
    public void shouldReturnToHomePage(){
//        assertEquals("home",homeController.successLogin( model, request));
    }


}
