package ControllerTest;


import Models.*;
import ServiceImpl.ConfigDB;
import ServiceImplTest.ConfigTest;
import com.sample.Controller.CancelOrderController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class CancelControllerTest {

    private MockMvc mockMvc;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = configTest.getViewInstance();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(cancelOrderController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @InjectMocks
    CancelOrderController cancelOrderController;

    @Test
    public void shouldReturnCancelOrderPage() throws Exception {
        Route route = configTest.getRouteDetails();
        ConfigDB configDB = new ConfigDB();
        OrderDetails orderDetails = configTest.getOrderDetails();
        PassengerWrapper passengerWrapper = configTest.getPassengerWrapper();
        mockMvc.perform(post("/cancelOrder")
                .sessionAttr("cancelRoute", route)
                .sessionAttr("passengerWrapper", passengerWrapper)
                .sessionAttr("configDB", configDB)
                .sessionAttr("cancelOrderDetails", orderDetails))
                .andExpect(view().name("cancelOrder"));
    }

    @Test
    public void shouldRedirectToSearchRoutesIfCancelIsDirectlyAccessesed() throws Exception {
        mockMvc.perform(get("/cancelOrder"))
                .andExpect(view().name("redirect:/searchRoutes"));
    }
}
