package ControllerTest;


import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.RouteService;
import com.sample.Controller.HomeController;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:/Users/atulbk/Desktop/untitled folder 2/tw-bus-reservation/src/main/webApp/WEB-INF/dispatcher-servlet.xml")

public class HomeControllerTest {

    private MockMvc mockMvc;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        InternalResourceViewResolver viewResolver = configTest.getViewInstance();

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(homeController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Mock
    RouteService routeService;
    @InjectMocks
    HomeController homeController;
    @Autowired
    HttpServletRequest request;

    @Test
    public void shouldReturnSearchRoutesPage() throws Exception {
        Cookie cookie = new Cookie("userEmail", "abcd@gmail.com");
        mockMvc.perform(get("/searchRoutes").cookie(cookie))
                .andExpect(view().name("searchRoutes"));

    }

    @Test
    public void shouldReturnToSearchIfValidationIsUnSuccessfull() throws Exception {
        Route route = configTest.getRouteDetails();
        route.setSource("  ");
        mockMvc.perform(post("/Home").flashAttr("route", route).param("selectedDate", "2017-1-15"))
                .andExpect(view().name("redirect:/searchRoutes"));
    }

    @Test
    public void shouldReturnToNoRouteFoundIfValidationIsSuccessfullAndNoRoutesAreAvailable() throws Exception {
        Route route = configTest.getRouteDetails();
        when(routeService.getRouteList(route)).thenReturn(null);
        ConfigDB configDB=new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        mockMvc.perform(post("/Home").flashAttr("route", route).param("selectedDate", "2017-1-15").sessionAttr("configDB",configDB))
                .andExpect(view().name("NoRouteFound"));
    }
    @Test
    public void shouldReturnToHomeIfValidationIsSuccessfull() throws Exception {
        Route route = configTest.getRouteDetails();
        List<Route> routeList=new ArrayList<Route>();
        routeList.add(route);
        when(routeService.getRouteList(route)).thenReturn(routeList);
        mockMvc.perform(post("/Home").flashAttr("route", route).param("selectedDate", "2017-1-15").sessionAttr("configDB",new ConfigDB()))
                .andExpect(view().name("home"));
    }
}
