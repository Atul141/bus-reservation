package ControllerTest;

import Models.UserDetails;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.LoginService;
import com.sample.Controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/atulbk/Desktop/untitled folder 2/tw-bus-reservation/src/main/webApp/WEB-INF/dispatcher-servlet.xml")

public class LoginControllerTest {

    private UserDetails userDetails;
    private MockMvc mockMvc;


    @Before
    public void setup() {
        ConfigTest configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = configTest.getViewInstance();

        userDetails = new UserDetails();
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(loginController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Mock
    LoginService loginService;
    @InjectMocks
    LoginController loginController;

    @Test
    public void shouldReturnLoginForSuccessfullLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(model().attribute("UserDetails", userDetails))
                .andExpect(model().attribute("loginError", ""))
                .andExpect(view().name("login"));

    }

    @Test
    public void shouldReturnToHomeAfterSuccessfullValidation() throws Exception {
        userDetails.setEmail("abcd@gmail.com");
        userDetails.setPassword("pass");

        when(loginService.validateLogin(any(UserDetails.class))).thenReturn(true);
        mockMvc.perform(post("/loginValidation").flashAttr("User", userDetails)
                .sessionAttr("status", SyntaxSugar.LOGGED_IN))
        .andExpect(view().name("redirect:/searchRoutes"));
    }

    @Test
    public void shouldReturnToLoginAfterUnSuccessfullValidation() throws Exception {
        userDetails.setEmail("abcd@gmail.com");
        userDetails.setPassword("pass");

        when(loginService.validateLogin(userDetails)).thenReturn(false);
        mockMvc.perform(post("/loginValidation").flashAttr("User", userDetails))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void shouldRedirectToSearchRoutesIfDirectlyAccessed() throws Exception {
        mockMvc.perform(get("/loginValidation"))
                .andExpect(view().name("redirect:/searchRoutes"));
    }
}
