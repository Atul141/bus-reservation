package ServiceTest;


import Models.AvailableSeatWrapper;
import Models.Route;
import Models.SelectedSeatWrapper;
import Services.OrderDetailsService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDetailsTest {

    private OrderDetailsService orderDetailsService;

    @Before
    public void setup() {
        orderDetailsService = new OrderDetailsService();
    }

    @Test
    public void shouldUpdateRoute() {
        Route route = new Route();
        route.setAvailableNoSeats(10);
        assertEquals(8, orderDetailsService.updateRoute(route, 2).getAvailableNoSeats());

    }

    @Test
    public void shouldUpdateAvailableSeat() {
        AvailableSeatWrapper availableSeatWrapper = new AvailableSeatWrapper();
        SelectedSeatWrapper selectedSeatWrapper = new SelectedSeatWrapper();

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
        availableSenior.add("B$");

        availableSeatWrapper.setGeneral(availableGeneral);
        availableSeatWrapper.setSeniorCitizenReserved(availableSenior);
        availableSeatWrapper.setDisabledReserved(availableDisabled);
        availableSeatWrapper.setWomenReservation(availableWomen);

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

        availableSeatWrapper = orderDetailsService.updateAvailableSeats(availableSeatWrapper, selectedSeatWrapper);

        availableGeneral.remove("C1");
        availableGeneral.remove("C2");

        availableWomen.remove("A1");
        availableDisabled.remove("B2");
        availableSenior.remove("B4");

        assertEquals(availableGeneral, availableSeatWrapper.getGeneral());
        assertEquals(availableWomen, availableSeatWrapper.getWomenReservation());
        assertEquals(availableSenior, availableSeatWrapper.getSeniorCitizenReserved());
        assertEquals(availableDisabled, availableSeatWrapper.getDisabledReserved());
    }
}

