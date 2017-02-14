package ControllerTest;


import Models.PassengerWrapper;
import Models.Route;
import Models.SelectedSeatWrapper;
import ServiceImplTest.ConfigTest;
import Validators.PassengerValidators;
import Validators.PaymentValidator;
import com.sample.Controller.ConfirmationController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class ConfirmationControllerTest {

    private MockMvc mockMvc;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = configTest.getViewInstance();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(confirmationController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @InjectMocks
    ConfirmationController confirmationController = new ConfirmationController();

    @Mock
    PassengerValidators passengerValidators;

    @Test
    public void shouldReturnBookingPageIfPassengerDetailsValidationIsUnSuccessfull() throws Exception {
        PassengerWrapper passengerWrapper = configTest.getPassengerWrapper();
        Route route = configTest.getRouteDetails();
        when(passengerValidators.validatePassengers(any(PassengerWrapper.class))).thenReturn("error");
        mockMvc.perform(post("/confirmation").flashAttr("passengerWrapper", passengerWrapper).sessionAttr("route", route))
                .andExpect(view().name("redirect:/reBooking"));
    }

    @Test
    public void shouldReturnBookingPageIfSeatAllocationValidationIsUnSuccessfull() throws Exception {
        PassengerWrapper passengerWrapper = configTest.getPassengerWrapper();
        Route route = configTest.getRouteDetails();
        when(passengerValidators.validatePassengers(any(PassengerWrapper.class))).thenReturn(null);
        when(passengerValidators.validateSelectedSeatsWithPassengers(any(PassengerWrapper.class), any(SelectedSeatWrapper.class))).thenReturn("error");
        mockMvc.perform(post("/confirmation").flashAttr("passengerWrapper", passengerWrapper).sessionAttr("route", route))
                .andExpect(view().name("redirect:/reBooking"));
    }

    @Test
    public void shouldReturnConfirmationPageIfSeatAllocationValidationIsSuccessfull() throws Exception {
        PassengerWrapper passengerWrapper = configTest.getPassengerWrapper();
        Route route = configTest.getRouteDetails();
        when(passengerValidators.validatePassengers(any(PassengerWrapper.class))).thenReturn(null);
        when(passengerValidators.validateSelectedSeatsWithPassengers(any(PassengerWrapper.class), any(SelectedSeatWrapper.class))).thenReturn(null);
        mockMvc.perform(post("/confirmation").flashAttr("passengerWrapper", passengerWrapper).sessionAttr("route", route))
                .andExpect(view().name("/confirmation"));
    }

    @Test
    public void shouldRedirectToSearchRoutesIfConfirmationIsDirectlyAccessed() throws Exception {
        mockMvc.perform(get("/confirmation"))
                .andExpect(view().name("redirect:/searchRoutes"));
    }
}
