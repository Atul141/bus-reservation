package ServiceTest;


import Services.PriceCalculation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriceCalculationTest {

@Test
    public void shouldCalculatePriceForAGivenNumberOfPassengers(){
    PriceCalculation priceCalculation=new PriceCalculation();
    assertEquals(600,priceCalculation.calculateprice(300,2));
}
}
