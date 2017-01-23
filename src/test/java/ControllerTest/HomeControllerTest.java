package ControllerTest;


import com.sample.Controller.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static junit.framework.Assert.assertEquals;

public class HomeControllerTest {
    Model model;
    private HomeController homeController;

    @Before
    public void setup() {
        model = new ExtendedModelMap();
        homeController = new HomeController();
    }

    @Test
    public void shouldReturnToHomePage(){
        assertEquals("home",homeController.successLogin("abcd",model));
    }


}
