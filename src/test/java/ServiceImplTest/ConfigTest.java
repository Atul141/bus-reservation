package ServiceImplTest;

import Dao.BusDao;
import Dao.RouteDao;
import Dao.SeatsDao;
import Dao.UserDetailsDao;
import Models.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ConfigTest {

    public UserDetailsDao getUserDetailsinstance() {
        UserDetailsDao userDetailsDao = new UserDetailsDao();
        userDetailsDao.setFirstName("abc");
        userDetailsDao.setLastName("def");
        userDetailsDao.setEmail("test5@gmail.com");
        userDetailsDao.setPassword("pass");
        userDetailsDao.setPhone("1123456");
        return userDetailsDao;
    }

    public RouteDao getRouteDaoDetails() {
        RouteDao route = new RouteDao();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            route.setDate(formatter.parse("2017-1-18"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public BusDao getBusWrapper() {
        BusDao busDao = new BusDao();
        busDao.setNumber("KA 09 G-9000");
        busDao.setSeat_no(1);
        busDao.setRoute_no(2);
        return busDao;
    }

    public Route getRouteDetails() {
        Route route = new Route();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date=dateFormat.parse("2017-1-15");
            route.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
