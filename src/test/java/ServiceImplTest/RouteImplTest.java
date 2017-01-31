package ServiceImplTest;


import Dao.RouteDao;
import Database.ConfigTest;
import Models.Route;
import ServiceImpl.RoutesImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class RouteImplTest {

    private Session session = null;
    private RoutesImpl routes;
    private RouteDao routeDao;

    @Before
    public void setup() {
        routes = new RoutesImpl();
        ConfigTest configTest = new ConfigTest();
        session = configTest.getTestSession();
        routeDao = configTest.getRouteDaoDetails();
        Transaction transaction = session.beginTransaction();
        session.save(routeDao);
        session.flush();
        transaction.commit();
    }

    @Test
    public void shouldReturnRouteDetailsForTheGivenRouteID() {
        assertEquals("KA-01 G-2020", routes.getRoutesBasedOnId(1).getBus_no());
    }

    @Test
    public void shouldReturnRouteDetailsForTheGivenRoute() {
        Route route = new Route();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 15);
        Date date = calendar.getTime();
        route.setDate(date);
        route.setDestination("BANGALORE");
        route.setSource("MYSORE");
        assertEquals(1, routes.getRoutes(route).size());
    }

    @After
    public void delete() {
        Transaction delete = session.beginTransaction();
        session.delete(routeDao);
        delete.commit();
        session.close();
    }

}
