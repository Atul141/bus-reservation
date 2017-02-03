package ServiceTest;


import Dao.BusDao;
import Dao.SeatsDao;
import Models.AvailableSeatWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.SaveImpl;
import ServiceImpl.SeatSelectionImpl;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.SeatSelectionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class SeatSelectionServiceTest {


    private SeatSelectionService seatSelectionService;
    private SeatsDao seatsDao;
    private ConfigDB configDB;
    private ConfigTest configTest;
    private BusDao busDao;


    @Before
    public void setup() {
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        seatSelectionService = new SeatSelectionService(configDB);
        configTest = new ConfigTest();
        seatsDao = configTest.getSeatDetails();
        busDao = configTest.getBusWrapper();

        SaveImpl saveImpl = new SaveImpl(configDB);
        saveImpl.saveToDb(seatsDao);
        saveImpl.saveToDb(busDao);
    }


    @Test
    public void shouldAbleToGetAvailableSeats() {

        List<String> availableGeneral = new ArrayList<String>();
        availableGeneral.add("C1");
        availableGeneral.add("C2");
        availableGeneral.add("C3");
        availableGeneral.add("C4");
        availableGeneral.add("D1");
        availableGeneral.add("D2");
        availableGeneral.add("D3");
        availableGeneral.add("D4");
        assertEquals(availableGeneral, seatSelectionService.getAvailableSeat("KA-09 G-2222", 2L).getGeneral());
    }

    @Test
    public void shouldAbleToUpdateAvailableSeats() {
        AvailableSeatWrapper availableSeatWrapperExpected = configTest.getAvailableSeatwrapperAfterUpdate();
        List<String> availableGeneral = new ArrayList<String>();
        availableGeneral.add("C1");
        availableGeneral.add("C2");
        availableGeneral.add("C3");
        availableGeneral.add("C4");
        availableGeneral.add("D1");
        availableGeneral.add("D2");
        availableGeneral.add("D3");
        availableGeneral.add("D4");
        assertEquals(availableGeneral, seatSelectionService.getAvailableSeat("KA-09 G-2222", 2L).getGeneral());

        availableGeneral.remove("C1");
        availableGeneral.remove("C2");
        availableGeneral.remove("D1");
        availableGeneral.remove("D2");
        availableGeneral.remove("D3");
        availableGeneral.remove("D4");
        seatSelectionService.updateAvailableSeats(availableSeatWrapperExpected);
        assertEquals(availableGeneral, seatSelectionService.getAvailableSeat("KA-09 G-2222", 2L).getGeneral());

    }

    @After
    public void delete() {
        configTest.delete(seatsDao);
        configTest.delete(busDao);
    }
}
