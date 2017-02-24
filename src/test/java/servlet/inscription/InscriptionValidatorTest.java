package servlet.inscription;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test sur le validator d'inscription
 * 
 * @author snesztler
 *
 */
public class InscriptionValidatorTest {
    private InscriptionValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new InscriptionValidator();
    }

    @Test
    public void test() {
        assertFalse(validator.checkPassword("test"));
        assertFalse(validator.checkPassword("Meuh"));
        assertFalse(validator.checkPassword("12345"));
        assertFalse(validator.checkPassword("Meuh123"));

        assertTrue(validator.checkPassword("Check12345"));
    }

}
