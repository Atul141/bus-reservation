package Validators;

import Models.UserDetails;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RegistrationFormValidatorTest {

    private UserDetails user;
    private RegistrationFormValidator validator;

    @Before
    public void setup() {
        user = new UserDetails();
        validator = new RegistrationFormValidator();
        user.setFirstName("abc");
        user.setLastName("def");
        user.setEmail("abc@gmail.com");
        user.setPhone("1234567890");
        user.setPassword("pass");
    }

    @Test
    public void shouldReturnErrorWhenFirstNameFieldIsEmpty() {
        user.setFirstName("");
        assertEquals("First Name is Compulsory field", validator.validateAllFields(user));
    }

    @Test
    public void shouldNotReturnErrorWhenFirstNameFieldIsNotEmpty() {
        assertEquals(null, validator.validateAllFields(user));
    }


    @Test
    public void shouldReturnErrorWhenLastNameFieldIsEmpty() {
        user.setLastName("");
        assertEquals("Last Name is Compulsory field", validator.validateAllFields(user));
    }

    @Test
    public void shouldNotReturnErrorWhenLastNameFieldIsNotEmpty() {
        assertEquals(null, validator.validateAllFields(user));
    }

    @Test
    public void shouldReturnErrorWhenEmailFieldIsEmpty() {
        user.setEmail("  ");
        assertEquals("Email is Compulsory field", validator.validateAllFields(user));
    }

    @Test
    public void shouldReturnErrorWhenEmailFieldIsInvalid() {
        user.setEmail("abcd");
        assertEquals("Invalid Email", validator.validateAllFields(user));
    }

    @Test
    public void shouldNotReturnErrorWhenEmailFieldIsvalid() {
        user.setEmail("abcd@gmail.com");
        assertEquals(null, validator.validateAllFields(user));
    }

    @Test
    public void shouldNotReturnErrorWhenEmailFieldIsNotEmpty() {
        assertEquals(null, validator.validateAllFields(user));
    }

    @Test
    public void shouldReturnErrorWhenPasswordFieldIsEmpty() {
        user.setPassword("   ");
        assertEquals("Password is Compulsory field", validator.validateAllFields(user));
    }

    @Test
    public void shouldNotReturnErrorWhenPasswordFieldIsNotEmpty() {
        assertEquals(null, validator.validateAllFields(user));
    }

    @Test
    public void shouldReturnErrorWhenPhoneFieldIsEmpty() {
        user.setPhone("   ");
        assertEquals("Phone Number is Compulsory field", validator.validateAllFields(user));
    }

    @Test
    public void shouldReturnErrorWhenPhoneFieldIsInvalid() {
        user.setPhone("12345678hgj");
        assertEquals("Phone Number is Invalid", validator.validateAllFields(user));
    }


    @Test
    public void shouldNotReturnErrorWhenPhoneFieldIsValid() {
        assertEquals(null, validator.validateAllFields(user));
    }

}
