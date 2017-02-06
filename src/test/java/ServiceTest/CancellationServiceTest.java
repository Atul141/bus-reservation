package ServiceTest;


import Models.OrderDetails;
import Models.Route;
import ServiceImpl.ConfigDB;
import ServiceImplTest.ConfigTest;
import Services.CancellationService;
import Services.OrderDetailsService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CancellationServiceTest {

    private CancellationService cancellationService;
    private OrderDetails orderDetails;
    private ConfigTest configTest;
    private ConfigDB configDB;
    private Route route;
    @Before
    public void setup(){
        configTest=new ConfigTest();
        configDB=new ConfigDB();
        route=configTest.getRouteDetails();
        OrderDetailsService orderDetailsService=new OrderDetailsService(configDB);
//        orderDetails=orderDetailsService.getOrderBasedOnId(1);
        cancellationService= new CancellationService();

    }
    @Test
    public void shouldReturnRefundAmount(){
        orderDetails=configTest.getOrderDetails(181L);
        assertEquals(1,cancellationService.getRefundAmount(orderDetails,route));
    }
}
