package Validators;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class EmailValidatorTest {

private EmailValidator emailValidator;

@Before
    public void setup(){
    emailValidator=new EmailValidator();
}

@Test
    public void shouldReturnFalseIfEmailIsInValid(){
        assertFalse(emailValidator.validate("abcd"));}


@Test
    public void shouldReturnTrueIfEmailIsValid(){
        assertTrue(emailValidator.validate("abcd@gmail.com"));}
}
