package ServiceImplTest;


import ServiceImpl.SequenceGenerator;
import org.junit.Before;
import org.junit.Test;

public class SequenceGeneratorTest {

    private SequenceGenerator sequenceGenerator;

    @Before
    public void setup() {
        sequenceGenerator = new SequenceGenerator();
    }

    @Test
    public void shouldIncrementTheIdValueForPassenger() {
        System.out.println(sequenceGenerator.generateSequencePassengers());
    }

    @Test
    public void shouldIncrementTheIdValueForUserDetails() {
        System.out.println(sequenceGenerator.generateSequenceUserDetails());
    }

    @Test
    public void shouldIncrementTheIdValueForOrderDetails() {
        System.out.println(sequenceGenerator.generateSequenceOrderDetails());
    }

}
