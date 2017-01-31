package ServiceTest;


import Dao.RouteDao;
import ServiceImplTest.ConfigTest;
import Models.Route;
import ServiceImpl.RoutesImpl;
import Services.RouteService;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        routesImpl = mock(RoutesImpl.class);
        routeService = new RouteService();
    }


    @Test
    public void getRoutesBaseOnId() {
        RouteDao routeDao = configTest.getRouteDaoDetails();
        initMocks(this);
        when(routesImpl.getRoutesBasedOnId(1)).thenReturn(routeDao);
        assertEquals(1, routeService.getRouteBasedOnId(1).getId());
    }

    @Test
    public void getRoutesList() {
        initMocks(this);
        RouteDao routeDao = configTest.getRouteDaoDetails();
        initMocks(this);
        Route route = new Route();
        route.setId(1);
        route.setSource("BANGALORE");
        route.setDestination("MYSORE");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse("2017-1-18");
            route.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<RouteDao> routeList = new ArrayList<RouteDao>();
        when(routesImpl.getRoutes(any(Route.class))).thenReturn(routeList);
        assertEquals(routeList, routeService.getRouteList(route));
    }
}
