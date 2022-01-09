import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorISBNTest {

    @Test
    public void checkAValid10DigitsISBN() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        boolean result = validatorISBN.checkISBN("0134685997");
        assertTrue("Effective java ISBN", result);

        result = validatorISBN.checkISBN("0984782869");

        assertTrue("CTCI", result);
    }

    @Test
    public void checkAValid13DigitsISBN() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        boolean result = validatorISBN.checkISBN("9780984782857");
        assertTrue(result);
    }

    @Test
    public void tenDigitsISBNNumbersEndingInAnXAreValid() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        boolean result = validatorISBN.checkISBN("043942089X");
        assertTrue("The Thief Lord", result);
    }

    @Test
    public void checkInvalid10DigitsISBN() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        boolean result = validatorISBN.checkISBN("0134685999");
        assertFalse(result);
    }

    @Test
    public void checkInvalid13DigitsISBN() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        boolean result = validatorISBN.checkISBN("9780984782858");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitNotAllowed() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        validatorISBN.checkISBN("134685999");
//        asser(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nonNumericISBNAreAllowed() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        validatorISBN.checkISBN("helloworld");
    }


}