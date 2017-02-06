package ControllerTest;


import Models.AvailableSeatWrapper;
import Models.PassengerWrapper;
import Models.PaymentWrapper;
import ServiceImplTest.ConfigTest;
import Services.PassengerService;
import com.sample.Controller.BookingController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.Assert.assertEquals;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/atulbk/Desktop/untitled folder 2/tw-bus-reservation/src/main/webApp/WEB-INF/dispatcher-servlet.xml")

public class BookingControllerTest {
    private ConfigTest configTest;

    @Mock
    PassengerService passengerService;

    @InjectMocks
    BookingController bookingController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        configTest=new ConfigTest();
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();

    }


    @Test
    public void shouldReturnBookingPage(){

//        PassengerWrapper passengerWrapper=new PassengerWrapper();
//        this.mockMvc.perform((post("/booking"))
//                .param("passengerWrapper","","")
//                .andExpect(view().name("booking"))
//////                .param()
////    }
}}
