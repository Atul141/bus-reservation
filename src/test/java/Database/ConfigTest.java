package Database;

import Dao.BusDao;
import Dao.RouteDao;
import Dao.SeatsDao;
import Dao.UserDetailsDao;
import Models.BusWrapper;
import Models.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;


public class ConfigTest {

    public Session getTestSession() {
        SessionFactory sessionFactory;
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDetailsDao.class);
        configuration.addAnnotatedClass(RouteDao.class);
        configuration.addAnnotatedClass(SeatsDao.class);
        configuration.addAnnotatedClass(BusDao.class);
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL94Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/testdb");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

    public UserDetailsDao getUserDetailsinstance() {
        UserDetailsDao userDetailsDao = new UserDetailsDao();
        userDetailsDao.setFirstName("abc");
        userDetailsDao.setLastName("def");
        userDetailsDao.setEmail("test@gmail.com");
        userDetailsDao.setPassword("pass");
        userDetailsDao.setPhone("1123456789");
        return userDetailsDao;
    }

    public RouteDao getRouteDaoDetails() {
        RouteDao route = new RouteDao();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 15);
        Date date = calendar.getTime();
        route.setDate(date);
        route.setDepartureTime(new Time(1730));
        route.setDestination("BANGALORE");
        route.setSource("MYSORE");
        route.setPrice(300);
        route.setBus_no("KA-01 G-2020");
        route.setDistance(150);
        route.setId(1);
        return route;
    }

    public SeatsDao getSeatDetails() {
        SeatsDao seatDao = new SeatsDao();
        seatDao.setDisabledReserved("B1-B2");
        seatDao.setId(1);
        seatDao.setSeniorCitizenReserved("B3-B4");
        seatDao.setWomenReservation("A1-A2-A3-A4");
        seatDao.setGeneral("C1-C2-C3-C4-D1-D2-D3-D4");
        return seatDao;
    }
    public BusDao getBusWrapper(){
        BusDao busDao=new BusDao();
        busDao.setNumber(" KA 09 G-9000 ");
        busDao.setSeat_no(1);
        busDao.setRoute_no(2);
        return busDao;
    }

    public Route getRouteDetails(){
        Route route = new Route();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 15);
        Date date = calendar.getTime();
        route.setDate(date);
        route.setDepartureTime(new Time(1730));
        route.setDestination("BANGALORE");
        route.setSource("MYSORE");
        route.setPrice(300);
        route.setBus_no("KA-01 G-2020");
        route.setDistance(150);
        route.setId(1);
        return route;
    }

}
