package ControllerTest;


import Models.Payment;
import ServiceImplTest.ConfigTest;
import Validators.PaymentValidator;
import com.sample.Controller.PaymentController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PaymentControllerTest {
    private MockMvc mockMvc;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = configTest.getViewInstance();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(paymentController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @InjectMocks
    PaymentController paymentController;
    @Mock
    PaymentValidator paymentValidator;
    @Test
    public void shouldReturnToPaymentPage() throws Exception {
        mockMvc.perform(get("/payment"))
                .andExpect(view().name("payment"));
    }
    @Autowired
    Payment payment;

    @Test
    public void shouldReturnToPaymentPageIfValidationIsUnSuccessfull() throws Exception {
    payment=configTest.getPayment();
    payment.setName("  ");
        mockMvc.perform(post("/validatePayment").flashAttr("payment",payment))
                .andExpect(view().name("redirect:/payment"));
    }
    @Test
    public void shouldReturnToDisplayOrderDetailsPageIfValidationIsSuccessfull() throws Exception {
    payment=configTest.getPayment();
        mockMvc.perform(post("/validatePayment").flashAttr("payment",payment))
                .andExpect(view().name("redirect:/DisplayOrderDetails"));
    }
}
