package ControllerTest;

import com.sample.Controller.DefaultController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static junit.framework.Assert.assertEquals;

public class DefaultControllerTest {


    Model model;
    private DefaultController defaultController;

    @Before
    public void setup() {
        model = new ExtendedModelMap();
        defaultController = new DefaultController();
    }

    @Test
    public void shouldReturnIndexForDefaultPage(){
        assertEquals("index",defaultController.homePage());
    }


}
