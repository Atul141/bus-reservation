package ServiceTest;


import Dao.OrderDetailsDao;
import Models.OrderDetails;
import ServiceImpl.ConfigDB;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.OrderDetailsService;
import Services.UserBookingsService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserBookingServiceTest {

    private OrderDetailsService orderDetailsService;
    private ConfigTest configTest;
    private ConfigDB configDB;
    private UserBookingsService userBookingsService;

    @Before
    public void setup() {
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        userBookingsService = new UserBookingsService(configDB);
        configTest = new ConfigTest();
        initMocks(this);
        orderDetailsService = mock(OrderDetailsService.class);
    }

    @Test
    public void shouldGetOrderDetails() {
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        OrderDetailsDao orderDetailsDao = configTest.getOrderDetailsDao();
        List<OrderDetailsDao> orderDetailsDaoList = new ArrayList<OrderDetailsDao>();
        orderDetailsDaoList.add(orderDetailsDao);
        when(orderDetailsService.getOrderDetails("abc@gmail.com")).thenReturn(orderDetailsDaoList);
        orderDetailsList = userBookingsService.getOrderDetailsList("abc@gmail.com");

        List<OrderDetails> orderDetailsListExpected = new ArrayList<OrderDetails>();
        orderDetailsListExpected.add(configTest.getOrderDetails());
        assertEquals(orderDetailsListExpected, orderDetailsList);
    }
}
