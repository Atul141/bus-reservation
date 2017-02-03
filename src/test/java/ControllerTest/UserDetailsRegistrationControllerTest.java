package ControllerTest;

import Models.UserDetails;
import com.sample.Controller.UserRegistrationController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;

import static junit.framework.Assert.assertEquals;

public class UserDetailsRegistrationControllerTest {

    Model model;
    private RedirectAttributes redirectAttributes;
    private UserRegistrationController userRegistrationController;
    private UserDetails user;
    @Autowired
    HttpServletRequest request;

    @Before
    public void setup(){
     model=new ExtendedModelMap();
     userRegistrationController =new UserRegistrationController();
     redirectAttributes=new RedirectAttributesModelMap();
     user =new UserDetails();
        user.setFirstName("abc");
        user.setLastName("def");
        user.setEmail("abc@gmail.com");
        user.setPhone("1234567890");
        user.setPassword("pass");
    }
    @Test
    public void shouldReturnRegisterWhenRegistrationFormIsSubmitted(){
        assertEquals("register", userRegistrationController.setupForm("error",model,request));
    }
    @Test
    public void shouldReturnSuccessWhenRegistrationFormIsSubmittedSuccessfully(){
        assertEquals("redirect:/success", userRegistrationController.submitForm(user,redirectAttributes,request));
    }
}
