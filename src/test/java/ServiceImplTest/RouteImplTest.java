package ServiceImplTest;


import Dao.RouteDao;
import Models.Route;
import ServiceImpl.*;
import ServiceImpl.SaveToDb;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;


public class RouteImplTest {

    private Session session ;
    private RoutesImpl routes;
    private RouteDao routeDao;
    private ConfigDB configDB;

    @Before
    public void setup() {
        configDB=new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        routes = new RoutesImpl(configDB);
        session=configDB.getSession();
        ConfigTest configTest = new ConfigTest();
        routeDao = configTest.getRouteDaoDetails();
        ServiceImpl.SaveToDb saveToDb=new SaveToDb(configDB);
        saveToDb.saveToDb(routeDao);
    }

    @Test
    public void shouldReturnRouteDetailsForTheGivenRouteID() {
        assertEquals("MYSORE", routes.getRoutesBasedOnId(1).getSource());
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
