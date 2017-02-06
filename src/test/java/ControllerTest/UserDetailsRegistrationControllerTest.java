package ControllerTest;

import Models.UserDetails;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import ServiceImplTest.ConfigTest;
import Validators.RegistrationFormValidator;
import com.sample.Controller.UserRegistrationController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class UserDetailsRegistrationControllerTest {

    private MockMvc mockMvc;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/webApp/JSP/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController)
                .setViewResolvers(viewResolver)
                .build();
    }
    @Mock
    RegistrationFormValidator registrationFormValidator;

    @InjectMocks
    UserRegistrationController userRegistrationController;

    @Test
    public void shouldReturnRegistrationPage() throws Exception {
        mockMvc.perform(get("/Registration"))
                .andExpect(view().name("register"));
    }
    @Test
    public void shouldReturnReRegistrationPage() throws Exception {
        mockMvc.perform(get("/ReRegistration"))
                .andExpect(view().name("register"));
    }
    @Test
    public void shouldReturnToSuccessPage() throws Exception {
        mockMvc.perform(get("/success"))
                .andExpect(view().name("success"));
    }
    @Test
    public void shouldReturnToReigistrationIfValidationIsUnSuccessfull() throws Exception {
      UserDetails userDetails=configTest.getUserDetails();
       when(registrationFormValidator.validateAllFields(any(UserDetails.class))).thenReturn("error");
        mockMvc.perform(post("/RegisterUserDetails").flashAttr("User",userDetails))
                .andExpect(view().name("redirect:/ReRegistration"));
    }
    @Test
    public void shouldReturnToSuccesspageIfValidationIsSuccessfull() throws Exception {
      UserDetails userDetails=configTest.getUserDetails();
       when(registrationFormValidator.validateAllFields(any(UserDetails.class))).thenReturn("redirect:/success");
        mockMvc.perform(post("/RegisterUserDetails").flashAttr("User",userDetails))
                .andExpect(view().name("redirect:/ReRegistration"));
    }

}
