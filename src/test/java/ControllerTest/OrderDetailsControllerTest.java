package ControllerTest;


import Models.AvailableSeatWrapper;
import Models.PassengerWrapper;
import Models.Route;
import Models.SelectedSeatWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.MessageService;
import Services.UserDetailsService;
import com.sample.Controller.OrderDetailsController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stubVoid;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class OrderDetailsControllerTest {

    private MockMvc mockMvc;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = configTest.getViewInstance();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(orderDetailsController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @InjectMocks
    OrderDetailsController orderDetailsController;

    @Test
    public void shouldReturnToPayment() throws Exception {
        Route route = configTest.getRouteDetails();
        ConfigDB configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        AvailableSeatWrapper availableSeatWrapper = configTest.getAvailableSeatwrapper();
        PassengerWrapper passengerWrapper = configTest.getPassengerWrapper();
        SelectedSeatWrapper selectedSeatWrapper = configTest.getSeatSelectionWrapper();
        mockMvc.perform(post("/saveOrder").sessionAttr("price", 600)
                .sessionAttr("route", route)
                .sessionAttr("passengerWrapper", passengerWrapper)
                .sessionAttr("configDB", configDB)
                .sessionAttr("email", "abc")
                .sessionAttr("availableSeatWrapper", availableSeatWrapper)
                .sessionAttr("selectedSeatWrapper", selectedSeatWrapper))
                .andExpect(view().name("redirect:/payment"));
    }

    @Mock
    UserDetailsService userDetailsService;
    @Mock
    MessageService messageService;

    @Test
    public void shouldReturnToDisplayOrderDetails() throws Exception {
        when(userDetailsService.getPhoneNumber("abc@gmail.com")).thenReturn("1234567890");
        stubVoid(messageService).toReturn().on().sendMessage("1234567890", "abc@gmail.com");
        mockMvc.perform(get("/DisplayOrderDetails")
                .sessionAttr("price", 600)
                .sessionAttr("status", SyntaxSugar.LOGGED_IN)
                .sessionAttr("passengerWrapper", configTest.getPassengerWrapper())
                .sessionAttr("orderDetails", configTest.getOrderDetails())
                .sessionAttr("route", configTest.getRouteDetails())
                .sessionAttr("email", "abc@gmail.com")
        ).andExpect(view().name("DisplayOrderDetails"));
    }

    @Test
    public void shouldRedirectToSearchRoutesIfDirectlyAccessed() throws Exception {
        mockMvc.perform(get("/saveOrder"))
                .andExpect(view().name("redirect:/searchRoutes"));
    }

    @Test
    public void shouldReturnToLoginIfNotLoggedIn() throws Exception {
        mockMvc.perform(get("/DisplayOrderDetails")
                .sessionAttr("status", SyntaxSugar.LOGGED_OUT))
                .andExpect(view().name("redirect:/login"));
    }
}
