package ServiceImplTest;

import Dao.*;
import Models.Passenger;
import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ConfigTest {

    private OrderDetailsDao orderDetails;

    public UserDetailsDao getUserDetailsinstance() {
        UserDetailsDao userDetailsDao = new UserDetailsDao();
        userDetailsDao.setFirstName("abc");
        userDetailsDao.setLastName("def");
        userDetailsDao.setEmail("test5@gmail.com");
        userDetailsDao.setPassword("pass");
        userDetailsDao.setPhone("1123456");
        userDetailsDao.setId(1);
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
            Date date = dateFormat.parse("2017-1-15");
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

    public PassengerDao getPassengerDetails() {
        PassengerDao passengerDao = new PassengerDao();
        passengerDao.setId(1);
        java.util.Date today = new java.util.Date();
        Timestamp timestamp = new java.sql.Timestamp(today.getTime());
        passengerDao.setTimestamp(timestamp);
        passengerDao.setSeat("A1");
        passengerDao.setAge(22);
        passengerDao.setGender("Male");
        passengerDao.setName("Abc");
        passengerDao.setIsDisabled('N');
        passengerDao.setIsSeniorCitizen('N');
        return passengerDao;
    }

    public void delete(Object object) {
        ConfigDB configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        Session session = configDB.getSession();
        Transaction delete = session.beginTransaction();
        session.delete(object);
        delete.commit();
        session.close();
    }

    public OrderDetailsDao getOrderDetails() {
        OrderDetailsDao orderDetailsDao=new OrderDetailsDao();
        java.util.Date today = new java.util.Date();
        Timestamp timestamp = new java.sql.Timestamp(today.getTime());
        orderDetailsDao.setTime(timestamp);
        orderDetailsDao.setStatus("confirm");
        orderDetailsDao.setRoute_id(1);
        orderDetailsDao.setId(1);
        orderDetailsDao.setPrice(600);
        orderDetailsDao.setEmail("abc@gmail.com");
        return orderDetailsDao;
    }
}
