package Validators;

import Models.UserDetails;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class LoginValidatorTest {

    private UserDetails user;
    private LoginValidator validator;

    @Before
    public void setup() {
        user = new UserDetails();
        validator = new LoginValidator();
        user.setEmail("abc@gmail.com");
        user.setPassword("pass");
    }
    @Test
    public void shouldReturnErrorWhenPasswordFieldIsEmpty() {
        user.setPassword("   ");
        assertEquals("Password is Compulsory field", validator.validate(user));
    }

    @Test
    public void shouldNotReturnErrorWhenPasswordFieldIsNotEmpty() {
        assertEquals(null, validator.validate(user));
    }
    @Test
    public void shouldReturnErrorWhenEmailFieldIsEmpty() {
        user.setEmail("  ");
        assertEquals("Email is Compulsory field", validator.validate(user));
    }

    @Test
    public void shouldReturnErrorWhenEmailFieldIsInvalid() {
        user.setEmail("abcd");
        assertEquals("Invalid Email", validator.validate(user));
    }

    @Test
    public void shouldNotReturnErrorWhenEmailFieldIsvalid() {
        user.setEmail("abcd@gmail.com");
        assertEquals(null, validator.validate(user));
    }

    @Test
    public void shouldNotReturnErrorWhenEmailFieldIsNotEmpty() {
        assertEquals(null, validator.validate(user));
    }
}
