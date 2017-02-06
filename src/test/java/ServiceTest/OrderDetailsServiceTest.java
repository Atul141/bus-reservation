package ServiceTest;


import Dao.OrderDetailsDao;
import Models.AvailableSeatWrapper;
import Models.OrderDetails;
import Models.Route;
import Models.SelectedSeatWrapper;
import ServiceImpl.ConfigDB;
import ServiceImpl.SaveImpl;
import ServiceImpl.SequenceGenerator;
import ServiceImpl.SyntaxSugar;
import ServiceImplTest.ConfigTest;
import Services.OrderDetailsService;
import jdk.nashorn.internal.runtime.regexp.joni.Syntax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class OrderDetailsServiceTest {

    private ConfigDB configDB;
    private ConfigTest configTest;
    private OrderDetails orderDetails;



    @Before
    public void setup() {
        initMocks(this);
        configTest = new ConfigTest();
        configDB = new ConfigDB();
        configDB.setEnvironment(SyntaxSugar.TEST_ENV);
        orderDetails = configTest.getOrderDetails(0);
    }

    @InjectMocks
    OrderDetailsService orderDetailsService=new OrderDetailsService(configDB);
    @Mock
    SequenceGenerator sequenceGenerator=new SequenceGenerator();
    @Test
    public void shouldUpdateRoute() {
        Route route = new Route();
        route.setAvailableNoSeats(10);
        assertEquals(8, orderDetailsService.updateRoute(route, 2).getAvailableNoSeats());

    }

    @Test
    public void shouldUpdateAvailableSeat() {
        AvailableSeatWrapper availableSeatWrapper = configTest.getAvailableSeatwrapper();
        AvailableSeatWrapper availableSeatWrapperUpdated = configTest.getAvailableSeatwrapperAfterUpdate();
        SelectedSeatWrapper selectedSeatWrapper = configTest.getSeatSelectionWrapper();
        availableSeatWrapper = orderDetailsService.updateAvailableSeats(availableSeatWrapper, selectedSeatWrapper);

        assertEquals(availableSeatWrapperUpdated.getGeneral(), availableSeatWrapper.getGeneral());
        assertEquals(availableSeatWrapperUpdated.getWomenReservation(), availableSeatWrapper.getWomenReservation());
        assertEquals(availableSeatWrapperUpdated.getSeniorCitizenReserved(), availableSeatWrapper.getSeniorCitizenReserved());
        assertEquals(availableSeatWrapperUpdated.getDisabledReserved(), availableSeatWrapper.getDisabledReserved());
    }



    @Test
    public void shouldSaveOrder() {

        when(sequenceGenerator.generateSequenceOrderDetails()).thenReturn(1L);
        assertEquals(1L, orderDetailsService.saveOrder(orderDetails));

    }

    @After
    public void delete() {
        OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
        orderDetailsDao.setId(orderDetails.getId());
        orderDetailsDao.setEmail(orderDetails.getEmail());
        orderDetailsDao.setPrice(orderDetails.getPrice());
        orderDetailsDao.setRoute_id(orderDetails.getRoute_id());
        orderDetailsDao.setStatus(orderDetails.getStatus());
        orderDetailsDao.setTime(orderDetails.getTime());
        configTest.delete(orderDetailsDao);
    }

}

