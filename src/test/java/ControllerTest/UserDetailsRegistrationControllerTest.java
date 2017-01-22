package ControllerTest;

import Models.UserDetails;
import com.sample.Controller.UserRegistration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static junit.framework.Assert.assertEquals;

public class UserDetailsRegistrationControllerTest {

    Model model;
    private UserRegistration userRegistration;
    @Before
    public void setup(){
     model=new ExtendedModelMap();
     userRegistration=new UserRegistration();
    }
    @Test
    public void shouldReturnRegisterWhenRegistrationFormIsSubmitted(){
        assertEquals("register",userRegistration.setupForm(model));
    }
    @Test
    public void shouldReturnSuccessWhenRegistrationFormIsSubmittedSuccessfully(){
        assertEquals("redirect:/success",userRegistration.submitForm(new UserDetails()));
    }
}
