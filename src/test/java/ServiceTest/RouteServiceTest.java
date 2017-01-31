package ServiceTest;


import Dao.RouteDao;
import ServiceImplTest.ConfigTest;
import Models.Route;
import ServiceImpl.RoutesImpl;
import Services.RouteService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class RouteServiceTest {

    private ConfigTest configTest;
    private RouteService routeService;
    private RoutesImpl routesImpl;

    @Before
    public void setup() {
        configTest = new ConfigTest();
//        routeService=mock(RouteService.class);
        routesImpl = mock(RoutesImpl.class);
        routeService = new RouteService();
    }
//    @Mock
//    RoutesImpl routesImpl;

//    @InjectMocks
//   RouteService routeService =new RouteService();


    @Test
    public void getRoutesBaseOnId() {
        RouteDao routeDao = configTest.getRouteDaoDetails();
        initMocks(this);
        when(routesImpl.getRoutesBasedOnId(1)).thenReturn(routeDao);
        assertEquals(1, routeService.getRouteBasedOnId(1).getId());
    }

    @Test
    public void getRoutesList() {
        RouteDao routeDao = configTest.getRouteDaoDetails();
        initMocks(this);
        Route route = new Route();
        route.setId(1);
        route.setSource("BANGALORE");
        route.setDestination("MYSORE");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 15);
        Date date = calendar.getTime();
        route.setDate(date);

        List<RouteDao> routeList = new ArrayList<RouteDao>();
        routeList.add(configTest.getRouteDaoDetails());
        when(routesImpl.getRoutes(route)).thenReturn(routeList);
        routeService.getRouteList(route);
        verify(routesImpl, times(1)).getRoutes(route);
    }
}
