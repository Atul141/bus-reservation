package ControllerTest;

import Models.UserDetails;
import com.sample.Controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static junit.framework.Assert.assertEquals;

public class LoginControllerTest {

    Model model;
    private LoginController loginController;

    @Before
    public void setup(){
        model=new ExtendedModelMap();
        loginController=new LoginController();
    }
@Test
    public void shouldReturnLoginForSuccessfullLogin(){
        assertEquals("login", loginController.Login(model));
}

@Test
    public void shouldReturnToHomeAfterSuccessfullValidation(){
        assertEquals("redirect:/Home",loginController.validateLogin(new UserDetails()));
}
}
