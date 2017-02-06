package ControllerTest;


import Models.OrderDetails;
import Models.OrderWrapper;
import ServiceImpl.ConfigDB;
import ServiceImplTest.ConfigTest;
import com.sample.Controller.UserBookingsController;
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


public class UserBookingControllerTest {

    private MockMvc mockMvc;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver =configTest.getViewInstance();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userBookingsController)
                .setViewResolvers(viewResolver)
                .build();
    }
@InjectMocks
    UserBookingsController userBookingsController;

    @Test
    public void shouldReturnToUserBookingPage() throws Exception {
        mockMvc.perform(get("/UserBookings"))
                .andExpect(view().name("UserBookings"));
    }
    @Test
    public void shouldReturnSavedOrderDetailsPage() throws Exception {
        OrderWrapper orderWrapper=new OrderWrapper();
        orderWrapper.setId(1);
        mockMvc.perform(post("/savedOrderDetails").flashAttr("orderWrapper",orderWrapper).sessionAttr("configDB",new ConfigDB()))
                .andExpect(view().name("/savedOrderDetails"));
    }
}
