package Validators;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InjectionvalidatorTest {
    private InjectionValidator injectionValidator;
    @Before
    public void setup(){
        injectionValidator=new InjectionValidator();
    }

    @Test
    public void shouldReturnErrorIfStringContainsSymbol(){
        assertTrue(injectionValidator.validateInjection("<"));
        assertTrue(injectionValidator.validateInjection(">"));
        assertTrue(injectionValidator.validateInjection("("));
        assertTrue(injectionValidator.validateInjection(")"));
        assertTrue(injectionValidator.validateInjection(";"));
    }
    @Test
    public void shouldNotReturnErrorIfStringDoesntContinsSymbols(){
        assertFalse(injectionValidator.validateInjection("sample"));
    }
    @Test
    public void shouldReturnErrorIfStringContainsScript(){
        String sample="<script></script>";
        assertTrue(injectionValidator.validateInjection(sample));
    }
    @Test
    public void shouldReturnErrorIfStringContainsSQLInjection(){
        String sample="or 1=1";
        assertTrue(injectionValidator.validateInjection(sample));
    }
}
