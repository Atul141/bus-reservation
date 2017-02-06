package ControllerTest;

import com.sample.Controller.DefaultController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/atulbk/Desktop/untitled folder 2/tw-bus-reservation/src/main/webApp/WEB-INF/dispatcher-servlet.xml")
public class DefaultControllerTest {


    private DefaultController defaultController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        defaultController = new DefaultController();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(defaultController).build();

    }

    @Test
    public void shouldReturnIndexForDefaultPage() throws Exception {

            mockMvc.perform(get("/"))
            .andExpect(view().name("index"));
    }
    @Test
    public void shouldReturnIndexForHomePage() throws Exception {

            mockMvc.perform(get("/default"))
            .andExpect(view().name("index"));
    }


}
