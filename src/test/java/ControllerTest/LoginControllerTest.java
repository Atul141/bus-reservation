package ControllerTest;

import Models.UserDetails;
import com.sample.Controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static junit.framework.Assert.assertEquals;

public class LoginControllerTest {

    Model model;
    RedirectAttributes redirectAttributes;
    private LoginController loginController;
    private UserDetails userDetails;

    @Before
    public void setup(){
        model=new ExtendedModelMap();
        redirectAttributes= new RedirectAttributesModelMap();
        loginController=new LoginController();
        userDetails=new UserDetails();
    }
@Test
    public void shouldReturnLoginForSuccessfullLogin(){
        assertEquals("login", loginController.Login("error",model));
}

@Test
    public void shouldReturnToHomeAfterSuccessfullValidation(){
        userDetails.setEmail("abcd@gmail.com");
        userDetails.setPassword("mysore");
        assertEquals("redirect:/Home",loginController.validateLogin(userDetails,redirectAttributes));
}
}
