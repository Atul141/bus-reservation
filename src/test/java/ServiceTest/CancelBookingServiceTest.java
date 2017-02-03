package ServiceTest;


import Models.AvailableSeatWrapper;
import Models.Passenger;
import Models.PassengerWrapper;
import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.CancelBookingService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CancelBookingServiceTest {

    private CancelBookingService cancelBookingService;
    private ConfigDB configDB;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        cancelBookingService = new CancelBookingService(configDB);
    }

    @Test
    public void shouldUpdateAvailableSeatsInRoute() {
        Route route = configTest.getRouteDetails();
        assertEquals(5, route.getAvailableNoSeats());

        route = cancelBookingService.updateRoute(route, 2);
        assertEquals(7, route.getAvailableNoSeats());
    }

    @Test
    public void shouldUpdateAvailableSeats() {
        PassengerWrapper passengerWrapper = new PassengerWrapper();
        List<Passenger> passengerList = new ArrayList<Passenger>();
        passengerList.add(configTest.getPassengerDetails());
        passengerWrapper.setPassengerList(passengerList);

        AvailableSeatWrapper availableSeatWrapperTotal = configTest.getAvailableSeatwrapper();
        AvailableSeatWrapper availableSeatWrapperUpdate = configTest.getAvailableSeatwrapperAfterUpdate();
        AvailableSeatWrapper availableSeatWrapperResult = cancelBookingService.updateAvailableSeats(passengerWrapper, availableSeatWrapperUpdate, availableSeatWrapperTotal);

        assertEquals(availableSeatWrapperUpdate.getWomenReservation(), availableSeatWrapperResult.getWomenReservation());
        assertEquals(availableSeatWrapperUpdate.getGeneral(), availableSeatWrapperResult.getGeneral());
        assertEquals(availableSeatWrapperUpdate.getSeniorCitizenReserved(), availableSeatWrapperResult.getSeniorCitizenReserved());
        assertEquals(availableSeatWrapperUpdate.getDisabledReserved(), availableSeatWrapperResult.getDisabledReserved());
    }

}
