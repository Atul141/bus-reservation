package ServiceImplTest;


import Dao.BusDao;
import Dao.SeatsDao;
import ServiceImpl.*;
import ServiceImpl.SaveImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SeatSelectionImplTest {
    private SeatSelectionImpl seatSelection;
    private SeatsDao seatsDao;
    private ConfigDB configDB;
    private ConfigTest configTest;
    private BusDao busDao;


    @Before
    public void setup() {
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        seatSelection = new SeatSelectionImpl(configDB);
        configTest = new ConfigTest();
        seatsDao = configTest.getSeatDetails();
        busDao = configTest.getBusWrapper();

        SaveImpl saveImpl = new SaveImpl(configDB);
        saveImpl.saveToDb(seatsDao);
        saveImpl.saveToDb(busDao);
    }

    @Test
    public void shouldReturnAvailableSeats() {
        assertEquals(1, seatSelection.getAvailableSeats("KA-09 G-2222", 2).getId());
    }

    @Test
    public void shouldAbleToUpdateAvailableSeats() {
        List<String> availableGeneral = new ArrayList<String>();
        availableGeneral.add("C1");
        availableGeneral.add("C2");
        availableGeneral.add("C3");
        availableGeneral.add("C4");
        availableGeneral.add("D1");
        availableGeneral.add("D2");
        availableGeneral.add("D3");
        availableGeneral.add("D4");
        assertEquals(availableGeneral, seatSelection.getAvailableSeats("KA-09 G-2222", 2L).getGeneral());

        availableGeneral.remove("C1");
        availableGeneral.remove("C2");
        availableGeneral.remove("D1");
        availableGeneral.remove("D2");
        availableGeneral.remove("D3");
        availableGeneral.remove("D4");

        SeatsDao seatsDao = configTest.getSeatDetailsExpected();
        seatSelection.updateAvailableSeats(seatsDao);
        assertEquals(availableGeneral, seatSelection.getAvailableSeats("KA-09 G-2222", 2L).getGeneral());

    }

    @After
    public void delete() {
        configTest.delete(seatsDao);
        configTest.delete(busDao);
    }
}
