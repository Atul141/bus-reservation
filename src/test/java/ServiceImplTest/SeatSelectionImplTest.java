package ServiceImplTest;


import Dao.BusDao;
import Dao.SeatsDao;
import ServiceImpl.ConfigDB;
import ServiceImpl.SeatSelectionImpl;
import ServiceImpl.SyntaxSugar;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SeatSelectionImplTest {
    private Session session = null;
    private SeatSelectionImpl seatSelection;
    private SeatsDao seatsDao;
    private ConfigDB configDB;


    @Before
    public void setup() {
        configDB=new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        seatSelection = new SeatSelectionImpl(configDB);
        ConfigTest configTest = new ConfigTest();
        session = configTest.getTestSession();
        seatsDao = configTest.getSeatDetails();
        BusDao busDao = configTest.getBusWrapper();
        Transaction transaction = session.beginTransaction();
        session.save(seatsDao);
        session.save(busDao);
        session.flush();
        transaction.commit();
    }

    private List<String> getSeatList(String disabledReserved) {
        String[] array = disabledReserved.split("-");
        List<String> list = Arrays.asList(array);
        return list;

    }

    @Test
    public void shouldReturnAvailableSeats() {
        SeatsDao seatsDao = new SeatsDao();
        seatsDao.setId(1);
        assertEquals(1, seatSelection.getAvailableSeats("KA 09 G-9000", 2).getId());
    }

    @After
    public void delete(){
        Transaction delete = session.beginTransaction();
        session.delete(seatsDao);
        delete.commit();
        session.close();
    }
}
