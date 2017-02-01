package ServiceImplTest;


import Dao.RouteDao;
import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImpl.RoutesImpl;
import ServiceImpl.SyntaxSugar;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;


public class RouteImplTest {

    private Session session = null;
    private RoutesImpl routes;
    private RouteDao routeDao;
    private ConfigDB configDB;

    @Before
    public void setup() {
        configDB=new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        routes = new RoutesImpl(configDB);
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            route.setDate(formatter.parse("2017-1-18"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
