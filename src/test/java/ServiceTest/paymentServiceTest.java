package ServiceTest;


import Models.PaymentWrapper;
import ServiceImplTest.ConfigTest;
import Services.PaymentService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class paymentServiceTest {

    private PaymentService paymentService;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        paymentService = new PaymentService();

    }

    @Test
    public void shouldGetMonthsList() {
        List<Integer> monthsList = configTest.getMonthsList();
        assertEquals(monthsList, paymentService.getMonthsList());
    }

    @Test
    public void shouldGetCardTypes() {
        List<String> cardType = configTest.getCardType();
        assertEquals(cardType, paymentService.getCardTypes());
    }

    @Test
    public void shouldGetYearsList() {
        List<Integer> yearsList = configTest.getYearsList();
        assertEquals(yearsList, paymentService.getYearsList());
    }
}
