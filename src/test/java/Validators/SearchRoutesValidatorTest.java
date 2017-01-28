package Validators;


import Models.Route;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SearchRoutesValidatorTest {

    private SearchRoutesValidator searchRoutesValidator;
    private Route route;
    private String date;

    @Before
    public void setup() {
        searchRoutesValidator = new SearchRoutesValidator();
        route = new Route();
        route.setSource("Mysore");
        route.setDestination("Mangalore");
        date = "2017-Jan-15";

    }

    @Test
    public void shouldReturnErrorIfSourceFieldIsEmpty() {
        route.setSource("   ");
        assertEquals("Error!:Source is Compulsory field", searchRoutesValidator.validateAllFields(route, date));
    }

    @Test
    public void shouldNotReturnErrorIfSourceFieldIsNotEmpty() {
        assertEquals(null, searchRoutesValidator.validateAllFields(route, date));
    }

    @Test
    public void shouldReturnErrorIfDestinationFieldIsEmpty() {
        route.setDestination("   ");
        assertEquals("Error!:Destination is Compulsory field", searchRoutesValidator.validateAllFields(route, date));
    }

    @Test
    public void shouldNotReturnErrorIfDestinationFieldIsNotEmpty() {
        assertEquals(null, searchRoutesValidator.validateAllFields(route, date));
    }

    @Test
    public void shouldReturnErrorIfDateFieldIsEmpty() {
        date = "  ";
        assertEquals("Error!:Date is Compulsory field", searchRoutesValidator.validateAllFields(route, date));
    }

    @Test
    public void shouldNotReturnErrorIfDateFieldIsNotEmpty() {
        assertEquals(null, searchRoutesValidator.validateAllFields(route, date));
    }
}
