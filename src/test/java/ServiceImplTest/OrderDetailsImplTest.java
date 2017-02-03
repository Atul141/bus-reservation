package ServiceImplTest;


import Dao.OrderDetailsDao;
import ServiceImpl.ConfigDB;
import ServiceImpl.OrderDetailsImpl;
import ServiceImpl.SyntaxSugar;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderDetailsImplTest {

    private OrderDetailsImpl orderDetailsImpl;
    private OrderDetailsDao orderDetailsDao;
    private ConfigTest configTest;
    private ConfigDB configDB;

    @Before
    public void setup() {
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        configTest = new ConfigTest();
        orderDetailsImpl = new OrderDetailsImpl(configDB);
        orderDetailsDao = configTest.getOrderDetailsDao();
    }

    @Test
    public void shouldSaveOrderDetails() {
        orderDetailsImpl.saveOrderDetails(orderDetailsDao);

        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        Session session = configDB.getSession();
        String query = "FROM OrderDetailsDao  orders where orders.id= 1";
        Transaction transaction = session.beginTransaction();
        OrderDetailsDao orderDetailsDao1 = (OrderDetailsDao) session.createQuery(query).uniqueResult();
        transaction.commit();
        assertEquals("abc@gmail.com", orderDetailsDao1.getEmail());
    }
    @After
    public void delete(){
        configTest.delete(orderDetailsDao);
    }

}
