package ServiceTest;


import Models.AvailableSeatWrapper;
import Models.Route;
import Models.SelectedSeatWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.OrderDetailsService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDetailsTest {

    private OrderDetailsService orderDetailsService;
    private ConfigDB configDB;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        orderDetailsService = new OrderDetailsService(configDB);
    }

    @Test
    public void shouldUpdateRoute() {
        Route route = new Route();
        route.setAvailableNoSeats(10);
        assertEquals(8, orderDetailsService.updateRoute(route, 2).getAvailableNoSeats());

    }

    @Test
    public void shouldUpdateAvailableSeat() {
        AvailableSeatWrapper availableSeatWrapper = configTest.getAvailableSeatwrapper();
        AvailableSeatWrapper availableSeatWrapperUpdated = configTest.getAvailableSeatwrapperAfterUpdate();
        SelectedSeatWrapper selectedSeatWrapper = configTest.getSeatSelectionWrapper();
        availableSeatWrapper = orderDetailsService.updateAvailableSeats(availableSeatWrapper, selectedSeatWrapper);

        assertEquals(availableSeatWrapperUpdated.getGeneral(), availableSeatWrapper.getGeneral());
        assertEquals(availableSeatWrapperUpdated.getWomenReservation(), availableSeatWrapper.getWomenReservation());
        assertEquals(availableSeatWrapperUpdated.getSeniorCitizenReserved(), availableSeatWrapper.getSeniorCitizenReserved());
        assertEquals(availableSeatWrapperUpdated.getDisabledReserved(), availableSeatWrapper.getDisabledReserved());
    }
}

