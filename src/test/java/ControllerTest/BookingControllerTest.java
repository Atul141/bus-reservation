package ControllerTest;


import Models.NumberOfSeats;
import Models.PassengerWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.RouteService;
import Services.SeatSelectionService;
import com.sample.Controller.BookingController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/atulbk/Desktop/untitled folder 2/tw-bus-reservation/src/main/webApp/WEB-INF/dispatcher-servlet.xml")

public class BookingControllerTest {
    private MockMvc mockMvc;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = configTest.getViewInstance();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(bookingController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @InjectMocks
    BookingController bookingController;
    @Mock
    RouteService routeService;
    @Mock
    SeatSelectionService seatSelectionService;

    @Test
    public void shouldBookTickets() throws Exception {
        NumberOfSeats numberOfSeats = configTest.getNumberOfSeat();
        ConfigDB configDB = new ConfigDB();
        when(routeService.getRouteBasedOnId(1)).thenReturn(configTest.getRouteDetails());
        when(seatSelectionService.getAvailableSeat("KA-01 G-2020", 1)).thenReturn(configTest.getAvailableSeatwrapper());
        mockMvc.perform(post("/booking").flashAttr("numberOfSeats", numberOfSeats)
                .sessionAttr("configDB", configDB))
                .andExpect(view().name("booking"));
    }

    @Test
    public void shouldReBookTickets() throws Exception {
        NumberOfSeats numberOfSeats = configTest.getNumberOfSeat();
        ConfigDB configDB = new ConfigDB();
        PassengerWrapper passengerWrapper = configTest.getPassengerWrapper();
        when(routeService.getRouteBasedOnId(1)).thenReturn(configTest.getRouteDetails());
        when(seatSelectionService.getAvailableSeat("KA-01 G-2020", 1)).thenReturn(configTest.getAvailableSeatwrapper());
        mockMvc.perform(get("/reBooking")
                .sessionAttr("status", SyntaxSugar.LOGGED_IN)
                .sessionAttr("numberOfSeats", numberOfSeats)
                .sessionAttr("configDB", configDB)
                .sessionAttr("passengerWrapper", passengerWrapper))
                .andExpect(view().name("booking"));
    }

    @Test
    public void shouldRedirectToLoginIfNotLoogedIt() throws Exception {
        mockMvc.perform(get("/reBooking")
                .sessionAttr("status", SyntaxSugar.LOGGED_OUT))
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    public void shouldRedirectToSearchRoutesIfBookingIsDirectlyAccessed() throws Exception {
        mockMvc.perform(get("/booking"))
                .andExpect(view().name("redirect:/searchRoutes"));
    }
}
