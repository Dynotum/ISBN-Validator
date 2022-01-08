import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorISBNTest {

    @Test
    public void checkValidatorISBN() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        boolean result = validatorISBN.checkISBN(134685997);
        assertTrue(result);
    }

    @Test
    public void checkInvalidValidatorISBN() {
        ValidatorISBN validatorISBN = new ValidatorISBN();
        boolean result = validatorISBN.checkISBN(134685999);
        assertFalse(result);
    }

}