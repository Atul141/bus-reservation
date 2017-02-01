package ServiceImplTest;


import Dao.PassengerDao;
import ServiceImpl.ConfigDB;
import ServiceImpl.PassengerDetailsImpl;
import ServiceImpl.SyntaxSugar;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PassengerDetailsImplTest {

    private Session session;
    private ConfigDB configDB;
    private PassengerDetailsImpl passengerDetails;
    private PassengerDao passengerDao;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        session = configDB.getSession();
        passengerDetails = new PassengerDetailsImpl(configDB);
        configTest = new ConfigTest();

    }

    @Test
    public void shouldSavePassengerDetails() {
        passengerDao = configTest.getPassengerDetails();
        passengerDetails.savePassengerDetails(passengerDao);
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        session = configDB.getSession();

        String query = "FROM PassengerDao  passenger where passenger.id= 1";
        Transaction transaction = session.beginTransaction();
        PassengerDao passengerDao1 = (PassengerDao) session.createQuery(query).uniqueResult();
        transaction.commit();
        assertEquals("Abc", passengerDao1.getName());

    }

    @After
    public void delete() {
       configTest.delete(passengerDao);
    }
}
