package ServiceTest;

import Dao.OrderDetailsDao;
import Models.OrderDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.AutoCancellationService;
import Services.OrderDetailsService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class AutoCancellationServiceTest {

    private ConfigTest configTest;
    private ConfigDB configDB;
    private AutoCancellationService autoCancellationService;
    private OrderDetailsService orderDetailsService;
    private OrderDetails orderDetails;

    @Before
    public void setup() {
        configDB = new ConfigDB();
        configTest = new ConfigTest();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        autoCancellationService = new AutoCancellationService(configDB);
        orderDetailsService = new OrderDetailsService(configDB);
        orderDetails=configTest.getOrderDetails();

    }


    @Test
    public void shouldNotAutoCancelAutoIfPaymentIsMadeBefore30min() {
        orderDetailsService.saveOrder(orderDetails);

        autoCancellationService.autoCancelOrder();
        Session session = configDB.getSession();
        String email = "abc@gmail.com";
        String query = "FROM OrderDetailsDao  orders where orders.email= " + "'" + email + "'";
        Transaction transaction = session.beginTransaction();
        OrderDetailsDao orderDetailsDao1 = (OrderDetailsDao) session.createQuery(query).uniqueResult();
        transaction.commit();
        assertEquals(SyntaxSugar.PENDING, orderDetailsDao1.getStatus());
    }

    @Test
    public void shouldAutoCancelAutoIfPaymentIsNotMadeBefore30min() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusMinutes(32);
        Timestamp timestamp = new java.sql.Timestamp(dateTime.getMillis());
        orderDetails.setTime(timestamp);
        orderDetailsService.saveOrder(orderDetails);

        autoCancellationService.autoCancelOrder();

        Session session = configDB.getSession();
        String email = "abc@gmail.com";
        String query = "FROM OrderDetailsDao  orders where orders.email= " + "'" + email + "'";
        Transaction transaction = session.beginTransaction();
        OrderDetailsDao orderDetailsDao1 = (OrderDetailsDao) session.createQuery(query).uniqueResult();
        transaction.commit();

        assertEquals(SyntaxSugar.CANCEL, orderDetailsDao1.getStatus());

    }

    @After
    public void delete() {
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        orderDetailsDao.setEmail(orderDetails.getEmail());
        orderDetailsDao.setTime(orderDetails.getTime());
        orderDetailsDao.setStatus(orderDetails.getStatus());
        orderDetailsDao.setId(orderDetails.getId());
        orderDetailsDao.setPrice(orderDetails.getPrice());
        orderDetailsDao.setRoute_id(orderDetails.getRoute_id());
        configTest.delete(orderDetailsDao);
    }

}
