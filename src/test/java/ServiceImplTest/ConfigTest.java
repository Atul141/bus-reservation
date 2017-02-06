package ServiceImplTest;

import Dao.*;
import Models.*;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ConfigTest {


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

    public SeatsDao getSeatDetailsExpected() {
        SeatsDao seatDao = new SeatsDao();
        seatDao.setDisabledReserved("B1-B2");
        seatDao.setId(1);
        seatDao.setSeniorCitizenReserved("B3-B4");
        seatDao.setWomenReservation("A1-A2-A3-A4");
        seatDao.setGeneral("C3-C4");
        return seatDao;
    }

    public BusDao getBusWrapper() {
        BusDao busDao = new BusDao();
        busDao.setNumber("KA-09 G-2222");
        busDao.setSeat_no(1);
        busDao.setRoute_no(2);
        return busDao;
    }

    public Route getRouteDetails() {
        Route route = new Route();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse("2017-2-5");
            route.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        route.setDepartureTime(new Time(20,5,3));
        route.setDestination("BANGALORE");
        route.setSource("MYSORE");
        route.setPrice(300);
        route.setBus_no("KA-01 G-2020");
        route.setDistance(150);
        route.setId(1);
        route.setAvailableNoSeats(5);
        return route;
    }

    public PassengerDao getPassengerDaoDetails() {
        PassengerDao passengerDao = new PassengerDao();
        passengerDao.setId(1);
        passengerDao.setOrderId(1);
        passengerDao.setSeat("A1");
        passengerDao.setAge(22);
        passengerDao.setGender("Male");
        passengerDao.setName("Abc");
        passengerDao.setIsDisabled('N');
        passengerDao.setIsSeniorCitizen('N');
        return passengerDao;
    }

    public Passenger getPassengerDetails() {
        Passenger passengerDao = new Passenger();
        passengerDao.setSeat("A1");
        passengerDao.setAge(22);
        passengerDao.setGender("Male");
        passengerDao.setName("Abc");
        passengerDao.setIsDisabled(true);
        passengerDao.setIsSeniorCitizen(false);
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

    public OrderDetailsDao getOrderDetailsDao() {
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
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

    public AvailableSeatWrapper getAvailableSeatwrapper() {
        AvailableSeatWrapper availableSeatWrapper = new AvailableSeatWrapper();

        List<String> availableGeneral = new ArrayList<String>();
        availableGeneral.add("C1");
        availableGeneral.add("C2");
        availableGeneral.add("C3");
        availableGeneral.add("C4");
        availableSeatWrapper.setGeneral(availableGeneral);

        List<String> availableWomen = new ArrayList<String>();
        availableWomen.add("A1");
        availableWomen.add("A2");
        availableWomen.add("A3");

        List<String> availableDisabled = new ArrayList<String>();
        availableDisabled.add("B1");
        availableDisabled.add("B2");

        List<String> availableSenior = new ArrayList<String>();
        availableSenior.add("B3");

        availableSeatWrapper.setGeneral(availableGeneral);
        availableSeatWrapper.setSeniorCitizenReserved(availableSenior);
        availableSeatWrapper.setDisabledReserved(availableDisabled);
        availableSeatWrapper.setWomenReservation(availableWomen);
        return availableSeatWrapper;
    }

    public AvailableSeatWrapper getAvailableSeatwrapperAfterUpdate() {
        AvailableSeatWrapper availableSeatWrapper = new AvailableSeatWrapper();
        availableSeatWrapper.setId(1);
        List<String> availableGeneral = new ArrayList<String>();
        availableGeneral.add("C3");
        availableGeneral.add("C4");
        availableSeatWrapper.setGeneral(availableGeneral);

        List<String> availableWomen = new ArrayList<String>();
        availableWomen.add("A2");
        availableWomen.add("A3");

        List<String> availableDisabled = new ArrayList<String>();
        availableDisabled.add("B1");

        List<String> availableSenior = new ArrayList<String>();
        availableSenior.add("B3");
        availableSeatWrapper.setGeneral(availableGeneral);
        availableSeatWrapper.setSeniorCitizenReserved(availableSenior);
        availableSeatWrapper.setDisabledReserved(availableDisabled);
        availableSeatWrapper.setWomenReservation(availableWomen);
        return availableSeatWrapper;
    }

    public SelectedSeatWrapper getSeatSelectionWrapper() {
        SelectedSeatWrapper selectedSeatWrapper = new SelectedSeatWrapper();
        List<String> selectedSeatWomen = new ArrayList<String>();
        selectedSeatWomen.add("A1");

        List<String> selectedSeatGeneral = new ArrayList<String>();
        selectedSeatGeneral.add("C1");
        selectedSeatGeneral.add("C2");

        List<String> selectedDisabled = new ArrayList<String>();
        selectedDisabled.add("B2");

        List<String> selectedSenior = new ArrayList<String>();
        selectedSenior.add("B4");


        selectedSeatWrapper.setSelectedSeatWomen(selectedSeatWomen);
        selectedSeatWrapper.setSelectedSeatDisabled(selectedDisabled);
        selectedSeatWrapper.setSelectedSeatSeniorCitizen(selectedSenior);
        selectedSeatWrapper.setSelectedSeatGeneral(selectedSeatGeneral);
        return selectedSeatWrapper;
    }

    public PassengerWrapper getPassengerWrapper() {

        PassengerWrapper passengerWrapper = new PassengerWrapper();
        Passenger passenger = new Passenger();
        passenger.setName("abd");
        passenger.setGender("Male");
        passenger.setAge(22);
        passenger.setSeat("C1");
        Passenger passenger1 = new Passenger();
        passenger1.setName("abd");
        passenger1.setGender("Female");
        passenger1.setAge(34);
        passenger1.setSeat("B2");
        Passenger passenger2 = new Passenger();
        passenger2.setName("Sample");
        passenger2.setGender("Female");
        passenger2.setAge(24);
        passenger2.setIsDisabled(true);
        passenger2.setSeat("A1");
        List<Passenger> passengerList = new ArrayList<Passenger>();
        passengerList.add(passenger1);
        passengerList.add(passenger2);
        passengerList.add(passenger);
        passengerWrapper.setPassengerList(passengerList);
        return passengerWrapper;

    }


    public OrderDetails getOrderDetails(long minutes) {
        OrderDetails orderDetailsDao = new OrderDetails();
        Calendar cal = Calendar.getInstance();
        Long time = cal.getTimeInMillis();
        Long milliseconds = (minutes * 60000);
        time -= milliseconds;
        Timestamp timestamp = new Timestamp(time);

        orderDetailsDao.setTime(timestamp);
        orderDetailsDao.setStatus("confirm");
        orderDetailsDao.setRoute_id(1);
        orderDetailsDao.setId(1);
        orderDetailsDao.setPrice(600);
        orderDetailsDao.setEmail("abc@gmail.com");
        return orderDetailsDao;
    }

    public List<Integer> getMonthsList() {
        List<Integer> monthsList = new ArrayList<Integer>();
        for (int i = 1; i <= 12; i++) {
            monthsList.add(i);
        }
        return monthsList;
    }

    public List<String> getCardType() {
        List<String> cardType = new ArrayList<String>();
        cardType.add("Visa");
        cardType.add("American Express");
        cardType.add("MaterCard");
        return cardType;
    }

    public List<Integer> getYearsList() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> yearsList = new ArrayList<Integer>();
        for (int i = year; i <= year + 10; i++)
            yearsList.add(i);
        return yearsList;
    }
}
