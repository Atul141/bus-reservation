package ServiceTest;


import ServiceImplTest.ConfigTest;
import Services.PaymentWrapperService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class paymentServiceTest {

    private PaymentWrapperService paymentWrapperService;
    private ConfigTest configTest;

    @Before
    public void setup() {
        configTest = new ConfigTest();
        paymentWrapperService = new PaymentWrapperService();

    }

    @Test
    public void shouldGetMonthsList() {
        List<Integer> monthsList = configTest.getMonthsList();
        assertEquals(monthsList, paymentWrapperService.getMonthsList());
    }

    @Test
    public void shouldGetCardTypes() {
        List<String> cardType = configTest.getCardType();
        assertEquals(cardType, paymentWrapperService.getCardTypes());
    }

    @Test
    public void shouldGetYearsList() {
        List<Integer> yearsList = configTest.getYearsList();
        assertEquals(yearsList, paymentWrapperService.getYearsList());
    }
}
