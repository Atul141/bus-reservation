package ServiceTest;


import Dao.RouteDao;
import Database.ConfigTest;
import Models.Route;
import ServiceImpl.RoutesImpl;
import Services.RouteService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.enterprise.inject.New;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RouteServiceTest {

    private ConfigTest configTest;
    private RouteService routeService;

    @Before
    public void setup(){
        configTest=new ConfigTest();
        routeService=new RouteService();
    }
    @Mock
    RoutesImpl routesImpl;

    @Test
    public void getRoutesBaseOnId(){
        RouteDao routeDao=configTest.getRouteDaoDetails();
        initMocks(this);
        when(routesImpl.getRoutesBasedOnId(1)).thenReturn(routeDao);
        assertEquals(1,routeService.getRouteBasedOnId(1).getId());
    }
    @Test
    public void getRoutesList(){
        RouteDao routeDao=configTest.getRouteDaoDetails();
        initMocks(this);
        Route route= new Route();
        route.setId(1);
        route.setSource("BANGALORE");
        route.setDestination("MYSORE");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 15);
        Date date = calendar.getTime();
        route.setDate(date);

        List<RouteDao> routeList=new ArrayList<RouteDao>();
        routeList.add(configTest.getRouteDaoDetails());
        when(routesImpl.getRoutes(route)).thenReturn(routeList);
        assertEquals(1,routeService.getRouteList(route).size());
    }
}
