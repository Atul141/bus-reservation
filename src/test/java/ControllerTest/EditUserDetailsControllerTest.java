package ControllerTest;

import Models.UserDetails;
import ServiceImplTest.ConfigTest;
import Services.UserDetailsService;
import Validators.RegistrationFormValidator;
import com.sample.Controller.EditUserDetailsController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/atulbk/Desktop/untitled folder 2/tw-bus-reservation/src/main/webApp/WEB-INF/dispatcher-servlet.xml")

public class EditUserDetailsControllerTest {
    private ConfigTest configTest;
    private MockMvc mockMvc;

    @Before
    public void setup() {

        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = configTest.getViewInstance();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(editUserDetailsController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Mock
    EditUserDetailsController editUserDetailsController;

    @InjectMocks
    EditUserDetailsController editUserDetailsController1;
    @Mock
    UserDetailsService userDetailsService;

    @Mock
    RegistrationFormValidator registrationFormValidator;
    @Autowired
    HttpServletRequest request;

    @Test
    public void shouldReturnViewUserDetails() throws Exception {
        when(editUserDetailsController.getUserDetails(request)).thenReturn(configTest.getUserDetails());
        mockMvc.perform(get("/viewUserDetails"))
                .andExpect(view().name("viewUserDetails"));
    }

    @Test
    public void shouldReturnToViewUserDetailsIfValidationSuccessfull() throws Exception {
        when(editUserDetailsController.getUserDetails(request)).thenReturn(configTest.getUserDetails());
        when(registrationFormValidator.validateAllFields(any(UserDetails.class))).thenReturn(null);
        mockMvc.perform(post("/updateUserDetails").flashAttr("User", configTest.getUserDetails())
                .sessionAttr("id", 1))
                .andExpect(view().name("redirect:/viewUserDetails"));
    }

    @Test
    public void shouldRetrunEditUserDetails() throws Exception {
        when(editUserDetailsController.getUserDetails(request)).thenReturn(configTest.getUserDetails());
        mockMvc.perform(get("/editUserDetails").sessionAttr("error", ""))
                .andExpect(view().name("editUserDetails"));
    }

}
