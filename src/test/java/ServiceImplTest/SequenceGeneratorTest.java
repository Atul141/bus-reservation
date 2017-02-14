package ServiceImplTest;


import ServiceImpl.ConfigDB;
import ServiceImpl.SequenceGenerator;
import ServiceImpl.SyntaxSugar;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SequenceGeneratorTest {

    private SequenceGenerator sequenceGenerator;
    private ConfigDB configDB;

    @Before
    public void setup() {
        configDB=new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        sequenceGenerator = new SequenceGenerator(configDB);
    }

    @Test
    public void shouldIncrementTheIdValueForPassenger() {
        String query = "SELECT MAX(passenger.id) from PassengerDao passenger";
        Long id=getNextId(query);
        assertEquals(sequenceGenerator.generateSequencePassengers(),id);
    }

    @Test
    public void shouldIncrementTheIdValueForUserDetails() {
        String query = "SELECT MAX(user.id) from UserDetailsDao user";
        Long id=getNextId(query);
        assertEquals(sequenceGenerator.generateSequenceUserDetails(),id);
    }

    @Test
    public void shouldIncrementTheIdValueForOrderDetails() {

        String query = "SELECT MAX(orders.id) from OrderDetailsDao orders";
        Long id=getNextId(query);
        assertEquals(sequenceGenerator.generateSequenceOrderDetails(),id);

    }
    private long getNextId(String query){
        Session session = configDB.getSession();
        long maxId;
        try {
            maxId = (Long) session.createQuery(query).uniqueResult();
        } catch (NullPointerException ex) {
            maxId = 1;
            return maxId;
        }
        maxId++;
        return maxId;
    }
    }

