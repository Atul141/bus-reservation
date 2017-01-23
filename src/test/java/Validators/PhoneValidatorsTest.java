package Validators;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class PhoneValidatorsTest {
    private PhoneValidators phoneValidators;

    @Before
    public void setup() {
        phoneValidators = new PhoneValidators();
    }

    @Test
    public void shouldReturnFalseIfPhoneIsInValid() {
        assertFalse(phoneValidators.validate("q1234567899"));
    }


    @Test
    public void shouldReturnTrueIfPhoneIsValid() {
        assertTrue(phoneValidators.validate("1234567890"));}
    }

