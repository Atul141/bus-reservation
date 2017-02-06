package ControllerTest;


import Models.AvailableSeatWrapper;
import Models.PassengerWrapper;
import Models.Route;
import Models.SelectedSeatWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import com.sample.Controller.OrderDetailsController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
        ConfigDB configDB=new ConfigDB();
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
    @Test
    public void shouldReturnToDisplayOrderDetails() throws Exception {
        mockMvc.perform(get("/DisplayOrderDetails").sessionAttr("price", 600)
        ) .andExpect(view().name("DisplayOrderDetails"));
    }

}
