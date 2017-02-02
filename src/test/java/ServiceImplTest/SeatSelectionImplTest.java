package ServiceImplTest;


import Dao.BusDao;
import Dao.SeatsDao;
import ServiceImpl.*;
import ServiceImpl.SaveImpl;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeatSelectionImplTest {
    private Session session = null;
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
        session = configDB.getSession();
        seatsDao = configTest.getSeatDetails();
        busDao = configTest.getBusWrapper();

        SaveImpl saveImpl = new SaveImpl(configDB);
        saveImpl.saveToDb(seatsDao);
        saveImpl.saveToDb(busDao);
    }

    @Test
    public void shouldReturnAvailableSeats() {
        assertEquals(1, seatSelection.getAvailableSeats("KA 09 G-9000", 2).getId());
    }

    @After
    public void delete() {
        configTest.delete(seatsDao);
        configTest.delete(busDao);
    }
}
