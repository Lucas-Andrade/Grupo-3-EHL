package test.java.utils;


import static org.junit.Assert.assertEquals;
import main.java.utils.exceptions.WrongLoginPasswordException;
import org.junit.Test;


/**
 * Test case that targets the {@link WrongLoginPasswordException} class.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class WrongLoginPasswordExceptionTest {
    
    @Test
    public void shouldHaveTheExpectedMessage() {
        assertEquals( "Wrong password: u1's password is not p1.",
                      new WrongLoginPasswordException( "u1", "p1" ).getMessage() );
    }
    
}
