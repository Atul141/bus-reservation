package ControllerTest;

import Models.UserDetails;
import com.sample.Controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static junit.framework.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/atulbk/Desktop/untitled folder 2/tw-bus-reservation/src/main/webApp/WEB-INF/dispatcher-servlet.xml")

public class LoginControllerTest {

    private LoginController loginController;
    private UserDetails userDetails;
    private MockMvc mockMvc;


    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/webApp/JSP/");
        viewResolver.setSuffix(".jsp");

        loginController = new LoginController();
        userDetails = new UserDetails();
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(loginController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void shouldReturnLoginForSuccessfullLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(model().attribute("UserDetails", userDetails))
                .andExpect(model().attribute("loginError", ""))
                .andExpect(view().name("login"));

    }

    @Test
    public void shouldReturnToHomeAfterSuccessfullValidation() {
        userDetails.setEmail("abcd@gmail.com");
        userDetails.setPassword("mysore");
//        assertEquals("redirect:/Home",loginController.validateLogin(userDetails,redirectAttributes,request,response));
    }
}
